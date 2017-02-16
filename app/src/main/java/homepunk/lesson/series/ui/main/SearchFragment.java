package homepunk.lesson.series.ui.main;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.series.App;
import homepunk.lesson.series.adapter.SeriesAdapter;
import homepunk.lesson.series.interfaces.Presenter;
import homepunk.lesson.series.interfaces.View.SearchFragmentView;
import homepunk.lesson.series.model.Series;

public class SearchFragment extends Fragment implements SearchFragmentView {
    @Bind(R.id.search_rv) RecyclerView rvSearch;
    @Bind(R.id.search_recomendations_rv) RecyclerView rvRecommend;

    @Inject Presenter.SearchFragmentPresenter searchFragmentPresenter;

    private List<Series> recommendSeries, searchResults;
    private SeriesAdapter recommendAdapter, seachAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_search, container, false);
        initUI(root);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        searchFragmentPresenter.setView(this);
        searchFragmentPresenter.getSearchRecommendationResults();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchFragmentPresenter.getSearchResults(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchFragmentPresenter.getSearchResults(newText);
                return true;
            }
        });
    }

    private void initUI(ViewGroup root){
        ButterKnife.bind(this, root);
        App.getAppComponent(getContext()).plus(this);
        setHasOptionsMenu(true);
        setUpRecommendationsRv();
        setUpSearchRv();
    }

    @Override
    public void onRecommendedSeriesRecieved(List<Series> seriesList) {
        recommendSeries.clear();
        recommendSeries.addAll(seriesList);
        recommendAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSearchResultsRecieved(List<Series> seriesList) {
        searchResults.clear();
        searchResults.addAll(seriesList);
        seachAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getContext(), "Error: " + error,
                Toast.LENGTH_SHORT).show();
    }

    private void setUpRecommendationsRv(){
        recommendSeries = new ArrayList<>();
        recommendAdapter = new SeriesAdapter(getContext(), recommendSeries);
        rvRecommend.setAdapter(recommendAdapter);
        rvRecommend.setLayoutManager(new GridLayoutManager(getContext(), getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE ? 3 : 2));
    }

    private void setUpSearchRv(){
        searchResults = new ArrayList<>();
        seachAdapter = new SeriesAdapter(getContext(), searchResults);
        rvSearch.setAdapter(seachAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvSearch.setLayoutManager(layoutManager);
    }
}

package homepunk.lesson.first.view.search;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.lesson.first.adapter.TVSeriesAdapter;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.interfaces.View.SearchFragmentView;
import homepunk.lesson.first.model.TVSeries;
import homepunk.lesson.first.presenter.search.SearchFragmentPresenter;

public class SearchFragment extends Fragment implements SearchFragmentView {
    @Bind(R.id.search_rv) RecyclerView rvSearch;
    @Bind(R.id.search_recomendations_rv) RecyclerView rvRecommend;

    private SearchFragmentPresenter searchFragmentPresenter;
    private List<TVSeries> recommendSeries;
    private TVSeriesAdapter recommendAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_search, container, false);
        searchFragmentPresenter = new SearchFragmentPresenter(getContext());
        initUI(root);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        searchFragmentPresenter.setView(this);
        searchFragmentPresenter.getRecommendedSeries();
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
//                searchFragmentPresenter.search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                searchFragmentPresenter.search(newText);
                return true;
            }
        });
    }

    private void initUI(ViewGroup root){
        ButterKnife.bind(this, root);
        setHasOptionsMenu(true);

        recommendSeries = new ArrayList<>();
        recommendAdapter = new TVSeriesAdapter(getContext(), recommendSeries);
        rvRecommend.setAdapter(recommendAdapter);
        rvRecommend.setLayoutManager(new GridLayoutManager(getContext(), getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE ? 3 : 2));

    }

    @Override
    public void onRecommendedSeriesRecieved(List<TVSeries> tvSeries) {
        recommendSeries.clear();
        recommendSeries.addAll(tvSeries);
        recommendAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getContext(), "Error: " + error,
                Toast.LENGTH_SHORT).show();
    }
}

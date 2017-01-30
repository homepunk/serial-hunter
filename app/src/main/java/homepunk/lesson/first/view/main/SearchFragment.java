package homepunk.lesson.first.view.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.presenter.search.SearchFragmentPresenter;

public class SearchFragment extends Fragment implements homepunk.lesson.first.interfaces.View {
    @Bind(R.id.search_rv) RecyclerView rvSearch;
    @Bind(R.id.search_recomendations_rv) RecyclerView rvRecommend;
    private ViewGroup root;
    private SearchFragmentPresenter searchFragmentPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, root);
        setHasOptionsMenu(true);

        searchFragmentPresenter = new SearchFragmentPresenter();
        searchFragmentPresenter.setView(this);
        searchFragmentPresenter.setRecycleView(rvRecommend);
        searchFragmentPresenter.setContext(getContext());
        searchFragmentPresenter.getRecommendationTVSeries();

        return root;
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
}

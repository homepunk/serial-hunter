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
import homepunk.lesson.first.presenter.main.RecommendPresenter;
import homepunk.lesson.first.presenter.main.SearchPresenter;
import homepunk.lesson.first.view.View.SearchFragmentView;

public class SearchFragment extends Fragment implements SearchFragmentView{
    @Bind(R.id.search_rv) RecyclerView rvSearch;
    @Bind(R.id.search_recomendations_rv) RecyclerView rvRecommend;
    private ViewGroup root;
    private SearchPresenter searchModule;
    private RecommendPresenter recommendModule;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, root);
        setHasOptionsMenu(true);

        searchModule = new SearchPresenter(this);

        recommendModule = new RecommendPresenter(this);
        recommendModule.attachRecommendRecycleView();
        recommendModule.setRecommendtations();

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
                searchModule.search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchModule.attachSearchRecycleView();
                searchModule.search(newText);
                return true;
            }
        });
    }

    @Override
    public RecyclerView getSearchRecycleView() {
        return this.rvSearch;
    }

    @Override
    public RecyclerView getRecommendRecycleView() {
        return this.rvRecommend;
    }
}

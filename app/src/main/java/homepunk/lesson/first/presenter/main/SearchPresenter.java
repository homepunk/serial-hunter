package homepunk.lesson.first.presenter.main;

import android.support.v7.widget.RecyclerView;

import homepunk.lesson.first.model.TVFetchrAsyncModel;
import homepunk.lesson.first.presenter.Presenter;
import homepunk.lesson.first.view.main.SearchFragment;

public class SearchPresenter implements Presenter.SearchPresenter {
    private RecyclerView rvSearch;
    private String searchRequest;
    private SearchFragment view;
    private TVFetchrAsyncModel task;

    public SearchPresenter(SearchFragment view) {
        this.view = view;
        this.rvSearch = view.getRecycleView();
    }

    @Override
    public void attachSearch() {
    }


}

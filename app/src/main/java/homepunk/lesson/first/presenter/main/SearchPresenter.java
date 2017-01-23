package homepunk.lesson.first.presenter.main;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import homepunk.lesson.first.adapter.TVListAdapter;
import homepunk.lesson.first.model.TVFetchrAsyncModel;
import homepunk.lesson.first.model.TVSeries;
import homepunk.lesson.first.presenter.Presenter;
import homepunk.lesson.first.view.main.SearchFragment;

public class SearchPresenter implements Presenter.SearchPresenter {
    private RecyclerView rvSearch;
    private List<TVSeries> searchList;
    private Context context;
    private TVListAdapter sAdapter;
    private SearchFragment view;
    private TVFetchrAsyncModel task;

    public SearchPresenter(SearchFragment view) {
        this.searchList = new ArrayList<>();
        this.view = view;
        this.rvSearch = view.getSearchRecycleView();
        this.context = view.getContext();
    }

    @Override
    public void attachSearchRecycleView() {
        this.sAdapter = new TVListAdapter(context, searchList);
        rvSearch.setAdapter(sAdapter);
        rvSearch.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void search(String newText) {
        task = new TVFetchrAsyncModel(this);
        task.setTVList(searchList);
        task.setAdapter(sAdapter);
        task.makeHttpConnection();
        task.searchByTitle(newText);
    }



}

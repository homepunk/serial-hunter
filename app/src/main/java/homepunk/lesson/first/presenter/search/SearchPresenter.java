package homepunk.lesson.first.presenter.search;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import homepunk.lesson.first.database.Constants;
import homepunk.lesson.first.model.network.TVListFetchrModel;
import homepunk.lesson.first.presenter.Presenter;
import homepunk.lesson.first.presenter.common.RecycleViewPresenter;
import homepunk.lesson.first.view.main.SearchFragment;

public class SearchPresenter implements Presenter.Search {
    private Context context;
    private SearchFragment view;
    private TVListFetchrModel task;
    private RecyclerView recyclerView;
    private RecycleViewPresenter rvPresenter;
    private String newText = "friends";

    public SearchPresenter(SearchFragment view) {
        this.view = view;
        this.context = view.getContext();
        this.rvPresenter = new RecycleViewPresenter(view);
    }

    @Override
    public void addView(ViewGroup view) {
        this.recyclerView = (RecyclerView) view;
    }

    @Override
    public void attachAllViews() {
        rvPresenter.setLayoutManeger(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        rvPresenter.setItemsQuantity(6);
        rvPresenter.attachRecycleView(recyclerView);
    }

    @Override
    public void updateContent() {
        this.task = new TVListFetchrModel(this);
        task.setExecuteRef(Constants.TV_SEARCH + newText + Constants.LANGUAGE_EN + Constants.API_KEY);
        rvPresenter.updateContent(task);
    }

    @Override
    public void search(String newText) {
        this.newText = newText;
        updateContent();
    }

    @Override
    public Context getContext() {
        return context;
    }
}

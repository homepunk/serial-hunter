package homepunk.lesson.first.presenter.search;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import homepunk.lesson.first.database.Constants;
import homepunk.lesson.first.model.network.TVListFetchrModel;
import homepunk.lesson.first.interfaces.Presenter;
import homepunk.lesson.first.presenter.common.RecycleViewPresenter;
import homepunk.lesson.first.view.main.SearchFragment;

public class SearchSimilarViewPresenter implements Presenter {
    private RecyclerView recyclerView;
    private Context context;
    private RecycleViewPresenter rvPresenter;

    public SearchSimilarViewPresenter(SearchFragment view) {
        this.context = view.getContext();
        this.rvPresenter = new RecycleViewPresenter(view);
    }

    @Override
    public void addView(ViewGroup view) {
        this.recyclerView = (RecyclerView) view;
    }

    @Override
    public void attachAllViews() {
        rvPresenter.attachRecycleView(recyclerView);
    }

    @Override
    public void updateContent() {
        TVListFetchrModel task = new TVListFetchrModel(this);
        task.setExecuteRef(Constants.TV_REFENECE + Constants.POPULAR + Constants.LANGUAGE_EN + Constants.API_KEY);
        rvPresenter.updateContent(task);
    }

    @Override
    public Context getContext() {
        return context;
    }
}

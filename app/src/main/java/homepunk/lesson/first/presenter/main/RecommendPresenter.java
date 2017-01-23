package homepunk.lesson.first.presenter.main;


import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import homepunk.lesson.first.adapter.TVListAdapter;
import homepunk.lesson.first.database.Constants;
import homepunk.lesson.first.model.TVFetchrAsyncModel;
import homepunk.lesson.first.model.TVSeries;
import homepunk.lesson.first.presenter.Presenter;
import homepunk.lesson.first.view.main.SearchFragment;

public class RecommendPresenter implements Presenter.RecommendtationsPresenter {
    private RecyclerView rvRecommend;
    private Context context;
    private List<TVSeries> recommendList;
    private TVListAdapter adapter;
    private SearchFragment view;
    private TVFetchrAsyncModel task;

    public RecommendPresenter(SearchFragment view) {
        this.recommendList = new ArrayList<>(Constants.FILM_COUNT);
        this.rvRecommend = view.getRecommendRecycleView();
        this.view = view;
        this.context = view.getContext();

    }

    @Override
    public void attachRecommendRecycleView() {
        this.adapter = new TVListAdapter(context, recommendList);
        rvRecommend.setAdapter(adapter);
        rvRecommend.setLayoutManager(new GridLayoutManager(context, view.getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE ? 3 : 2));
    }

    @Override
    public void setRecommendtations() {
        task = new TVFetchrAsyncModel();
        task.setTVList(recommendList);
        task.setAdapter(adapter);
        task.makeHttpConnection();
        task.execute(Constants.TV_REFENECE + Constants.POPULAR + Constants.LANGUAGE_EN + Constants.API_KEY);
    }
}

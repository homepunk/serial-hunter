package homepunk.lesson.first.presenter.main;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import homepunk.lesson.first.adapter.TVListAdapter;
import homepunk.lesson.first.database.Constants;
import homepunk.lesson.first.model.TVFetchrAsyncModel;
import homepunk.lesson.first.model.TVSeries;
import homepunk.lesson.first.presenter.Presenter;
import homepunk.lesson.first.view.main.MainFragment;


public class RecycleViewPresenter implements Presenter.RecycleViewPresenter {
    private TVListAdapter adapter;
    private TVFetchrAsyncModel task;
    private List<TVSeries> tvList;
    private Context context;
    private MainFragment view;
    private RecyclerView recyclerView;

    public RecycleViewPresenter(MainFragment f) {
        this.view = f;
        this.context = view.getContext();
        this.recyclerView = view.getRecycleView();
        this.tvList = new ArrayList<>(Constants.FILM_COUNT);
    }

    @Override
    public void attachRecycleView() {
        adapter = new TVListAdapter(context, tvList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(context, view.getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE ? 3 : 2));
    }

    @Override
    public void attachPosters() {
        task = new TVFetchrAsyncModel();
        task.setAdapter(adapter);
        task.setTVList(tvList);
        task.makeHttpConnection();
        task.execute(Constants.TV_REFENECE + Constants.TV_TOP20 + Constants.LANGUAGE_EN + Constants.API_KEY);
    }
}

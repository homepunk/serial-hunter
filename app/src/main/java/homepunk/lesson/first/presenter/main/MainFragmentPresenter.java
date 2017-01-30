package homepunk.lesson.first.presenter.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import homepunk.lesson.first.adapter.TVSeriesAdapter;
import homepunk.lesson.first.database.Constants;
import homepunk.lesson.first.interfaces.Presenter;
import homepunk.lesson.first.model.TVSeries;
import homepunk.lesson.first.model.network.TVListFetchrModel;
import homepunk.lesson.first.presenter.common.RecycleViewPresenter;
import homepunk.lesson.first.view.main.MainFragment;


public class MainFragmentPresenter implements Presenter.MainFragmentPresenter, Presenter{
    private MainFragment view;
    private RecyclerView recyclerView;
    private List<TVSeries> tvList;
    private Context context;
    private TVSeriesAdapter adapter;

    @Override
    public void setView(MainFragment view) {
        this.view = view;
    }

    @Override
    public void setRecycleView(RecyclerView view) {
        this.recyclerView = view;
    }

    @Override
    public void getMostPopularTVSeries() {
        TVListFetchrModel tvTask = new TVListFetchrModel();
        tvList = new ArrayList<>(Constants.FILM_COUNT);
        adapter = new TVSeriesAdapter(view.getContext(), tvList);
        setUpRecycleViewPresenter();

        tvTask.setPresenter(this);
        tvTask.setTVList(tvList);
        tvTask.setAdapter(adapter);
        tvTask.setContext(view.getContext());
        tvTask.setExecuteRef(Constants.TV_REFENECE + Constants.TV_TOP20 + Constants.LANGUAGE_EN + Constants.API_KEY);
        tvTask.fetch();
    }

    public Context getContext() {
        return view.getContext();
    }

    private void setUpRecycleViewPresenter(){
        tvList = new ArrayList<>(Constants.FILM_COUNT);
        adapter = new TVSeriesAdapter(view.getContext(), tvList);
        RecycleViewPresenter rvPresenter = new RecycleViewPresenter();
        rvPresenter.setPresenter(this);
        rvPresenter.setContext(view.getContext());
        rvPresenter.setTVList(tvList);
        rvPresenter.setAdapter(adapter);
        rvPresenter.setResources(view.getResources());
        rvPresenter.setUpRecycleView(recyclerView);
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}

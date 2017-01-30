package homepunk.lesson.first.presenter.detailed;

import android.content.Context;
import android.view.ViewGroup;

import homepunk.lesson.first.database.Constants;
import homepunk.lesson.first.model.TVSeries;
import homepunk.lesson.first.model.network.TVObjectFetchrModel;
import homepunk.lesson.first.interfaces.Presenter;

public class DetailedFragmentPresenter implements Presenter {

    private homepunk.lesson.first.view.detailed.DetailedFragment view;
    private int id;

    private TVSeries tvSeries;
    private TVObjectFetchrModel task;
    private Listener listener;

    public DetailedFragmentPresenter(homepunk.lesson.first.view.detailed.DetailedFragment view) {
        this.view = view;
        this.id = view.getFromBundle();
        this.listener = new Listener();
    }

    private void setTvSeries(TVSeries tvSeries) {
        this.tvSeries = tvSeries;
    }

    @Override
    public void attachAllViews() {
        task = new TVObjectFetchrModel();
        task.registerObserver(listener);
        task.setExecuteRef(Constants.TV_REFENECE + id + Constants.LANGUAGE_RU + Constants.API_KEY);
        task.openHttpConnection();
    }

    @Override
    public void updateContent() {
        view.setOverview(tvSeries.overview);
        view.setPosterImage(tvSeries.getFullPosterPath(TVSeries.WIDTH_780));
    }

    @Override
    public Context getContext() {
        return view.getContext();
    }

    @Override
    public void addView(ViewGroup view) {
    }

    public class Listener implements Observer {

        @Override
        public void update(TVSeries item) {
            setTvSeries(item);
            updateContent();
        }
    }
}


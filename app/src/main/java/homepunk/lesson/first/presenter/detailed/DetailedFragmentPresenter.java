package homepunk.lesson.first.presenter.detailed;

import android.content.Context;
import android.view.ViewGroup;

import homepunk.lesson.first.database.Constants;
import homepunk.lesson.first.model.network.TVObjectFetchrModel;
import homepunk.lesson.first.model.TVSeries;
import homepunk.lesson.first.presenter.Presenter;

public class DetailedFragmentPresenter implements Presenter.DetailedFragment {

    private homepunk.lesson.first.view.detailed.DetailedFragment view;
    private int id;
    private boolean update;

    private TVSeries tvSeries;
    private TVObjectFetchrModel task;

    public DetailedFragmentPresenter(homepunk.lesson.first.view.detailed.DetailedFragment view) {
        this.view = view;
        this.id = view.getFromBundle();
    }

    public void openNetworkConnection() {
        task = new TVObjectFetchrModel(this);
        task.setExecuteRef(Constants.TV_REFENECE + id + Constants.LANGUAGE_RU + Constants.API_KEY);
        task.openHttpConnection();
    }

    @Override
    public void addView(ViewGroup view) {

    }

    @Override
    public void attachAllViews() {

    }

    @Override
    public void updateContent() {

    }

    @Override
    public Context getContext() {
        return getContext();
    }

    @Override
    public void update(TVSeries tvSeries) {
        this.tvSeries = tvSeries;
        if(update != true) {
            return;
        } else {
            view.setOverview(tvSeries.overview);
            view.setPosterImage(tvSeries.getFullPosterPath(TVSeries.WIDTH_780));
        }
    }

    @Override
    public void setDetails() {
            update = true;
    }
}


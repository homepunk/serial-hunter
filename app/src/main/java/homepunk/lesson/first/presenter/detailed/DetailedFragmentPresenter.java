package homepunk.lesson.first.presenter.detailed;

import homepunk.lesson.first.database.Constants;
import homepunk.lesson.first.model.TVFetchrAsyncModel;
import homepunk.lesson.first.model.TVSeries;
import homepunk.lesson.first.presenter.Presenter;
import homepunk.lesson.first.view.detailed.DetailedFragment;

public class DetailedFragmentPresenter implements Presenter.DetailedFragmentPresenter {

    private DetailedFragment view;
    private int id;
    private boolean update;

    private TVSeries tvSeries;
    private TVFetchrAsyncModel task;

    public DetailedFragmentPresenter(DetailedFragment view) {
        this.view = view;
        this.id = view.getFromBundle();
    }


    @Override
    public void startNetworkConnection() {
        task = new TVFetchrAsyncModel(this);
        task.makeHttpConnection();
        task.execute(Constants.TV_REFENECE + id + Constants.LANGUAGE_RU + Constants.API_KEY);
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


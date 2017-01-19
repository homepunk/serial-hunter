package homepunk.lesson.first.presenter.detailed;

import homepunk.lesson.first.database.Constants;
import homepunk.lesson.first.model.TVFetchrAsyncModel;
import homepunk.lesson.first.model.TVSeries;
import homepunk.lesson.first.presenter.Presenter;
import homepunk.lesson.first.ui.detailed.DetailedActivityFragment;

public class DetailedFragmentPresenter implements Presenter.DetailedFragmentPresenter {

    private DetailedActivityFragment view;
    private int id;

    private TVSeries tvSeries;
    private TVFetchrAsyncModel task;

    public DetailedFragmentPresenter(DetailedActivityFragment view) {
        this.view = view;
        this.id = view.getDataFromBundle();
    }

    @Override
    public void getDetailedFromNetwork() {
        task = new TVFetchrAsyncModel(view.getContext(), this);
        task.makeHttpConnection();
        task.execute(Constants.TV_REFENECE + id + Constants.LANGUAGE_EN + Constants.API_KEY);
    }

    @Override
    public void notifyDataChanged() {

    }

    public void setTvSeries(TVSeries tvSeries) {
        this.tvSeries = tvSeries;
    }

    @Override
    public void update(TVSeries tvSeries) {
        setTvSeries(tvSeries);
        setDetailedInfo();
    }

    @Override
    public void setDetailedInfo() {
            view.setOverview(tvSeries.overview);

            view.setPosterImage(tvSeries.getFullPosterPath(TVSeries.WIDTH_780));
    }
}


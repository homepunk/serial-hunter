package homepunk.lesson.first.presenter;

import java.util.List;

import homepunk.lesson.first.interfaces.Listeners;
import homepunk.lesson.first.interfaces.Model;
import homepunk.lesson.first.interfaces.Presenter;
import homepunk.lesson.first.interfaces.View;
import homepunk.lesson.first.model.TVSeries;


public class MainFragmentPresenter implements Presenter.MainFragmentPresenter{
    private final Model.TVSeriesModel model;
    private View.MainFragmentView view;

    public MainFragmentPresenter(Model.TVSeriesModel model) {
        this.model = model;
    }

    @Override
    public void setView(View.MainFragmentView view) {
        this.view = view;
    }

    @Override
    public void getMostPopularSeries() {
        model.fetchSeriesList(new Listeners.ListListener() {
            @Override
            public void onResult(List<TVSeries> tvSeries) {
                if (MainFragmentPresenter.this.view != null)
                    MainFragmentPresenter.this.view.onTVSeriesReceived(tvSeries);
            }

            @Override
            public void onError(Exception e) {
                if (MainFragmentPresenter.this.view != null)
                    MainFragmentPresenter.this.view.onError(e.getLocalizedMessage());
            }
        });
    }

    @Override
    public void onSeriesSelected(int id) {
    }
}

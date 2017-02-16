package homepunk.lesson.series.presenter;

import java.util.List;

import homepunk.lesson.series.interfaces.Listeners;
import homepunk.lesson.series.interfaces.Model;
import homepunk.lesson.series.interfaces.Presenter;
import homepunk.lesson.series.interfaces.View;
import homepunk.lesson.series.model.Series;

public class TopRatedFragmentPresenter implements Presenter.TopRatedFragmentPresenter {
    private final Model.DataManagerModel model;
    private View.HotUpdatesFragmentView view;

    public TopRatedFragmentPresenter(Model.DataManagerModel maodel) {
        this.model = maodel;
    }

    @Override
    public void setView(View.HotUpdatesFragmentView view) {
        this.view = view;
    }

    @Override
    public void getHotUpdates() {
        model.fetchTopRatedSeries(new Listeners.RetrofitListener<List<Series>>() {
            @Override
            public void onResult(List<Series> series) {
                if (TopRatedFragmentPresenter.this.view != null)
                    TopRatedFragmentPresenter.this.view.onTopRatedRecieved(series);
            }

            @Override
            public void onError(String e) {
                if (TopRatedFragmentPresenter.this.view != null)
                    TopRatedFragmentPresenter.this.view.onError(e);
            }
        });
    }
}

package homepunk.lesson.series.presenter;

import java.util.List;

import homepunk.lesson.series.interfaces.Listeners;
import homepunk.lesson.series.interfaces.Model;
import homepunk.lesson.series.interfaces.Presenter;
import homepunk.lesson.series.interfaces.View;
import homepunk.lesson.series.model.Series;

public class PopularFragmentPresenter implements Presenter.PopularFragmentPresenter {
    private final Model.DataManagerModel model;
    private View.PopularFragmentView view;

    public PopularFragmentPresenter(Model.DataManagerModel maodel) {
        this.model = maodel;
    }

    @Override
    public void setView(View.PopularFragmentView view) {
        this.view = view;
    }

    @Override
    public void getPopularSeries() {
        model.fetchPopularSeries(new Listeners.RetrofitListener<List<Series>>() {
            @Override
            public void onResult(List<Series> series) {
                if (PopularFragmentPresenter.this.view != null)
                    PopularFragmentPresenter.this.view.onPopularSeriesRecieved(series);
            }

            @Override
            public void onError(String e) {
                if (PopularFragmentPresenter.this.view != null)
                    PopularFragmentPresenter.this.view.onError(e);
            }
        });
    }
}

package homepunk.lesson.series.presenter;

import java.util.List;

import homepunk.lesson.series.interfaces.Model;
import homepunk.lesson.series.interfaces.Presenter;
import homepunk.lesson.series.interfaces.View;
import homepunk.lesson.series.model.Series;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        model.fetchPopularSeries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Series>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if(PopularFragmentPresenter.this.view != null)
                            PopularFragmentPresenter.this.view.onError(e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(List<Series> series) {
                        if(PopularFragmentPresenter.this.view != null)
                            PopularFragmentPresenter.this.view.onPopularSeriesRecieved(series);
                    }
                });
    }
}

package homepunk.lesson.series.presenter;

import java.util.List;

import homepunk.lesson.series.interfaces.Model;
import homepunk.lesson.series.interfaces.Presenter;
import homepunk.lesson.series.interfaces.View;
import homepunk.lesson.series.model.Series;
import homepunk.lesson.series.utils.RxUtils;
import rx.Subscriber;
import rx.Subscription;


public class MainViewPresenter implements Presenter.MainPresenter {
    private final Model.DataManager model;
    private View.MainFragmentView view;
    private Subscription subscription;

    // nucles
    public MainViewPresenter(Model.DataManager model) {
        this.model = model;
    }

    @Override
    public void setView(View.MainFragmentView view) {
        this.view = view;
    }

    @Override
    public void getOnAirSeries() {
        model.fetchOnAirSeries()
                .compose(RxUtils.applySchedulers())
                .subscribe(getSubscription());
    }

    @Override
    public void onSeriesSelected(int id) {
    }

    @Override
    public void unsuscribeFromObservable() {
        if(RxUtils.isSubsribed(subscription))
            subscription.unsubscribe();
    }

    private Subscriber getSubscription() {
        if (!RxUtils.isSubsribed(subscription))
            this.subscription = new Subscriber<List<Series>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    if (MainViewPresenter.this.view != null)
                        MainViewPresenter.this.view.onError(e.getLocalizedMessage());
                }

                @Override
                public void onNext(List<Series> series) {
                    if (MainViewPresenter.this.view != null)
                        MainViewPresenter.this.view.onResult(series);
                }
            };

        return (Subscriber) this.subscription;
    }
}

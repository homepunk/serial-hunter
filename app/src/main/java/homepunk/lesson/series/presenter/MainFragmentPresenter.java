package homepunk.lesson.series.presenter;

import java.util.List;

import homepunk.lesson.series.interfaces.Model;
import homepunk.lesson.series.interfaces.Presenter;
import homepunk.lesson.series.interfaces.View;
import homepunk.lesson.series.model.Series;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainFragmentPresenter implements Presenter.MainFragmentPresenter {
    private final Model.DataManagerModel model;
    private View.MainFragmentView view;
    private Subscription subscription;

    public MainFragmentPresenter(Model.DataManagerModel model) {
        this.model = model;
    }

    @Override
    public void setView(View.MainFragmentView view) {
        this.view = view;
    }

    @Override
    public void getOnAirSeries() {
        model.fetchOnAirSeries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getSubscription());
    }

    @Override
    public void onSeriesSelected(int id) {
    }

    @Override
    public void unsuscribeFromObservable() {
        if(subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }

//    Memory leak protection
    private Subscriber getSubscription() {
        if (this.subscription == null)
            this.subscription = new Subscriber<List<Series>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    if (MainFragmentPresenter.this.view != null)
                        MainFragmentPresenter.this.view.onError(e.getLocalizedMessage());
                }

                @Override
                public void onNext(List<Series> series) {
                    if (MainFragmentPresenter.this.view != null)
                        MainFragmentPresenter.this.view.onTVSeriesReceived(series);
                }
            };

        return (Subscriber) this.subscription;
    }
}

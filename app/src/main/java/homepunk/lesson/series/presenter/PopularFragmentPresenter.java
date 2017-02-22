package homepunk.lesson.series.presenter;

import java.util.List;

import homepunk.lesson.series.interfaces.Model;
import homepunk.lesson.series.interfaces.Presenter;
import homepunk.lesson.series.interfaces.View;
import homepunk.lesson.series.model.Series;
import homepunk.lesson.series.utils.RxUtils;
import rx.Subscriber;
import rx.Subscription;

public class PopularFragmentPresenter implements Presenter.PopularFragmentPresenter {
    private final Model.DataManagerModel model;
    private View.PopularFragmentView view;
    private Subscription subscription;

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
                .compose(RxUtils.applySchedulers())
                .subscribe(getSubscription());
    }

    @Override
    public void unsuscribeFromObservable() {
        if(subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }

    public Subscriber getSubscription() {
        if (this.subscription == null)
           this.subscription = new Subscriber<List<Series>>() {
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
            };

        return (Subscriber) subscription;
    }
}

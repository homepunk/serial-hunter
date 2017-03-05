package homepunk.lesson.series.presenter;

import java.util.List;

import homepunk.lesson.series.interfaces.Model;
import homepunk.lesson.series.interfaces.Presenter;
import homepunk.lesson.series.interfaces.View;
import homepunk.lesson.series.model.Series;
import homepunk.lesson.series.utils.RxUtils;
import rx.Subscriber;
import rx.Subscription;

public class PopularViewPresenter implements Presenter.PopularPresenter {
    private final Model.DataManagerModel model;
    private View.PopularFragmentView view;
    private Subscription subscription;

    public PopularViewPresenter(Model.DataManagerModel maodel) {
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
        if(RxUtils.isSubsribed(subscription))
            subscription.unsubscribe();
    }

    public Subscriber getSubscription() {
        if (!RxUtils.isSubsribed(subscription))
           this.subscription = new Subscriber<List<Series>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    if(PopularViewPresenter.this.view != null)
                        PopularViewPresenter.this.view.onError(e.getLocalizedMessage());
                }

                @Override
                public void onNext(List<Series> series) {
                    if(PopularViewPresenter.this.view != null)
                        PopularViewPresenter.this.view.onResult(series);
                }
            };

        return (Subscriber) subscription;
    }
}

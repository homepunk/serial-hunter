package homepunk.lesson.series.presenter;

import homepunk.lesson.series.interfaces.Model;
import homepunk.lesson.series.interfaces.Presenter;
import homepunk.lesson.series.interfaces.View;
import homepunk.lesson.series.model.Series;
import homepunk.lesson.series.utils.RxUtils;
import rx.Subscriber;
import rx.Subscription;

import static homepunk.lesson.series.utils.RxUtils.isSubsribed;

public class DetailedViewPresenter implements Presenter.DetailedPresenter {

    private View.DetailedFragmentView view;
    private final Model.DataManager model;
    private Subscription subscription;

    public DetailedViewPresenter(Model.DataManager model) {
        this.model = model;
    }

    @Override
    public void setView(View.DetailedFragmentView view) {
        this.view = view;
    }

    @Override
    public void getDetailedDescription(int id) {
        model.fetchDetailedDescriptionById(id)
                .compose(RxUtils.applySchedulers())
                .subscribe(getSubscription());
    }

    @Override
    public void unsuscribeFromObservable() {
        if(isSubsribed(subscription))
            subscription.unsubscribe();
    }

    public Subscriber getSubscription() {
       if(!isSubsribed(subscription))
           this.subscription = new Subscriber<Series>() {
               @Override
               public void onCompleted() {

               }

               @Override
               public void onError(Throwable e) {
                   if(DetailedViewPresenter.this.view != null)
                       DetailedViewPresenter.this.view.onError(e.getLocalizedMessage());
               }

               @Override
               public void onNext(Series series) {
                   if(DetailedViewPresenter.this.view != null)
                       DetailedViewPresenter.this.view.onResult(series);
               }
           };

        return (Subscriber) subscription;
    }
}


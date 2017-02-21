package homepunk.lesson.series.presenter;

import homepunk.lesson.series.interfaces.Model;
import homepunk.lesson.series.interfaces.Presenter;
import homepunk.lesson.series.interfaces.View;
import homepunk.lesson.series.model.Series;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DetailedFragmentPresenter implements Presenter.DetailedFragmentPresenter {

    private View.DetailedFragmentView view;
    private final Model.DataManagerModel model;
    private Subscription subscription;

    public DetailedFragmentPresenter(Model.DataManagerModel model) {
        this.model = model;
    }

    @Override
    public void setView(View.DetailedFragmentView view) {
        this.view = view;
    }

    @Override
    public void getDetailedDescription(int id) {
        model.fetchDetailedDescriptionById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getSubscription());
    }

    @Override
    public void unsuscribeFromObservable() {
        if(subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }

    public Subscriber getSubscription() {
       if(this.subscription == null)
           this.subscription = new Subscriber<Series>() {
               @Override
               public void onCompleted() {

               }

               @Override
               public void onError(Throwable e) {
                   if(DetailedFragmentPresenter.this.view != null)
                       DetailedFragmentPresenter.this.view.onError(e.getLocalizedMessage());
               }

               @Override
               public void onNext(Series series) {
                   if(DetailedFragmentPresenter.this.view != null)
                       DetailedFragmentPresenter.this.view.onDetailedDescriptionRecieved(series);
               }
           };

        return (Subscriber) subscription;
    }
}


package homepunk.lesson.series.presenter;

import homepunk.lesson.series.interfaces.Model;
import homepunk.lesson.series.interfaces.Presenter;
import homepunk.lesson.series.interfaces.View;
import homepunk.lesson.series.model.HdrezkaSeries;
import homepunk.lesson.series.model.Series;
import homepunk.lesson.series.utils.RxUtils;
import rx.Subscriber;
import rx.Subscription;
import timber.log.Timber;

public class UpdatesPresenter implements Presenter.UpdatesPresenter {
    private View.UpdatesView view;
    private Model.HdrezkaParseManager parseManager;
    private Model.DataManager dataManager;
    private Subscription subscription;

    public UpdatesPresenter(Model.HdrezkaParseManager parseManager, Model.DataManager dataManager) {
        this.parseManager = parseManager;
        this.dataManager = dataManager;
    }

    @Override
    public void setView(View.UpdatesView view) {
        this.view = view;
    }

    @Override
    public void getUpdates() {
        if (parseManager != null) {
            parseManager.fetchUpdates()
                    .compose(RxUtils.applySchedulers())
                    .subscribe(getSubscription());
        }
    }

    @Override
    public void unsuscribeFromObservable() {
        if(RxUtils.isSubsribed(subscription))
            subscription.unsubscribe();
    }

    private Subscriber getSubscription(){
        if (!RxUtils.isSubsribed(subscription))
            this.subscription = new Subscriber<HdrezkaSeries>() {

                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    if (view != null)
                        view.onError(e.getLocalizedMessage());
                }

                @Override
                public void onNext(HdrezkaSeries hdrezkaSeries) {
                    Timber.i("Recieved " + hdrezkaSeries.getMainTitle());
                    dataManager.fetchSeriesByTitle(hdrezkaSeries.getMainTitle())
                            .compose(RxUtils.applySchedulers())
                            .subscribe(new Subscriber<Series>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {
                                    view.onError(e.getLocalizedMessage());
                                }

                                @Override
                                public void onNext(Series series) {
                                    view.onResult(series, hdrezkaSeries);
                                }
                            });
                }
            };

        return (Subscriber) this.subscription;
    }

}

package homepunk.lesson.series.data.database;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import homepunk.lesson.series.App;
import homepunk.lesson.series.model.Series;
import rx.Observable;
import timber.log.Timber;

public class RealmRepository {
    @Inject
    RealmService realmService;

    public RealmRepository(Context context) {
        App.getAppComponent(context).plus(this);
    }

    public Observable<List<Series>> getOnAirSeries() {
        return Observable.defer(() ->
                Observable.create(subscriber -> {
                    List<Series> series = realmService.getOnAirSeries();
                    if (series == null || series.size() == 0)
                        Observable.empty();
                    else {
                        subscriber.onNext(series);
                        Timber.i("Cached series are from database");
                    }
                    subscriber.onCompleted();
                }));
    }

    public void saveOnAirSeries(List<Series> series) {
        realmService.saveOnAirSeries(series);
    }
}

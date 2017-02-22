package homepunk.lesson.series.data.database;

import android.content.Context;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import homepunk.lesson.series.App;
import homepunk.lesson.series.model.Series;
import rx.Observable;
import rx.Subscriber;

public class DbFlowRepository {
    @Inject
    DbFlowService dbService;

    public static final String LOG_TAG = DbFlowRepository.class.getCanonicalName();

    public DbFlowRepository(Context context) {
        App.getAppComponent(context).plus(this);
    }

    public void saveAll(List<Series> series) {
        if (dbService.isAlreadyInDatabase(series))
            Log.d(LOG_TAG, "List already in database");
        else
            dbService.saveAll(series);
    }

    public Observable<List<Series>> getAll() {
        List<Series> dbSeries = dbService.getAll();

        if (dbSeries == null || dbSeries.size() == 0) {
            return Observable.empty();
        } else {
            return Observable.create(new Observable.OnSubscribe<List<Series>>() {
                @Override
                public void call(Subscriber<? super List<Series>> subscriber) {
                    try {
                        subscriber.onCompleted();
                        subscriber.onNext(dbSeries);
                        Log.d(LOG_TAG, "Creating new observable with existing series in db");
                    } catch (Exception e) {
                        subscriber.onError(e);
                    }
                }
            });
        }
    }
}

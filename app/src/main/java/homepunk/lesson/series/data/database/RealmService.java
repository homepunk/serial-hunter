package homepunk.lesson.series.data.database;

import java.util.List;

import homepunk.lesson.series.model.Series;
import io.realm.Realm;
import io.realm.RealmResults;
import timber.log.Timber;

public class RealmService {
    public void saveOnAirSeries(List<Series> seriesList) {
        Realm defaultInstance = Realm.getDefaultInstance();

        defaultInstance.executeTransaction(realm -> {
            for (int i = 0; i < seriesList.size(); i++) {
                Series series = seriesList.get(i);
                Series seriesDb = realm.createObject(Series.class);
                seriesDb.setId(series.getId());
                seriesDb.setTitle(series.getTitle());
                seriesDb.setPosterPath(series.getPosterPath());
                seriesDb.setViewType(series.getViewType());
                Timber.i("Series " + series.getTitle() + " saved succesfully");
            }
            Timber.i(seriesList.size() + " series saved");
        });
        defaultInstance.close();
    }

    public List<Series> getOnAirSeries() {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        final RealmResults<Series> realmResults = realm.where(Series.class)
                .findAll();
        final List<Series> seriesList = realm.copyFromRealm(realmResults);
        Timber.i("Database has " + realmResults.size() + "series");
        realm.commitTransaction();
        realm.close();

        return seriesList;
    }
}

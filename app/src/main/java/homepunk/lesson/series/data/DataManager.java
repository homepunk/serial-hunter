package homepunk.lesson.series.data;

import java.util.List;

import homepunk.lesson.series.data.database.RealmRepository;
import homepunk.lesson.series.data.rest.TmdbRepository;
import homepunk.lesson.series.model.Series;
import rx.Observable;

import static homepunk.lesson.series.interfaces.Model.DataManagerModel;

public class DataManager implements DataManagerModel {
    public static final String LOG_TAG = DataManager.class.getCanonicalName();
    private TmdbRepository rest;
    private RealmRepository db;

    public DataManager(TmdbRepository rest, RealmRepository db) {
        this.rest = rest;
        this.db = db;
    }

    @Override
    public Observable<List<Series>> fetchOnAirSeries() {
        return Observable.defer(() -> /*db.getOnAirSeries()
                        .switchIfEmpty(*/rest.fetchOnAirSeries()/*
                        .doOnNext(series -> db.saveOnAirSeries(series))*/);
    }

    @Override
    public Observable<List<Series>> fetchPopularSeries() {
        return rest.fetchPopularSeries();
    }

    @Override
    public Observable<List<Series>> fetchSearchResults(String searchQuery) {
        return rest.fetchSearchResults(searchQuery);
    }

    @Override
    public Observable<Series> fetchDetailedDescriptionById(int id) {
        return Observable.defer(() -> rest.fetchDetailedDescriptionById(id));
    }
}

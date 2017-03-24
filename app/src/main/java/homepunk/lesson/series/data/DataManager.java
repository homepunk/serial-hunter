package homepunk.lesson.series.data;

import java.util.List;

import homepunk.lesson.series.data.database.RealmRepository;
import homepunk.lesson.series.data.rest.TmdbRepository;
import homepunk.lesson.series.interfaces.Model;
import homepunk.lesson.series.model.Series;
import rx.Observable;

public class DataManager implements Model.DataManager {
    public static final String LOG_TAG = DataManager.class.getCanonicalName();
    private TmdbRepository tmdbRepository;
    private RealmRepository db;

    public DataManager(TmdbRepository rest, RealmRepository db) {
        this.tmdbRepository = rest;
        this.db = db;
    }

    @Override
    public Observable<List<Series>> fetchOnAirSeries() {
        return Observable.defer(() -> /*db.getOnAirSeries()
                        .switchIfEmpty(*/tmdbRepository.fetchOnAirSeries()/*
                        .doOnNext(series -> db.saveOnAirSeries(series))*/);
    }

    @Override
    public Observable<List<Series>> fetchPopularSeries() {
        return tmdbRepository.fetchPopularSeries();
    }

    @Override
    public Observable<List<Series>> fetchSearchResults(String searchQuery) {
        return tmdbRepository.fetchSearchResults(searchQuery);
    }

    @Override
    public Observable<Series> fetchDetailedDescriptionById(int id) {
        return Observable.defer(() -> tmdbRepository.fetchDetailedDescriptionById(id));
    }

    @Override
    public Observable<Series> fetchSeriesByTitle(String title) {
        return Observable.defer(() -> tmdbRepository.fetchSeriesByTitle(title));
    }
}

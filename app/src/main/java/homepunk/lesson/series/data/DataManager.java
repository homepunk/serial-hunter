package homepunk.lesson.series.data;

import java.util.List;

import homepunk.lesson.series.data.database.DbFlowRepository;
import homepunk.lesson.series.data.rest.RetrofitRepository;
import homepunk.lesson.series.model.Series;
import rx.Observable;

import static homepunk.lesson.series.interfaces.Model.DataManagerModel;

public class DataManager implements DataManagerModel {
    public static final String LOG_TAG = DataManager.class.getCanonicalName();
    private RetrofitRepository rest;
    private DbFlowRepository db;

    public DataManager(RetrofitRepository rest, DbFlowRepository db) {
        this.rest = rest;
        this.db = db;
    }

    @Override
    public Observable<List<Series>> fetchOnAirSeries() {
        return db.getAll()
                .switchIfEmpty(rest.fetchOnAirSeries()
                        .doOnNext(series -> {
                            if (series != null)
                                db.saveAll(series);
                        }));
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
        return rest.fetchDetailedDescriptionById(id);
    }


}

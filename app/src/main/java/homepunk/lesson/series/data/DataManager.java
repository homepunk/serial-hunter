package homepunk.lesson.series.data;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import homepunk.lesson.series.App;
import homepunk.lesson.series.data.database.DbFlowService;
import homepunk.lesson.series.data.rest.RetrofitRepository;
import homepunk.lesson.series.model.Series;
import rx.Observable;

import static homepunk.lesson.series.interfaces.Model.DataManagerModel;

public class DataManager implements DataManagerModel {
    @Inject
    DbFlowService dbService;
    @Inject
    RetrofitRepository rest;

    public DataManager(Context context) {
        App.getAppComponent(context).plus(this);
    }

    @Override
    public Observable<List<Series>> fetchOnAirSeries() {
        return rest.fetchOnAirSeries().doOnNext(series -> dbService.saveAll(series));
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

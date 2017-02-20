package homepunk.lesson.series.data.rest;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import homepunk.lesson.series.App;
import homepunk.lesson.series.model.Series;
import rx.Observable;

import static homepunk.lesson.series.data.Constants.KEY_API;
import static homepunk.lesson.series.data.Constants.LANGUAGE_EN;
import static homepunk.lesson.series.data.Constants.WITHOUT_EXPOSE;
import static homepunk.lesson.series.data.Constants.WITH_EXPOSE;

public class RetrofitRepository {
    @Inject
    @Named(WITHOUT_EXPOSE)
    RetrofitService seriesService;
    @Inject
    @Named(WITH_EXPOSE)
    RetrofitService listService;

    public RetrofitRepository(Context context) {
        App.getAppComponent(context).plus(this);
    }

    public Observable<List<Series>> fetchOnAirSeries() {
        return listService.loadOnAirSeries(1, LANGUAGE_EN, KEY_API)
                .map(page -> page.setViewType(Series.GRID_TYPE));
    }

    public Observable<List<Series>> fetchPopularSeries() {
        return listService.loadPopularSeries(1, LANGUAGE_EN, KEY_API)
                .map(page -> page.setViewType(Series.GRID_TYPE));
    }

    public Observable<List<Series>> fetchSearchResults(String searchQuery) {
        return listService.loadSearchResults(1, searchQuery, LANGUAGE_EN, KEY_API)
                .map(page -> page.setViewType(Series.BACKDROP_TYPE));
    }

    public Observable<Series> fetchDetailedDescriptionById(int id) {
        return seriesService.loadTVSeriesDetails(id, LANGUAGE_EN, KEY_API);

    }
}

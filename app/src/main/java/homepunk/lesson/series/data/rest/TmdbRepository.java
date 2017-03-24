package homepunk.lesson.series.data.rest;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import homepunk.lesson.series.App;
import homepunk.lesson.series.model.Series;
import rx.Observable;
import rx.functions.Func1;

import static homepunk.lesson.series.data.Constants.KEY_API;
import static homepunk.lesson.series.data.Constants.LANGUAGE_EN;
import static homepunk.lesson.series.data.Constants.LANGUAGE_RU;
import static homepunk.lesson.series.data.Constants.PAGE_ONE;
import static homepunk.lesson.series.model.Series.BACKDROP_TYPE;
import static homepunk.lesson.series.model.Series.GRID_TYPE;
import static homepunk.lesson.series.utils.RxUtils.getSeriesWithViewType;

public class TmdbRepository {
    @Inject
    TmdbApi tmdbApi;

    public TmdbRepository(Context context) {
        App.getAppComponent(context).plus(this);
    }

    public Observable<List<Series>> fetchOnAirSeries() {
        return tmdbApi.loadOnAirSeries(PAGE_ONE, LANGUAGE_EN, KEY_API)
                .compose(getSeriesWithViewType(GRID_TYPE));
    }

    public Observable<List<Series>> fetchPopularSeries() {
        return tmdbApi.loadPopularSeries(PAGE_ONE, LANGUAGE_EN, KEY_API)
                .compose(getSeriesWithViewType(BACKDROP_TYPE));
    }

    public Observable<List<Series>> fetchSearchResults(String query) {
        return tmdbApi.loadSearchResults(PAGE_ONE, query, LANGUAGE_RU, KEY_API)
                .compose(getSeriesWithViewType(GRID_TYPE));
    }

    public Observable<Series> fetchDetailedDescriptionById(int id) {
        return tmdbApi.loadTVSeriesDetails(id, LANGUAGE_EN, KEY_API);
    }

    public Observable<Series> fetchSeriesByTitle(String query) {
        return tmdbApi.loadSearchResults(PAGE_ONE, query, LANGUAGE_RU, KEY_API)
                .compose(getSeriesWithViewType(BACKDROP_TYPE))
                .flatMap(new Func1<List<Series>, Observable<Series>>() {
                    @Override
                    public Observable<Series> call(List<Series> seriesList) {
                        return Observable.just(seriesList.get(0));
                    }
                });

    }
}

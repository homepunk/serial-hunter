package homepunk.lesson.series.interfaces;

import java.util.List;

import homepunk.lesson.series.model.HdrezkaSeries;
import homepunk.lesson.series.model.Series;
import rx.Observable;

public interface Model {

    interface DataManager {
        Observable<List<Series>> fetchOnAirSeries();

        Observable<List<Series>> fetchPopularSeries();

        Observable<List<Series>> fetchSearchResults(String searchQuery);

        Observable<Series> fetchDetailedDescriptionById(int id);

        Observable<Series> fetchSeriesByTitle(String title);
    }

    interface HdrezkaParseManager {
        Observable<HdrezkaSeries> fetchUpdates();
    }
}

package homepunk.lesson.series.interfaces;

import java.io.IOException;
import java.util.List;

import homepunk.lesson.series.model.Series;
import rx.Observable;

public interface Model {

    interface DataManagerModel {
        Observable<List<Series>> fetchOnAirSeries();

        Observable<List<Series>> fetchPopularSeries();

        Observable<List<Series>> fetchSearchResults(String searchQuery);

        Observable<Series> fetchDetailedDescriptionById(int id);
    }

    interface SeriesDAO<T>{
        long save(T t) throws IOException;
        boolean delete(T t);
        T get(int id);
        List<T> getAll();
        boolean clear();
        void saveAll(List<T> series);
        boolean isAlreadyInDatabase(List<Series> series);
    }
}

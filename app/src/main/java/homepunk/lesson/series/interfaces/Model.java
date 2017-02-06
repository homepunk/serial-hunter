package homepunk.lesson.series.interfaces;

import java.io.IOException;
import java.util.List;

import homepunk.lesson.series.model.Series;

import static homepunk.lesson.series.interfaces.Listeners.*;

public interface Model {

    interface DataManagerModel {
        void fetchOnAirSeries(final RetrofitListener<List<Series>> listener);

        void fetchSeriesById(int id, final RetrofitListener<Series> listener);
    }

    interface SeriesDAO<T>{
        long save(T t) throws IOException;
        boolean delete(T t);
        T get(int id);
        List<T> getAll();

        void saveAll(List<T> series);
    }
}

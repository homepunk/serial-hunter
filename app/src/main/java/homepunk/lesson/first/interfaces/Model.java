package homepunk.lesson.first.interfaces;

import java.io.IOException;
import java.util.List;

import static homepunk.lesson.first.interfaces.Listeners.*;

public interface Model {

    interface DataRepositoryModel {
        void fetchOnAirSeries(final RetrofitListListener listener);

        void fetchSeriesById(int id, final RetrofitListener listener);
    }

    interface SeriesDAO<T>{
        long save(T t) throws IOException;
        boolean delete(T t);
        T get(int id);
        List<T> getAll();

        void saveAll(List<T> series);
    }
}

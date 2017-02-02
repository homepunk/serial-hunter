package homepunk.lesson.first.interfaces;

import java.io.IOException;
import java.util.List;

public interface Model {

    interface TVSeriesModel{
        void fetchSeriesList(Listeners.ListListener resultListener);

        void fetchSeriesById(int id, Listeners.Listener resultListener);
    }

    interface SeriesDAO<T>{
        long save(T t) throws IOException;
        boolean delete(T t);
        T get(int id);
        List<T> getAll();

        void saveAll(List<T> series);
    }

    interface Observerable {
        void registerObserver(Presenter.Observer listener);

        void notifyObservers();
    }
}

package homepunk.lesson.first.interfaces;

import java.io.IOException;
import java.util.List;

import homepunk.lesson.first.adapter.TVSeriesAdapter;
import homepunk.lesson.first.model.TVSeries;

public interface Model {

    interface TVObjectFetchrModel{
        void openHttpConnection();

        void setExecuteRef(String ref);
    }

    interface TVListFetchrModel extends TVObjectFetchrModel{
        void setTVList(List<TVSeries> tvList);

        void clearResults();

        void setAdapter(TVSeriesAdapter adapter);
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

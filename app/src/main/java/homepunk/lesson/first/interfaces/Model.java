package homepunk.lesson.first.interfaces;

import android.content.Context;

import java.io.IOException;
import java.util.List;

import homepunk.lesson.first.adapter.TVSeriesAdapter;
import homepunk.lesson.first.model.TVSeries;

public interface Model {

    interface TVSeriesModel{
        void fetchTVSeriesList(Listener resultListener);

        void setSelectedTVSeriesId(int id);

        TVSeries fetchTVSeries(Listener resultListener);
    }

    interface TVObjectFetchrModel{
        void fetch();

        void setExecuteRef(String ref);
    }

    interface TVListFetchrModel {
        void setTVList(List<TVSeries> tvList);

        void setExecuteRef(String ref);

        void setAdapter(TVSeriesAdapter adapter);

        void setPresenter(Presenter presenter);

        void setContext(Context context);

        void clearResults();

        void fetch();
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

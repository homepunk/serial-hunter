package homepunk.lesson.first.model;

import java.util.List;

import homepunk.lesson.first.adapter.TVListAdapter;

public interface Model {
    interface TVFetchrAsyncModel {
        void setTVList(List<TVSeries> tvList);

        void setAdapter(TVListAdapter adapter);

        void makeHttpConnection();

        void execute(String ref);
    }
}

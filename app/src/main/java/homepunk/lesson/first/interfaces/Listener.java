package homepunk.lesson.first.interfaces;

import java.util.List;

import homepunk.lesson.first.model.TVSeries;

public interface Listener {
        void onResult(List<TVSeries> tvSeries);

        void onError(Exception e);
}

package homepunk.lesson.first.interfaces;

import java.util.List;

import homepunk.lesson.first.model.TVSeries;

public interface Listeners {
        interface ListListener {
                void onResult(List<TVSeries> seriesList);

                void onError(Exception e);
        }

        interface Listener {
                void onResult(TVSeries series);

                void onError(Exception e);
        }
}

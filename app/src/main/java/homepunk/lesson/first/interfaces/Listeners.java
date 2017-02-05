package homepunk.lesson.first.interfaces;

import java.util.List;

import homepunk.lesson.first.model.Series;

public interface Listeners {
        interface RetrofitListListener {
                void onResult(List<Series> onAirList);

                void onError(String e);
        }

        interface RetrofitListener {
                void onResult(Series series);

                void onError(String e);
        }
}

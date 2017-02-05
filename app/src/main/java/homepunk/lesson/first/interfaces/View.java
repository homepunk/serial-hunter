package homepunk.lesson.first.interfaces;

import java.util.List;

import homepunk.lesson.first.model.Series;

public interface View {
    interface MainActivityView {
    }

    interface MainFragmentView {
        void onTVSeriesReceived(List<Series> tvSeries);

        void onError(String error);
    }

    interface SearchFragmentView {
        void onRecommendedSeriesRecieved(List<Series> tvSeries);

        void onError(String error);
    }

    interface DetailedFragmentView {
        void onSeriesDescRecieved(Series tvSeries);

        void onError(String error);
    }

}

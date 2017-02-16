package homepunk.lesson.series.interfaces;

import java.util.List;

import homepunk.lesson.series.model.Series;

public interface View {
    interface MainActivityView {
    }

    interface MainFragmentView {
        void onTVSeriesReceived(List<Series> seriesList);

        void onError(String error);
    }

    interface SearchFragmentView {
        void onRecommendedSeriesRecieved(List<Series> seriesList);

        void onSearchResultsRecieved(List<Series> seriesList);

        void onError(String error);
    }

    interface DetailedFragmentView {
        void onSeriesDescRecieved(Series series);

        void onError(String error);
    }

    interface HotUpdatesFragmentView {
        void onTopRatedRecieved(List<Series> seriesList);

        void onError(String error);
    }

}

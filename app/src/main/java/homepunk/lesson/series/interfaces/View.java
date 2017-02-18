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
        void onDetailedDescriptionRecieved(Series series);

        void onError(String error);
    }

    interface PopularFragmentView {
        void onPopularSeriesRecieved(List<Series> seriesList);

        void onError(String error);
    }

}

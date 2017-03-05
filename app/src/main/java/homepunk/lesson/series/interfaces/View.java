package homepunk.lesson.series.interfaces;

import java.util.List;

import homepunk.lesson.series.model.Series;

public interface View {
    interface MainActivityView {
    }

    interface MainFragmentView {
        void onResult(List<Series> seriesList);

        void onError(String error);
    }

    interface SearchFragmentView {
        void onResult(List<Series> seriesList);

        void onSearchResult(List<Series> seriesList);

        void onError(String error);
    }

    interface DetailedFragmentView {
        void onResult(Series series);

        void onError(String error);
    }

    interface PopularFragmentView {
        void onResult(List<Series> seriesList);

        void onError(String error);
    }

}

package homepunk.lesson.first.interfaces;

import java.util.List;

import homepunk.lesson.first.model.TVSeries;

public interface View {
    interface MainActivityView {
    }

    interface MainFragmentView {
        void onTVSeriesReceived(List<TVSeries> tvSeries);

        void onError(String error);
    }

    interface SearchFragmentView {
        void onRecommendedSeriesRecieved(List<TVSeries> tvSeries);

        void onError(String error);
    }

    interface DetailedFragmentView {
        void onSeriesDescRecieved(TVSeries tvSeries);

        void onError(String error);
    }

}

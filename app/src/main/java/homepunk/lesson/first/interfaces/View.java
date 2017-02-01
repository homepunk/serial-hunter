package homepunk.lesson.first.interfaces;

import android.widget.RelativeLayout;

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
        void  onDescriptionRecieved(TVSeries tvSeries);

        void onError(String error);

        int getFromBundle();

        void setOverview(String o);

        void setPosterImage(String path);

        int getFabMarginTop();

        int getFabMarginLeft();

        int getDisplayContentHeight();

        RelativeLayout.LayoutParams getLayoutParams();
    }

}

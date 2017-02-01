package homepunk.lesson.first.interfaces;

import android.widget.RelativeLayout;

import java.util.List;

import homepunk.lesson.first.model.TVSeries;
import homepunk.lesson.first.view.detailed.DetailedFragment;

public interface View {
    interface MainActivityView {
    }

    interface MainFragmentView {
        void onTVSeriesReceived(List<TVSeries> tvSeries);

        void onError(String error);
    }

    interface DetailedActivityView{
        DetailedFragment newInstance(int id);

        int getFromIntent();
    }

    interface DetailedFragmentView{

        int getFromBundle();

        void setOverview(String o);

        void setPosterImage(String path);

        int getFabMarginTop();

        int getFabMarginLeft();

        int getDisplayContentHeight();

        RelativeLayout.LayoutParams getLayoutParams();
    }

}

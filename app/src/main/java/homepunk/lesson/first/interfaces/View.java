package homepunk.lesson.first.interfaces;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.widget.RelativeLayout;

import homepunk.lesson.first.view.detailed.DetailedFragment;

public interface View {
    Context getContext();

    Resources getResources();

    interface MainActivityView {
        boolean isSpinnerVisible();

        void setSpinnerVisibility(boolean visibility);

        void beginTransaction(Fragment fragment);
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

package homepunk.lesson.first.interfaces;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import homepunk.lesson.first.view.detailed.DetailedFragment;

public interface View {
    Context getContext();

    Resources getResources();

    interface MainActivityView {
        void addSpinnerView(Spinner spinner);

        void setSpinnerVisibility(boolean visibility);

        boolean isSpinnerVisible();

        void setSpinnerDropDownWidth(int width);

        void setSpinnerLayoutParams(ViewGroup.LayoutParams params);

        void setSpinnerAdapater(SpinnerAdapter adapter);

        void setSpinnerPrompt(String title);

        void setSpinnerSelection(int position);

        void setSpinnerArrowColor(int color);

        void setBottomBarBackgroundColor(int color);

        void setDefaultTab(int id);



    }

    interface MainFragmentView extends View{
        RecyclerView getRecycleView();
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

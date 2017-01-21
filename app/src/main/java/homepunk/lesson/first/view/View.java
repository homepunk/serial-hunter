package homepunk.lesson.first.view;

import android.content.Context;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import homepunk.lesson.first.view.detailed.DetailedActivityFragment;

public interface View {

    DetailedActivityFragment newInstance(int id);

    int getFromIntent();


    interface MainActivityView {

        Toolbar getToolbar();

        DrawerLayout getDrawerLayout();

        NavigationView getNavigationView();

        BottomNavigationView getBottomNavigationView();

        Spinner getSpinnerView();
    }

    interface MainFragmentView {
        Context getContext();

        RecyclerView getRecycleView();
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

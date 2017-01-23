package homepunk.lesson.first.view;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.roughike.bottombar.BottomBar;

import homepunk.lesson.first.presenter.main.SpinnerPresenter;
import homepunk.lesson.first.view.detailed.DetailedFragment;

public interface View {

    DetailedFragment newInstance(int id);

    int getFromIntent();


    interface MainActivityView {

        Toolbar getToolbar();

        Spinner getSpinnerView();

        SpinnerPresenter getSpinnerPresenter();

        BottomBar getBoottomBar();

        DrawerLayout getDrawerLayout();

        NavigationView getNavigationView();

        void addSpinner(Spinner view);
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

    interface SearchFragmentView{
        RecyclerView getSearchRecycleView();

        RecyclerView getRecommendRecycleView();
    }
}

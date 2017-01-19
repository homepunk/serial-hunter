package homepunk.lesson.first.view;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;

import homepunk.lesson.first.presenter.main.NavDrawerPresenter;
import homepunk.lesson.first.presenter.main.TabsPresenter;
import homepunk.lesson.first.view.detailed.DetailedActivityFragment;

public interface View {

    DetailedActivityFragment newInstance(int id);

    int getFromIntent();


    interface MainActivityView {
        NavDrawerPresenter drawerPresenter = null;
        TabsPresenter tabsPresenter = null;

        Toolbar getToolbar();

        DrawerLayout getDrawerLayout();

        NavigationView getNavigationView();

        ViewPager getViewPager();

        TabLayout getTabs();
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

package homepunk.lesson.first.view;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import homepunk.lesson.first.presenter.main.NavDrawerPresenter;
import homepunk.lesson.first.presenter.main.TabsPresenter;

public interface View {

    Activity getActivity();

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
}

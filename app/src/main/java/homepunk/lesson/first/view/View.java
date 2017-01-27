package homepunk.lesson.first.view;

import android.content.Context;
import android.content.res.Resources;
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
    Context getContext();

    Resources getResources();

    interface MainActivityView {

        Toolbar getToolbar();

        Spinner getSpinnerView();

        BottomBar getBoottomBar();

        DrawerLayout getDrawerLayout();

        NavigationView getNavigationView();

        SpinnerPresenter getSpinnerPresenter();

        void addSpinner(Spinner view);
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

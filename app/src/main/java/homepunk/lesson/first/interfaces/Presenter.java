package homepunk.lesson.first.interfaces;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.roughike.bottombar.BottomBar;

import homepunk.lesson.first.model.TVSeries;
import homepunk.lesson.first.model.network.TVListFetchrModel;
import homepunk.lesson.first.view.main.MainActivity;

public interface Presenter {

    interface MainActivityPresenter{
        void setView(MainActivity view);

        void setBottomBar(BottomBar bottomBar);

        void setSpinner(android.widget.Spinner spinner);

        void setFragmentManager(FragmentManager fragmentManager);

        void onSpinnerItemClicked();

        void onTabSelected();
    }

    interface TVListPresetner {
        void setView(View view);

        void onTVSeriesClick();

        void refreshTVList();

        void updateViewWithSavedList();
    }

    void addView(ViewGroup view);

    void attachAllViews();

    void updateContent();

//    void openNetworkConnection();

    Context getContext();

    interface Search extends Presenter{
        void search(String newText);
    }

    interface NavDrawerPresenter {

        void setView(View.MainActivityView view);

        void setToolbar(Toolbar toolbar);

        void setNavigationView(NavigationView navigationView);

        void setDrawerLayout(DrawerLayout drawer);

        void createNavDrawer();

        boolean onNavigationItemSelected(MenuItem item);

        void onBackPressed();
    }

    interface RecycleView {
        void attachRecycleView(RecyclerView rv);

        void setLayoutManeger(RecyclerView.LayoutManager layoutManeger);

        void setItemsQuantity(int quantity);

        void updateContent(TVListFetchrModel fetchr);
    }

    interface DetailedActivity {
        void sendDataToFragment();
    }

    interface Fab {
        void setMainFabClickListener(FloatingActionButton fab);

        void setFabsClickListeners(FloatingActionButton fab, FloatingActionButton fab1,
                                   FloatingActionButton fab2);
        void loadFabAnimation();
    }

    interface CustomShadedView {
        void addView(RelativeLayout layout);

        int getMarginTop();

        int getMarginRight();
    }

    interface Observer{

        void update(TVSeries tvSeries);
    }

}

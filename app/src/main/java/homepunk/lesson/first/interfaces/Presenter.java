package homepunk.lesson.first.interfaces;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import java.util.List;

import homepunk.lesson.first.adapter.TVSeriesAdapter;
import homepunk.lesson.first.model.TVSeries;
import homepunk.lesson.first.view.main.MainFragment;
import homepunk.lesson.first.view.main.SearchFragment;

public interface Presenter {

    void setContext(Context context);

    interface MainActivityPresenter {
        void setView(View.MainActivityView view);

        void onSpinnerItemClicked();

        void onTabSelected(@IdRes int tabId);

        void onNavigationItemSelected(int id);
    }

    interface MainFragmentPresenter {
        void setView(MainFragment view);

        void setRecycleView(RecyclerView view);

        void getMostPopularTVSeries();
    }

    interface SearchFragmentPresenter{
        void setView(SearchFragment view);

        void setRecycleView(RecyclerView view);

        void getRecommendationTVSeries();

        void onSearchViewClicked();

    }

    interface TVListPresetner {
        void setView(View view);

        void onTVSeriesClick();

        void refreshTVList();

        void updateViewWithSavedList();
    }
//
//    void addView(ViewGroup view);
//
//    void attachAllViews();
//
//    void updateContent();
//
////    void openNetworkConnection();
//
//    Context getContext();

    interface Search extends Presenter {
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
        void setPresenter(Presenter presenter);

        void setContext(Context context);

        void setTVList(List<TVSeries> tvList);

        void setAdapter(TVSeriesAdapter adapter);

        void setUpRecycleView(RecyclerView rv);

        void setLayoutManeger(RecyclerView.LayoutManager layoutManeger);

        void setItemsQuantity(int quantity);

//        void updateContent(TVListFetchrModel fetchr);
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

    interface Observer {

        void update(TVSeries tvSeries);
    }

}

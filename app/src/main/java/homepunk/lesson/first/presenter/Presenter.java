package homepunk.lesson.first.presenter;

import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import homepunk.lesson.first.model.TVSeries;

public interface Presenter {

    interface NavDrawerPresenter {

        void attachNavDrawer();

        void onBackPressed();

        boolean onCreateOptionsMenu(Menu menu);

        public boolean onOptionsItemSelected(MenuItem item);

    }

    interface BottomNavigationTabPresenter {

        void attachBottomNavigattionBar();

        void setBackgroundColor(int color);
    }

    interface RecycleViewPresenter {
        void attachRecycleView();

        void attachPosters();
    }

    interface DetailedActivityPresenter {

        void sendDataToFragment();
    }

    interface DetailedFragmentPresenter {

        void startNetworkConnection();

        void setDetails();

        void update(TVSeries tvSeries);



    }

    interface FabPresenter {

        void setMainFabClickListener(FloatingActionButton fab);

        void setFabsClickListeners(FloatingActionButton fab, FloatingActionButton fab1,
                                   FloatingActionButton fab2);
        void loadFabAnimation();
    }

    interface CustomShadedPresenter {
        void addView(RelativeLayout layout);

        int getMarginTop();

        int getMarginRight();
    }

    interface SpinnerPresenter{
        void attachSpinner();

        void setSpinnerArrowColor(int color);
    }
}

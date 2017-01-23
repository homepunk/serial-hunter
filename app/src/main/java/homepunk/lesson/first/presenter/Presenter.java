package homepunk.lesson.first.presenter;

import android.support.design.widget.FloatingActionButton;
import android.widget.RelativeLayout;

import homepunk.lesson.first.model.TVSeries;

public interface Presenter {

    interface NavDrawerPresenter {

        void attachNavDrawer();

        void onBackPressed();

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

    interface SpinnerPresenter {
        void attachSpinner();

        void detachSpinner();

        boolean getSpinnerVisibility();

        void setSpinnerArrowColor(int color);

        void setSpinnerVisibility(boolean visibility);
    }

    interface SearchPresenter {
        void attachSearchRecycleView();

        void search(String newText);
    }

    interface RecommendtationsPresenter{
        void attachRecommendRecycleView();

        void setRecommendtations();
    }
}

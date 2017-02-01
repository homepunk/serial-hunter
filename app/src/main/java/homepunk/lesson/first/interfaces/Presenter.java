package homepunk.lesson.first.interfaces;

import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.widget.RelativeLayout;

import homepunk.lesson.first.model.TVSeries;

public interface Presenter {

    interface MainActivityPresenter {
        void setView(View.MainActivityView view);

        void onSpinnerItemClicked();

        Fragment onTabSelected(@IdRes int tabId);

        void onNavigationItemSelected(int id);
    }

    interface MainFragmentPresenter {
        void setView(View.MainFragmentView view);

        void getMostPopularTVSeries();

        void onTVSeriesSelected(int id);
    }

    interface SearchFragmentPresenter{
        void setView(View.SearchFragmentView view);

        void getRecommendedSeries();

        void onSearchViewClicked();

    }

    interface DetailedFragmentPresenter{
        void setView(View.DetailedFragmentView view);

        void getTVSeriesDescription();
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

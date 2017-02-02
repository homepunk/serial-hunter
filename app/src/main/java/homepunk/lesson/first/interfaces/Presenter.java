package homepunk.lesson.first.interfaces;

import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;

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

        void getMostPopularSeries();

        void onSeriesSelected(int id);
    }

    interface SearchFragmentPresenter{
        void setView(View.SearchFragmentView view);

        void getRecommendedSeries();

        void onSearchViewClicked();

    }

    interface DetailedFragmentPresenter{
        void setView(View.DetailedFragmentView view);

        void getSeriesDescriptionById(int id);
    }

    interface Fab {
        void setMainFabClickListener(FloatingActionButton fab);

        void setFabsClickListeners(FloatingActionButton fab, FloatingActionButton fab1,
                                   FloatingActionButton fab2);

        void loadFabAnimation();
    }

    interface Observer {

        void update(TVSeries tvSeries);
    }

}

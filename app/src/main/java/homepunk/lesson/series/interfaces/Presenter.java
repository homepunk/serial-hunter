package homepunk.lesson.series.interfaces;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

public interface Presenter {

    interface MainActivityPresenter {
        void setView(View.MainActivityView view);

        void onSpinnerItemClicked();

        Fragment onTabSelected(@IdRes int tabId);

        void onNavigationItemSelected(int id);
    }

    interface MainFragmentPresenter {
        void setView(View.MainFragmentView view);

        void getOnAirSeries();

        void onSeriesSelected(int id);

        void unsuscribeFromObservable();
    }

    interface SearchFragmentPresenter {
        void setView(View.SearchFragmentView view);

        void getSearchRecommendationResults();

        void getSearchResults(String searchString);

        void onSearchViewClicked();

        void unsuscribeFromObservable();
    }

    interface DetailedFragmentPresenter {
        void setView(View.DetailedFragmentView view);

        void getDetailedDescription(int id);

        void unsuscribeFromObservable();
    }

    interface PopularFragmentPresenter {
        void setView(View.PopularFragmentView view);

        void getPopularSeries();

        void unsuscribeFromObservable();
    }
}

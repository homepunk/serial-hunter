package homepunk.lesson.first.presenter;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import homepunk.lesson.first.model.network.TVListFetchrModel;
import homepunk.lesson.first.model.TVSeries;

public interface Presenter {
    void addView(ViewGroup view);

    void attachAllViews();

    void updateContent();

//    void openNetworkConnection();

    Context getContext();

    interface DetailedFragment extends Presenter{
        void setDetails();

        void update(TVSeries tvSeries);
    }

    interface Search extends Presenter{
        void search(String newText);
    }

    interface NavDrawer {
        void attachNavDrawer();

        void onBackPressed();
    }

    interface BottomNavigationTab {
        void attachBottomNavigattionBar();

        void setBackgroundColor(int color);
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

    interface CustomShaded {
        void addView(RelativeLayout layout);

        int getMarginTop();

        int getMarginRight();
    }

    interface Spinner {
        void attachSpinner();

        void detachSpinner();

        boolean getSpinnerVisibility();

        void setSpinnerArrowColor(int color);

        void setSpinnerVisibility(boolean visibility);
    }

}

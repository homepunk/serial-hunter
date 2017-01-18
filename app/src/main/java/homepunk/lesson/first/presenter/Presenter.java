package homepunk.lesson.first.presenter;

import android.view.Menu;
import android.view.MenuItem;

import homepunk.lesson.first.adapter.TVListAdapter;
import homepunk.lesson.first.ui.main.MainActivity;

public interface Presenter {

    interface NavDrawerPresenter {
        MainActivity view = null;

        void setUpNavDrawer();

        void onBackPressed();

        boolean onCreateOptionsMenu(Menu menu);

        public boolean onOptionsItemSelected(MenuItem item);

    }

    interface TabsPresenter {
        MainActivity view = null;

        void setUpTabs();

        void setUpTabIcons();
    }

    interface RecycleViewPresenter {
        void setUpRecycleView();

        void setUpPosters();

        TVListAdapter getAdapter();
    }

    interface TVFetchAsyncPresenter {
        void makeHttpConnection();

        void notifyDataChanged();

        void setAdapter(TVListAdapter adapter);
    }

}

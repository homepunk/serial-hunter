package homepunk.lesson.first.presenter.main;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.interfaces.Presenter;
import homepunk.lesson.first.interfaces.View;
import homepunk.lesson.first.ui.main.MainFragment;
import homepunk.lesson.first.ui.search.SearchFragment;

public class MainActivityPresenter implements Presenter.MainActivityPresenter {
    private View.MainActivityView view;
    private Fragment fragment;

    @Override
    public void setView(View.MainActivityView view) {
        this.view = view;
    }

    @Override
    public void onSpinnerItemClicked() {

    }

    @Override
    public Fragment onTabSelected(@IdRes int tabId) {
        switch (tabId) {
            case R.id.tab_hot_updates:
                fragment = new MainFragment();
                break;
            case R.id.tab_watchlist:
                fragment = new MainFragment();
                break;
            case R.id.tab_search:
                fragment = new SearchFragment();
                break;
        }
        return fragment;
    }

    @Override
    public void onNavigationItemSelected(int id) {
        if (id == R.id.nav_camera) {
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
    }
}

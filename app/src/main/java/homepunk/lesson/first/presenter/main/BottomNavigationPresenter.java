package homepunk.lesson.first.presenter.main;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.presenter.Presenter;
import homepunk.lesson.first.view.main.MainActivity;
import homepunk.lesson.first.view.main.MainFragment;
import homepunk.lesson.first.view.main.SearchFragment;

public class BottomNavigationPresenter implements Presenter.BottomNavigationTabPresenter {
    private MainActivity view;
    private Fragment fragment;
    private BottomBar bottomBar;
    private SpinnerPresenter spinnerView;
    private FragmentManager fragmentManager;

    public BottomNavigationPresenter(MainActivity view) {
        this.view = view;
        this.bottomBar = view.getBoottomBar();
        this.spinnerView = view.getSpinnerPresenter();
    }

    @Override
    public void attachBottomNavigattionBar() {
        fragmentManager = view.getSupportFragmentManager();
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_hot_updates:
                        if (spinnerView != null) {
                            if (!spinnerView.getSpinnerVisibility())
                                spinnerView.setSpinnerVisibility(true);
                        }
                        fragment = new MainFragment();
                        break;
                    case R.id.tab_watchlist:
                        if (spinnerView != null) {
                            if (!spinnerView.getSpinnerVisibility())
                                spinnerView.setSpinnerVisibility(true);
                        }
                        fragment = new MainFragment();
                        break;
                    case R.id.tab_search:
                        if(spinnerView != null) {
                            spinnerView.setSpinnerVisibility(false);

                        }
                        fragment = new SearchFragment();
                        break;
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.contentContainer, fragment).commit();
            }
        });
    }

    @Override
    public void setBackgroundColor(int color) {
        bottomBar.setBackgroundColor(color);
        bottomBar.setDrawingCacheBackgroundColor(color);
    }

    public void serDefaultTab(int id) {
        bottomBar.setDefaultTab(id);
    }
}

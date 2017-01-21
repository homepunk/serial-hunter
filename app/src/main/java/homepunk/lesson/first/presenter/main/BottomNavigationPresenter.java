package homepunk.lesson.first.presenter.main;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindString;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.presenter.Presenter;
import homepunk.lesson.first.view.detailed.DetailedActivityFragment;
import homepunk.lesson.first.view.main.MainActivity;
import homepunk.lesson.first.view.main.MainActivityFragment;

public class BottomNavigationPresenter implements Presenter.BottomNavigationTabPresenter {
    @BindString(R.string.tab_top) String tab_top;
    @BindString(R.string.tab_watch_later) String tab_watch_later;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private MainActivity view;
    private BottomBar bottomBar;

    public BottomNavigationPresenter(MainActivity view) {
        this.view = view;
        this.bottomBar = view.getBottomBar();
    }

    @Override
    public void attachBottomNavigattionBar() {
        fragmentManager = view.getSupportFragmentManager();
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId){
                    case R.id.action_hot_updates:
                        fragment = new MainActivityFragment();
                        break;
                    case R.id.action_watchlist:
                        fragment = new MainActivityFragment();
                        break;
                    case R.id.action_search:
                        fragment = new DetailedActivityFragment();
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

    public void serDefaultTab(int id){
        bottomBar.setDefaultTab(id);
    }
}

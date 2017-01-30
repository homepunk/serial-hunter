package homepunk.lesson.first.presenter.main;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.database.Constants;
import homepunk.lesson.first.interfaces.Presenter;
import homepunk.lesson.first.interfaces.LifecycleCallbacks;
import homepunk.lesson.first.view.main.MainActivity;
import homepunk.lesson.first.view.main.MainFragment;
import homepunk.lesson.first.view.main.SearchFragment;

public class MainActivityPresenter implements Presenter.MainActivityPresenter, LifecycleCallbacks {
    private MainActivity view;
    private BottomBar bottomBar;
    private Spinner spinner;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private ViewGroup.LayoutParams params;

    private void attachSpinner() {
        Display display = view.getWindowManager().getDefaultDisplay();
        int spinnerWidth = display.getWidth();
        view.setSpinnerDropDownWidth(spinnerWidth);
        view.setSpinnerLayoutParams(spinner.getLayoutParams());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view, R.layout.list_item_spinner, Constants.data);
        adapter.setDropDownViewResource(R.layout.list_item_spinner_dpordown);

        view.setSpinnerAdapater(adapter);
        view.setSpinnerPrompt("Title");
        view.setSpinnerSelection(2);
        view.setSpinnerArrowColor(R.color.colorText);
        view.setSpinnerVisibility(Constants.VISIBLE);
        view.addSpinnerView(spinner);
    }

    @Override
    public void setView(MainActivity view) {
        this.view = view;
    }

    @Override
    public void setBottomBar(BottomBar bottomBar) {
        this.bottomBar = bottomBar;
    }

    @Override
    public void setSpinner(Spinner spinner) {
        this.spinner = spinner;
    }

    @Override
    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onSpinnerItemClicked() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    @Override
    public void onTabSelected() {
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_hot_updates:
                        if (spinner != null) {
                            if (!view.isSpinnerVisible())
                                view.setSpinnerVisibility(Constants.VISIBLE);
                        }
                        fragment = new MainFragment();
                        break;
                    case R.id.tab_watchlist:
                        if (spinner != null) {
                            if (!view.isSpinnerVisible())
                                view.setSpinnerVisibility(Constants.VISIBLE);
                        }
                        fragment = new MainFragment();
                        break;
                    case R.id.tab_search:
                        if (spinner != null) {
                            view.setSpinnerVisibility(Constants.INVISIBLE);
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
    public void onStart() {
        view.setDefaultTab(R.id.tab_watchlist);
        view.setBottomBarBackgroundColor(R.color.colorAccent);
        attachSpinner();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }
}

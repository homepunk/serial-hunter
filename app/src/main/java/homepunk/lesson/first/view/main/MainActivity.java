package homepunk.lesson.first.view.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.roughike.bottombar.BottomBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.presenter.main.BottomNavigationPresenter;
import homepunk.lesson.first.presenter.main.NavDrawerPresenter;
import homepunk.lesson.first.presenter.main.SpinnerPresenter;
import homepunk.lesson.first.view.View;

public class MainActivity extends AppCompatActivity
        implements View.MainActivityView {

    @Bind(R.id.main_toolbar) Toolbar toolbar;
    @Bind(R.id.nav_view) NavigationView navigationView;
    @Bind(R.id.drawer_layout) DrawerLayout drawerLayout;
    @Bind(R.id.spinner) Spinner spinner;
    @Bind(R.id.bottomBar) BottomBar bottomBar;
    @Bind(R.id.main_relative_layout) RelativeLayout layout;

    private NavDrawerPresenter navDrawerModule;
    private BottomNavigationPresenter bottomNavBarModule;
    private SpinnerPresenter spinnerViewModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        navDrawerModule = new NavDrawerPresenter(this);
        navDrawerModule.attachNavDrawer();

        spinnerViewModule = new SpinnerPresenter(this);
        spinnerViewModule.attachSpinner();
        spinnerViewModule.setSpinnerArrowColor(R.color.colorText);

        bottomNavBarModule = new BottomNavigationPresenter(this);
        bottomNavBarModule.attachBottomNavigattionBar();
        bottomNavBarModule.serDefaultTab(R.id.tab_watchlist);
        bottomNavBarModule.setBackgroundColor(R.color.colorAccent);
    }

    @Override
    public Toolbar getToolbar() {
        return this.toolbar;
    }

    public Spinner getSpinnerView() {
        return this.spinner;
    }

    @Override
    public SpinnerPresenter getSpinnerPresenter() {
        return this.spinnerViewModule;
    }

    @Override
    public BottomBar getBoottomBar() {
        return this.bottomBar;
    }

    @Override
    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    @Override
    public NavigationView getNavigationView() {
        return navigationView;
    }

    @Override
    public void addSpinner(Spinner view) {
        this.layout.removeView(spinner);
        this.layout.addView(view);
    }


}
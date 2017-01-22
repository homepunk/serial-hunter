package homepunk.lesson.first.view.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.nav_view) NavigationView navigationView;
    @Bind(R.id.drawer_layout) DrawerLayout drawerLayout;
    @Bind(R.id.spinner) Spinner spinner;
    @Bind(R.id.bottomBar) BottomBar bottomBar;
    @Bind(R.id.main_relative_layout) RelativeLayout layout;

    private NavDrawerPresenter navDrawer;
    private BottomNavigationPresenter bottomNavBar;
    private SpinnerPresenter spinnerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        navDrawer = new NavDrawerPresenter(this);
        navDrawer.attachNavDrawer();

        spinnerView = new SpinnerPresenter(this);
        spinnerView.attachSpinner();
        spinnerView.setSpinnerArrowColor(R.color.colorText);

        bottomNavBar = new BottomNavigationPresenter(this);
        bottomNavBar.attachBottomNavigattionBar();
        bottomNavBar.serDefaultTab(R.id.action_watchlist);
        bottomNavBar.setBackgroundColor(R.color.colorAccent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return onOptionsItemSelected(item);
    }
    @Override
    public Toolbar getToolbar() {
        return this.toolbar;
    }

    @Override
    public Spinner getSpinnerView() {
        return this.spinner;
    }

    @Override
    public SpinnerPresenter getSpinnerPresenter() {
        return this.spinnerView;
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
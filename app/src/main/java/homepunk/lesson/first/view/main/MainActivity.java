package homepunk.lesson.first.view.main;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.roughike.bottombar.BottomBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.database.Constants;
import homepunk.lesson.first.presenter.main.MainActivityPresenter;
import homepunk.lesson.first.presenter.main.NavigationDrawerPresenter;
import homepunk.lesson.first.interfaces.View;

public class MainActivity extends AppCompatActivity
        implements View.MainActivityView{
    @Bind(R.id.main_toolbar) Toolbar toolbar;
    @Bind(R.id.nav_view) NavigationView navigationView;
    @Bind(R.id.drawer_layout) DrawerLayout drawerLayout;
    @Bind(R.id.spinner) Spinner spinner;
    @Bind(R.id.bottomBar) BottomBar bottomBar;
    @Bind(R.id.main_relative_layout) RelativeLayout layout;

    private NavigationDrawerPresenter navDrawerModule;
    private MainActivityPresenter mainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        navDrawerModule = new NavigationDrawerPresenter();
        mainActivityPresenter = new MainActivityPresenter();

        navDrawerModule.setView(this);
        navDrawerModule.setToolbar(toolbar);
        navDrawerModule.setDrawerLayout(drawerLayout);
        navDrawerModule.setNavigationView(navigationView);
        navDrawerModule.createNavDrawer();

        mainActivityPresenter.setView(this);
        mainActivityPresenter.setBottomBar(bottomBar);
        mainActivityPresenter.setFragmentManager(getSupportFragmentManager());
        mainActivityPresenter.setSpinner(spinner);
        mainActivityPresenter.onSpinnerItemClicked();
        mainActivityPresenter.onTabSelected();
        mainActivityPresenter.onStart();
    }


    @Override
    public void addSpinnerView(Spinner view) {
        this.layout.removeView(spinner);
        this.layout.addView(view);
    }

    @Override
    public void setSpinnerVisibility(boolean visibility) {
        if(Constants.VISIBLE)
            this.spinner.setVisibility(android.view.View.VISIBLE);
        else this.spinner.setVisibility(android.view.View.INVISIBLE);
    }

    @Override
    public boolean isSpinnerVisible() {
        return (this.spinner.getVisibility() == android.view.View.VISIBLE) ? true : false;
    }

    @Override
    public void setSpinnerDropDownWidth(int width) {
        spinner.setDropDownWidth(width);

    }

    @Override
    public void setSpinnerLayoutParams(ViewGroup.LayoutParams params) {
        spinner.setLayoutParams(params);
    }

    @Override
    public void setSpinnerAdapater(SpinnerAdapter adapter) {
        spinner.setAdapter(adapter);

    }

    @Override
    public void setSpinnerPrompt(String title) {
        spinner.setPrompt(title);
    }

    @Override
    public void setSpinnerSelection(int position) {
        spinner.setSelection(position);
    }

    @Override
    public void setSpinnerArrowColor(int color) {
        spinner.getBackground().setColorFilter(getResources().getColor(color), PorterDuff.Mode.SRC_ATOP);

    }

    @Override
    public void setBottomBarBackgroundColor(int color) {
        bottomBar.setBackgroundColor(color);
        bottomBar.setDrawingCacheBackgroundColor(color);
    }

    @Override
    public void setDefaultTab(int id) {
        bottomBar.setDefaultTab(id);
    }
}
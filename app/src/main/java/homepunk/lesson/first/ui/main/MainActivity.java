package homepunk.lesson.first.ui.main;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.lesson.first.di.App;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.data.database.Constants;
import homepunk.lesson.first.interfaces.Presenter;
import homepunk.lesson.first.interfaces.View;

public class MainActivity extends AppCompatActivity
        implements View.MainActivityView, NavigationView.OnNavigationItemSelectedListener {
    @Bind(R.id.main_toolbar) Toolbar toolbar;
    @Bind(R.id.nav_view) NavigationView navigationView;
    @Bind(R.id.drawer_layout) DrawerLayout drawerLayout;
    @Bind(R.id.spinner) Spinner spinner;
    @Bind(R.id.bottomBar) BottomBar bottomBar;
    @Bind(R.id.main_relative_layout) RelativeLayout layout;

    @Inject
    Presenter.MainActivityPresenter mainPresenter;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getAppComponent(this).plus(this);
        initUI();

        mainPresenter.setView(this);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        mainPresenter.onNavigationItemSelected(item.getItemId());

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            onBackPressed();
        }
    }

    private void initUI() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setUpSpinner();
        setUpBottomBar();
        setUpNavDrawer();
    }

    private void setUpBottomBar(){
        bottomBar.setBackgroundColor(Color.BLACK);
        bottomBar.setDrawingCacheBackgroundColor(Color.BLACK);
        bottomBar.setDefaultTab(R.id.tab_watchlist);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_search)
                    spinner.setVisibility(android.view.View.GONE);
                else spinner.setVisibility(android.view.View.VISIBLE);

                Fragment fragment = mainPresenter.onTabSelected(tabId);
                FragmentManager fragmentManager = getSupportFragmentManager();
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.contentContainer, fragment).commit();
            }
        });
    }

    private void setUpSpinner() {
        int spinnerWidth = getWindowManager().getDefaultDisplay().getWidth();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_spinner, Constants.data);
        adapter.setDropDownViewResource(R.layout.list_item_spinner_dpordown);

        spinner.getBackground().setColorFilter(getResources().getColor(R.color.colorText), PorterDuff.Mode.SRC_ATOP);
        spinner.setDropDownWidth(spinnerWidth);
        spinner.setAdapter(adapter);
        spinner.setSelection(2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
//              Presenter handles all user's actions
                mainPresenter.onSpinnerItemClicked();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    private void setUpNavDrawer(){
        drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
    }


}
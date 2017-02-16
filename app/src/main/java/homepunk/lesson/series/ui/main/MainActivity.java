package homepunk.lesson.series.ui.main;

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

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.series.data.Constants;
import homepunk.lesson.series.interfaces.View;
import homepunk.lesson.series.utils.ScreenUtils;

public class MainActivity extends AppCompatActivity
        implements View.MainActivityView, NavigationView.OnNavigationItemSelectedListener {
    @Bind(R.id.main_toolbar) Toolbar toolbar;
    @Bind(R.id.nav_view) NavigationView navigationView;
    @Bind(R.id.drawer_layout) DrawerLayout drawerLayout;
    @Bind(R.id.bottomBar) BottomBar bottomBar;
    @Bind(R.id.main_relative_layout) RelativeLayout layout;
    @Bind(R.id.spinner) Spinner spinner;


    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
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
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setUpBottomBar();
        setUpNavDrawer();
        setUpSpinner(spinner);
    }

    private void setUpBottomBar(){
        bottomBar.setBackgroundColor(Color.BLACK);
        bottomBar.setDrawingCacheBackgroundColor(Color.BLACK);
        bottomBar.setDefaultTab(R.id.tab_watchlist);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Fragment fragment = null;
                switch (tabId) {
                    case R.id.tab_hot_updates:
                        spinner.setVisibility(android.view.View.GONE);
                        fragment = new TopRatedFragment();
                        break;
                    case R.id.tab_watchlist:
                        spinner.setVisibility(android.view.View.VISIBLE);
                        fragment = new MainFragment();
                        break;
                    case R.id.tab_search:
                        spinner.setVisibility(android.view.View.GONE);
                        fragment = new SearchFragment();
                        break;
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content_container, fragment).commit();
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

    private void setUpSpinner(Spinner spinner) {
        int spinnerWidth = ScreenUtils.getDisplayContentWidth(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_spinner, Constants.data);
        adapter.setDropDownViewResource(R.layout.list_item_spinner_dpordown);

        spinner.getBackground().setColorFilter(getResources().getColor(R.color.colorText), PorterDuff.Mode.SRC_ATOP);
        spinner.setDropDownWidth(spinnerWidth);
        spinner.setAdapter(adapter);
        spinner.setSelection(2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }
}
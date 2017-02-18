package homepunk.lesson.series.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.series.adapter.SeriesPagerAdapter;
import homepunk.lesson.series.interfaces.View;

public class MainActivity extends AppCompatActivity
        implements View.MainActivityView, NavigationView.OnNavigationItemSelectedListener {
    @Bind(R.id.main_toolbar) Toolbar toolbar;
    @Bind(R.id.nav_view) NavigationView navigationView;
    @Bind(R.id.drawer_layout) DrawerLayout drawerLayout;
    @Bind(R.id.bottomBar) BottomBar bottomBar;
    @Bind(R.id.pager) ViewPager pager;


    private ActionBarDrawerToggle drawerToggle;
    private PagerAdapter pagerAdapter;

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
        setUpNavDrawer();
        setUpBottomBar();
        setUpViewPager();
    }

    private void setUpViewPager(){
        pagerAdapter = new SeriesPagerAdapter(getSupportFragmentManager());

        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(bottomBar.getCurrentTabPosition());
        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                bottomBar.selectTabAtPosition(position);
            }
        });
    }

    private void setUpBottomBar(){
        bottomBar.setBackgroundColor(Color.BLACK);
        bottomBar.setDrawingCacheBackgroundColor(Color.BLACK);
        bottomBar.setDefaultTab(R.id.tab_watchlist);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_hot_updates:
                        pager.setCurrentItem(0);
                        break;
                    case R.id.tab_watchlist:
                        pager.setCurrentItem(1);
                        break;
                    case R.id.tab_search:
                        pager.setCurrentItem(2);
                        break;
                }
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
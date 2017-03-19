package homepunk.lesson.series.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;

import java.util.List;

import javax.inject.Inject;

import br.com.mauker.materialsearchview.MaterialSearchView;
import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.series.App;
import homepunk.lesson.series.adapter.SeriesPagerAdapter;
import homepunk.lesson.series.interfaces.Presenter;
import homepunk.lesson.series.interfaces.View;
import homepunk.lesson.series.model.Series;
import homepunk.lesson.series.ui.base.BaseActivity;

public class MainActivity extends BaseActivity
        implements View.MainActivityView, NavigationView.OnNavigationItemSelectedListener, View.SearchFragmentView {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.bottomBar)
    BottomBar bottomNavgiationTabs;
    @Bind(R.id.pager)
    ViewPager pager;

    @Inject
    Presenter.SearchPresenter searchPresenter;

    private ActionBarDrawerToggle drawerToggle;
    private PagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.getAppComponent(this).plus(this);
        setSupportActionBar(toolbar);

        setupNavigationDrawer();
        setupBottomNavigationTabs();
        setupViewPager();
        setupSearchView();
        setupSearchViewListeners();
        setupPopupWindow();
    }

    @Override
    protected void onResume() {
        super.onResume();

        searchPresenter.setView(this);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_search:
                if (searchView != null)
                    searchView.openSearch();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (searchView != null && searchView.isOpen()) {
            searchView.closeSearch();
        } else {
            onBackPressed();
        }
    }

    @Override
    public void onSearchResult(List<Series> searchResults) {
        searchList.clear();
        searchList.addAll(searchResults);
        listPopupAdapter.notifyDataSetChanged();

        showDropDown();
    }

    @Override
    public void onError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    private void setupViewPager() {
        pagerAdapter = new SeriesPagerAdapter(getSupportFragmentManager());

        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(bottomNavgiationTabs.getCurrentTabPosition());
        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (searchView.isOpen())
                    searchView.closeSearch();
                bottomNavgiationTabs.selectTabAtPosition(position);
            }
        });
    }

    private void setupBottomNavigationTabs() {
        bottomNavgiationTabs.setBackgroundColor(Color.BLACK);
        bottomNavgiationTabs.setDrawingCacheBackgroundColor(Color.BLACK);
        bottomNavgiationTabs.setDefaultTab(R.id.tab_watchlist);
        bottomNavgiationTabs.setOnTabSelectListener(tabId -> {
            if (searchView != null && searchView.isOpen())
                searchView.closeSearch();

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
        });
    }

    private void setupNavigationDrawer() {
        drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupSearchViewListeners() {
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (searchPresenter != null)
                    searchPresenter.getSearchResults(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() <= 1) {
                    if (popupWindow.isShowing())
                        popupWindow.dismiss();

                    return false;
                } else {
                    searchPresenter.getSearchResults(newText);
                    return true;
                }
            }
        });
    }


}
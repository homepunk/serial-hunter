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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Toast;

import com.balysv.materialmenu.MaterialMenuDrawable;
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

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

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

        setupBottomNavigationTabs();
        setupViewPager();
        setupNavigationDrawer();
//        setupSearchView();
//        setupSearchViewListeners();
        setupPopupWindow();
        setupAnimatedSearch();
        setupSearchToLineListeners();
        setupOpenCloseAnimation();
        setupHamburgerIconListener();
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
            dismissDropDown();
        } else if (mEditText != null && mEditText.getVisibility() == VISIBLE) {
            closeAnimatedSearch(closeAnim, true);
        } else {
            onBackPressed();
            dismissDropDown();
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
                closeAnimatedSearch(quickCloseAnim, false);
                bottomNavgiationTabs.selectTabAtPosition(position);
            }
        });
    }

    private void setupBottomNavigationTabs() {
        bottomNavgiationTabs.setBackgroundColor(Color.BLACK);
        bottomNavgiationTabs.setDrawingCacheBackgroundColor(Color.BLACK);
        bottomNavgiationTabs.setDefaultTab(R.id.tab_watchlist);
        bottomNavgiationTabs.setOnTabSelectListener(tabId -> {
            closeAnimatedSearch(quickCloseAnim, false);

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

        materialMenu = new MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.EXTRA_THIN);
        toolbar.setNavigationIcon(materialMenu);
//        navigationView.setItemIconTintList(null);

        navigationView.setNavigationItemSelectedListener(this);

    }

    private void setupSearchToLineListeners() {
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 1)
                    searchPresenter.getSearchResults(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setupHamburgerIconListener() {
        toolbar.setNavigationOnClickListener(v -> {
            if (mEditText != null && mEditText.getVisibility() == VISIBLE) {
                closeAnimatedSearch(closeAnim, true);
            } else if (mEditText.getVisibility() == GONE)
                drawerLayout.openDrawer(GravityCompat.START);
        });
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
                    dismissDropDown();

                    return false;
                } else {
                    searchPresenter.getSearchResults(newText);
                    return true;
                }
            }
        });
    }

}
package homepunk.lesson.first.view.main;

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
import android.view.Display;
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
import homepunk.lesson.first.database.Constants;
import homepunk.lesson.first.interfaces.Presenter;
import homepunk.lesson.first.interfaces.View;
import homepunk.lesson.first.presenter.main.MainActivityPresenter;

public class MainActivity extends AppCompatActivity
        implements View.MainActivityView, NavigationView.OnNavigationItemSelectedListener {
    @Bind(R.id.main_toolbar) Toolbar toolbar;
    @Bind(R.id.nav_view) NavigationView navigationView;
    @Bind(R.id.drawer_layout) DrawerLayout drawerLayout;
    @Bind(R.id.spinner) Spinner spinner;
    @Bind(R.id.bottomBar) BottomBar bottomBar;
    @Bind(R.id.main_relative_layout) RelativeLayout layout;

    private ActionBarDrawerToggle drawerToggle;
    private Presenter.MainActivityPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mainPresenter = new MainActivityPresenter();
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

    @Override
    public boolean isSpinnerVisible() {
        if (spinner.getVisibility() == android.view.View.VISIBLE)
            return true;
        else return false;
    }

    @Override
    public void setSpinnerVisibility(boolean visibility) {
        if (visibility = Constants.VISIBLE)
            spinner.setVisibility(android.view.View.VISIBLE);
        else spinner.setVisibility(android.view.View.INVISIBLE);

    }

    @Override
    public void beginTransaction(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contentContainer, fragment).commit();
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
                mainPresenter.onTabSelected(tabId);
            }
        });
    }

    private void setUpSpinner() {
        Display display = getWindowManager().getDefaultDisplay();
        int spinnerWidth = display.getWidth();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_spinner, Constants.data);
        adapter.setDropDownViewResource(R.layout.list_item_spinner_dpordown);
        spinner.getBackground().setColorFilter(getResources().getColor(R.color.colorText), PorterDuff.Mode.SRC_ATOP);
        spinner.setDropDownWidth(spinnerWidth);
        spinner.setLayoutParams(spinner.getLayoutParams());
        spinner.setAdapter(adapter);
        spinner.setPrompt("Title");
        spinner.setSelection(2);
        spinner.setVisibility(android.view.View.VISIBLE);
        spinner.addView(spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
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
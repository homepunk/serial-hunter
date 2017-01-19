package homepunk.lesson.first.view.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.presenter.main.NavDrawerPresenter;
import homepunk.lesson.first.presenter.main.TabsPresenter;
import homepunk.lesson.first.view.View;

public class MainActivity extends AppCompatActivity
        implements  View.MainActivityView {


    @Bind (R.id.tabs) TabLayout tabs;
    @Bind (R.id.pager) ViewPager pager;
    @Bind (R.id.toolbar) Toolbar toolbar;
    @Bind (R.id.header) ImageView header;
    @Bind (R.id.nav_view) NavigationView navigationView;
    @Bind (R.id.drawer_layout) DrawerLayout drawerLayout;

    private NavDrawerPresenter drawerPresenter;
    private TabsPresenter tabsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        drawerPresenter = new NavDrawerPresenter(this);
        drawerPresenter.setUpNavDrawer();

        tabsPresenter = new TabsPresenter(this);
        tabsPresenter.setUpTabs();
    }

    @Override
    public TabLayout getTabs() {
        return tabs;
    }

    @Override
    public ViewPager getViewPager() {
        return pager;
    }

    @Override
    public Toolbar getToolbar() {
        return this.toolbar;
    }

    @Override
    public NavigationView getNavigationView() {
        return navigationView;
    }

    @Override
    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }





}

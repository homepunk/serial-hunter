package homepunk.lesson.first.presenter.main;

import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.interfaces.Presenter;
import homepunk.lesson.first.interfaces.View;
import homepunk.lesson.first.view.main.MainActivity;

public class NavigationDrawerPresenter implements OnNavigationItemSelectedListener, Presenter.NavDrawerPresenter {
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private MenuItem searchMenuItem;
    public MainActivity view;
    public ActionBarDrawerToggle drawerToggle;


    @Override
    public void setView(View.MainActivityView view){
        this.view = (MainActivity) view;
    }

    @Override
    public void setToolbar(Toolbar toolbar){
        this.toolbar = toolbar;
    }

    @Override
    public void setNavigationView(NavigationView navigationView){
        this.navigationView = navigationView;
    }

    @Override
    public void setDrawerLayout(DrawerLayout drawer){
        this.drawer = drawer;
    }

    @Override
    public void createNavDrawer() {
        drawerToggle = new ActionBarDrawerToggle(
                view, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            view.onBackPressed();
        }
    }
}

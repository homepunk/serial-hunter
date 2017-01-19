package homepunk.lesson.first.presenter.main;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import butterknife.BindString;
import homepunk.lesson.first.view.main.MainActivity;
import homepunk.lesson.first.adapter.TabsAdapter;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.presenter.Presenter;

public class TabsPresenter implements Presenter.TabsPresenter {
    @BindString(R.string.tab_top) String tab_top;
    @BindString(R.string.tab_watch_later) String tab_watch_later;

    public CharSequence titles[] = {tab_top, tab_watch_later};
    public TabsAdapter adapter;
    private MainActivity view;
    private ViewPager pager;
    private TabLayout tabs;

    public TabsPresenter(MainActivity view) {
        this.view = view;
        this.pager = view.getViewPager();
        this.tabs = view.getTabs();
    }

    @Override
    public void setUpTabs() {
        adapter = new TabsAdapter(view.getSupportFragmentManager(), titles, titles.length);
        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);
        setUpTabIcons();
    }

    @Override
    public void setUpTabIcons() {
        tabs.getTabAt(0).setIcon(R.drawable.ic_star_5);
        tabs.getTabAt(1).setIcon(R.drawable.ic_watch_later);
    }
}

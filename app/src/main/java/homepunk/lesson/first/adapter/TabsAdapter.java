package homepunk.lesson.first.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import homepunk.lesson.first.view.detailed.DetailedActivityFragment;
import homepunk.lesson.first.view.main.MainActivityFragment;

public class TabsAdapter extends FragmentStatePagerAdapter {

    private CharSequence titles[];
    private int numbOfTabs;

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public TabsAdapter(FragmentManager fm, CharSequence tabTitles[], int numbOfTabs) {
        super(fm);
        this.titles = tabTitles;
        this.numbOfTabs = numbOfTabs;
    }

    // This method return the fragment for the every position in the View Pager
    // if the position is 0 we are returning the First tab
    // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            MainActivityFragment tab1 = new MainActivityFragment();
            return tab1;
        } else {
            DetailedActivityFragment tab2 = new DetailedActivityFragment();
            return tab2;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return numbOfTabs;
    }
}

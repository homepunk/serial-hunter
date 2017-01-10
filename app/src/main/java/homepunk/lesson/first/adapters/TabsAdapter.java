package homepunk.lesson.first.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import homepunk.lesson.first.contollers.fragments.DetailedPageActivityFragment;
import homepunk.lesson.first.contollers.fragments.MainActivityFragment;

public class TabsAdapter extends FragmentStatePagerAdapter {

    CharSequence titles[];
    int numbOfTabs;


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public TabsAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);
        this.titles = mTitles;
        this.numbOfTabs = mNumbOfTabsumb;

    }
    // This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {
        if (position == 0) // if the position is 0 we are returning the First tab
        {
            MainActivityFragment tab1 = new MainActivityFragment();
            return tab1;
        } else             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            DetailedPageActivityFragment tab2 = new DetailedPageActivityFragment();
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

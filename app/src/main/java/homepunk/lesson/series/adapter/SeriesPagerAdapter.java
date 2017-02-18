package homepunk.lesson.series.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import homepunk.lesson.series.ui.main.MainFragment;
import homepunk.lesson.series.ui.main.PopularFragment;
import homepunk.lesson.series.ui.main.SearchFragment;

public class SeriesPagerAdapter extends FragmentStatePagerAdapter {
    public SeriesPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new PopularFragment();
                break;
            case 1:
                fragment = new MainFragment();
                break;
            case 2:
                fragment = new SearchFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}

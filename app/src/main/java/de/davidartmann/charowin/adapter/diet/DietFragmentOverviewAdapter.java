package de.davidartmann.charowin.adapter.diet;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import de.davidartmann.charowin.fragment.diet.DietFragmentCalender;
import de.davidartmann.charowin.fragment.diet.DietFragmentCharts;
import de.davidartmann.charowin.fragment.diet.DietFragmentDietplanList;
import de.davidartmann.charowin.fragment.diet.DietFragmentMealList;

/**
 * Adapter for the training tab overview.
 * {@link FragmentStatePagerAdapter} is important, else we get empty views,
 * the second time we open a child fragment of our {@link android.support.v4.view.ViewPager}.
 *
 * Created by David on 05.10.2015.
 */
public class DietFragmentOverviewAdapter extends FragmentStatePagerAdapter {

    private static final String DIET_FRAGMENT_OVERVIEW_ADAPTER =
            DietFragmentOverviewAdapter.class.getSimpleName();

    private String[] mTitles;

    public DietFragmentOverviewAdapter(FragmentManager fragmentManager, String[] mTitles) {
        super(fragmentManager);
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new DietFragmentMealList();
                break;
            case 1:
                fragment = new DietFragmentDietplanList();
                break;
            case 2:
                fragment = new DietFragmentCalender();
                break;
            case 3:
                fragment = new DietFragmentCharts();
                break;
            default:
                Log.w(DIET_FRAGMENT_OVERVIEW_ADAPTER, "default path in getItem()");
                fragment = new DietFragmentMealList();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}

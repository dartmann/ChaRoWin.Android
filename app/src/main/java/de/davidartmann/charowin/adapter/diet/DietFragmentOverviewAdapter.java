package de.davidartmann.charowin.adapter.diet;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import de.davidartmann.charowin.fragment.diet.DietFragmentMealList;
import de.davidartmann.charowin.fragment.training.TrainingFragmentCharts;
import de.davidartmann.charowin.fragment.training.TrainingFragmentExerciseList;
import de.davidartmann.charowin.fragment.training.TrainingFragmentWorkoutCalender;
import de.davidartmann.charowin.fragment.training.TrainingFragmentWorkoutPlanList;

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

package de.davidartmann.charowin.adapter.training;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import de.davidartmann.charowin.fragment.training.TrainingFragmentCharts;
import de.davidartmann.charowin.fragment.training.TrainingFragmentExerciseList;
import de.davidartmann.charowin.fragment.training.TrainingFragmentWorkoutCalender;
import de.davidartmann.charowin.fragment.training.TrainingFragmentWorkoutPlanList;

/**
 * Adapter for the training tab overview.
 *
 * Created by David on 05.10.2015.
 */
public class TrainingFragmentOverviewNewAdapter extends FragmentPagerAdapter {

    private static final String TRAINING_FRAGMENT_OVERVIEW_NEW_ADAPTER =
            TrainingFragmentOverviewNewAdapter.class.getSimpleName();

    private int numberOfTabs;
    private String[] mTitles;

    public TrainingFragmentOverviewNewAdapter(FragmentManager fragmentManager, int numberOfTabs, String[] mTitles) {
        super(fragmentManager);
        this.numberOfTabs = numberOfTabs;
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new TrainingFragmentExerciseList();
                break;
            case 1:
                fragment = new TrainingFragmentWorkoutPlanList();
                break;
            case 2:
                fragment = new TrainingFragmentWorkoutCalender();
                break;
            case 3:
                fragment = new TrainingFragmentCharts();
                break;
            default:
                Log.w(TRAINING_FRAGMENT_OVERVIEW_NEW_ADAPTER, "default path in getItem()");
                fragment = new TrainingFragmentExerciseList();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Log.d(TRAINING_FRAGMENT_OVERVIEW_NEW_ADAPTER, mTitles[position]);
        return mTitles[position];
    }
}

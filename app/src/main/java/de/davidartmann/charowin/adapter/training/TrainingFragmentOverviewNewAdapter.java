package de.davidartmann.charowin.adapter.training;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import de.davidartmann.charowin.fragment.training.TrainingFragmentCharts;
import de.davidartmann.charowin.fragment.training.TrainingFragmentExerciseList;
import de.davidartmann.charowin.fragment.training.TrainingFragmentCalender;
import de.davidartmann.charowin.fragment.training.TrainingFragmentWorkoutPlanList;

/**
 * Adapter for the training tab overview.
 * {@link FragmentStatePagerAdapter} is important, else we get empty views,
 * the second time we open a child fragment of our {@link android.support.v4.view.ViewPager}.
 *
 * Created by David on 05.10.2015.
 */
public class TrainingFragmentOverviewNewAdapter extends FragmentStatePagerAdapter {

    private static final String TRAINING_FRAGMENT_OVERVIEW_NEW_ADAPTER =
            TrainingFragmentOverviewNewAdapter.class.getSimpleName();

    private String[] mTitles;

    public TrainingFragmentOverviewNewAdapter(FragmentManager fragmentManager, String[] mTitles) {
        super(fragmentManager);
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
                fragment = new TrainingFragmentCalender();
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
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}

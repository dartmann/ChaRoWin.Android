package de.davidartmann.charowin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import de.davidartmann.charowin.adapter.training.TrainingFragmentOverviewNewAdapter;

/**
 * Training View which contains a tabbed view between the exerciselist, workoutlist, calender
 * and statistics.
 *
 * Created by David on 05.10.2015.
 */
public class TrainingActivityOverviewNew extends FragmentActivity {

    private static final String TRAINING_FRAGMENT_OVERVIEW_NEW =
            TrainingActivityOverviewNew.class.getSimpleName();

    private ViewPager mViewPager;
    private String[] mTitles;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_training_overview_new);

        mTitles = getResources().getStringArray(R.array.fragment_training_overview_titles);
        mViewPager = (ViewPager) findViewById(R.id.fragment_training_overview_new_viewpager);
        mViewPager.setAdapter(new TrainingFragmentOverviewNewAdapter(getSupportFragmentManager(), mTitles.length, mTitles));
    }
}

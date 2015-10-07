package de.davidartmann.charowin.fragment.training;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import de.davidartmann.charowin.R;
import de.davidartmann.charowin.adapter.training.TrainingFragmentOverviewNewAdapter;

/**
 * Training View which contains a tabbed view between the exerciselist, workoutlist, calender
 * and statistics.
 *
 * Created by David on 05.10.2015.
 */
public class TrainingFragmentOverview extends Fragment {

//    private static final String TRAINING_FRAGMENT_OVERVIEW_NEW =
//            TrainingFragmentOverview.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training_overview, container, false);
        String[] mPagerTitleStripTitles = getResources().getStringArray(R.array.fragment_training_overview_titles);
        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.fragment_training_overview_new_viewpager);
        mViewPager.setAdapter(new TrainingFragmentOverviewNewAdapter(
                ((AppCompatActivity) getActivity()).getSupportFragmentManager(), mPagerTitleStripTitles));
        PagerSlidingTabStrip pagerSlidingTabStrip =
                (PagerSlidingTabStrip) view.findViewById(R.id.fragment_training_overview_new_pagerslidingtabstrip);
        pagerSlidingTabStrip.setViewPager(mViewPager);
        return view;
    }
}

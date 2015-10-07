package de.davidartmann.charowin.fragment.diet;

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
import de.davidartmann.charowin.adapter.diet.DietFragmentOverviewAdapter;

/**
 * Diet Overview Fragment, which contains a {@link com.astuetz.PagerSlidingTabStrip}.
 *
 * Created by David on 24.09.2015.
 */
public class DietFragmentOverview extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diet_overview, container, false);
        String[] mPagerTitleStripTitles = getResources().getStringArray(R.array.fragment_diet_overview);
        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.fragment_diet_overview_new_viewpager);
        mViewPager.setAdapter(new DietFragmentOverviewAdapter(
                ((AppCompatActivity) getActivity()).getSupportFragmentManager(), mPagerTitleStripTitles));
        PagerSlidingTabStrip pagerSlidingTabStrip =
                (PagerSlidingTabStrip) view.findViewById(R.id.fragment_diet_overview_new_pagerslidingtabstrip);
        pagerSlidingTabStrip.setViewPager(mViewPager);
        return view;
    }
}

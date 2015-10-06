package de.davidartmann.charowin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import de.davidartmann.charowin.adapter.training.TrainingFragmentOverviewNewAdapter;

/**
 * Training View which contains a tabbed view between the exerciselist, workoutlist, calender
 * and statistics.
 *
 * Created by David on 05.10.2015.
 */
public class TrainingActivityOverviewNew extends AppCompatActivity {

    private static final String TRAINING_FRAGMENT_OVERVIEW_NEW =
            TrainingActivityOverviewNew.class.getSimpleName();

    private ViewPager mViewPager;
    private String[] mTitles;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_overview_new);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();/*getActionBar();*/
        if (actionBar != null) {
            //back button is removed by disabling this
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        mTitles = getResources().getStringArray(R.array.fragment_training_overview_titles);
        mViewPager = (ViewPager) findViewById(R.id.fragment_training_overview_new_viewpager);
        mViewPager.setAdapter(new TrainingFragmentOverviewNewAdapter(getSupportFragmentManager(), mTitles.length, mTitles));
        setTitle(mTitles[0]);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.animation_up_enter, R.anim.animation_up_exit);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                overridePendingTransition(R.anim.animation_up_enter, R.anim.animation_up_exit);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

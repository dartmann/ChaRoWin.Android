package de.davidartmann.charowin.fragment.training;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import de.davidartmann.charowin.R;
import de.davidartmann.charowin.adapter.training.TrainingFragmentWorkoutPlanListAdapterNew;
import de.davidartmann.charowin.adapter.training.model.WorkoutPlanNew;
import de.davidartmann.charowin.util.CustomSnackBar;

/**
 * Fragment for the workout plan list.
 *
 * Created by David on 05.10.2015.
 */
public class TrainingFragmentWorkoutPlanList extends Fragment {

    private View mView;
    private ImageView mImageViewPinAsCurrent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_training_workoutplanlist, container, false);
        RecyclerView mRecyclerView = (RecyclerView) mView.findViewById(R.id.fragment_training_workoutplanlist_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mView.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        List<WorkoutPlanNew> workoutPlans = createWorkouts();
        RecyclerView.Adapter mAdapter = new TrainingFragmentWorkoutPlanListAdapterNew(workoutPlans);
        mRecyclerView.setAdapter(mAdapter);
        ImageView imageViewSettings =
                (ImageView) mView.findViewById(R.id.fragment_training_workoutplanlist_cardlayout_imageview_settings);
        imageViewSettings.setOnClickListener(new ImageViewSettingsClickListener());
        mImageViewPinAsCurrent =
                (ImageView) mView.findViewById(R.id.fragment_training_workoutplanlist_cardview_imageview_pinascurrent);
        mImageViewPinAsCurrent.setOnClickListener(new ImageViewPinAsCurrentListener());
        LinearLayout linearLayoutCard =
                (LinearLayout) mView.findViewById(R.id.fragment_training_workoutplanlist_cardview_linearlayout_card);
        linearLayoutCard.setOnClickListener(new LinearLayoutCardListener());
        //TODO: check which plan is pinned as current or if none
        return mView;
    }

    private List<WorkoutPlanNew> createWorkouts() {
        List<WorkoutPlanNew> workoutPlans = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            WorkoutPlanNew workoutPlan = new WorkoutPlanNew("Name "+i, "Description "+i, String.valueOf(i));
            workoutPlans.add(workoutPlan);
        }
        return workoutPlans;
    }

    private class ImageViewSettingsClickListener implements View.OnClickListener {

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            CustomSnackBar.create(mView, "Einstellungen zeigen", null, null);
        }
    }

    private class ImageViewPinAsCurrentListener implements View.OnClickListener {

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            //TODO: check if another plan is pinned as current, then reset its image first
            mImageViewPinAsCurrent.setImageResource(R.drawable.ic_favorite_black_48dp);
        }
    }

    private class LinearLayoutCardListener implements View.OnClickListener {

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            CustomSnackBar.create(mView, "Einzelnes Workout in DialogView zeigen?", null, null);
        }
    }
}

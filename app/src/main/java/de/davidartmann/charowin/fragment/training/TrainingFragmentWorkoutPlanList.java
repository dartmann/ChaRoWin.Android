package de.davidartmann.charowin.fragment.training;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import de.davidartmann.charowin.R;
import de.davidartmann.charowin.adapter.training.TrainingFragmentWorkoutPlanListAdapter;
import de.davidartmann.charowin.adapter.training.model.WorkoutPlanAdapterModel;

/**
 * Fragment for the workout plan list.
 *
 * Created by David on 05.10.2015.
 */
public class TrainingFragmentWorkoutPlanList extends Fragment {

//    private static final String TRAINING_FRAGMENT_WORKOUT_PLANLIST =
//            TrainingFragmentWorkoutPlanList.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training_workoutplanlist, container, false);
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_training_workoutplanlist_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        List<WorkoutPlanAdapterModel> workoutPlanAdapterModels = createWorkouts();
        RecyclerView.Adapter mAdapter = new TrainingFragmentWorkoutPlanListAdapter(workoutPlanAdapterModels);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    private List<WorkoutPlanAdapterModel> createWorkouts() {
        List<WorkoutPlanAdapterModel> workoutPlanAdapterModels = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            WorkoutPlanAdapterModel workoutPlanAdapterModel = new WorkoutPlanAdapterModel("Name "+i, "Description "+i, String.valueOf(i));
            workoutPlanAdapterModels.add(workoutPlanAdapterModel);
        }
        return workoutPlanAdapterModels;
    }
}

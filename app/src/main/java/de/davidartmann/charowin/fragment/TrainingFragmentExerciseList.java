package de.davidartmann.charowin.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import de.davidartmann.charowin.R;
import de.davidartmann.charowin.adapter.TrainingFragmentExerciseListAdapter;
import de.davidartmann.charowin.adapter.model.Exercise;

/**
 * Fragment for the list of exercises of the actual trainingplan.
 *
 * Created by David on 28.08.2015.
 */
public class TrainingFragmentExerciseList extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //TODO: just for testing, delete afterwards:
        List<Exercise> exercises = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            Exercise exercise = new Exercise();
            exercise.setImageUrl("http://www.shapefit.com/wp-content/uploads/2015/04/chest-exercises-barbell-bench-press-medium-grip.gif");
            exercise.setExerciseName("Exercise " + i);
            exercise.setSets("3");
            exercise.setReps("10,10,10,8");
            exercise.setRestTime("120");
            exercises.add(exercise);
        }
        View view = inflater.inflate(R.layout.fragment_training_exerciselist, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_training_recyclerview);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new TrainingFragmentExerciseListAdapter(exercises);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }
}

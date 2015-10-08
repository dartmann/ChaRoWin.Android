package de.davidartmann.charowin.fragment.training;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import de.davidartmann.charowin.R;
import de.davidartmann.charowin.adapter.training.TrainingFragmentExerciseListAdapter;
import de.davidartmann.charowin.adapter.training.model.Exercise;
import de.davidartmann.charowin.util.CustomSnackBar;

/**
 * Fragment for the exercise list.
 *
 * Created by David on 05.10.2015.
 */
public class TrainingFragmentExerciseList extends Fragment implements View.OnClickListener {

//    private static final String TRAINING_FRAGMENT_EXERCISELIST =
//            TrainingFragmentExerciseList.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //TODO: just for testing, delete afterwards:
        List<Exercise> exercises = createExercises();
        View view = inflater.inflate(R.layout.fragment_training_exerciselist, container, false);
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_training_exerciselist_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.Adapter mAdapter = new TrainingFragmentExerciseListAdapter(exercises);
        mRecyclerView.setAdapter(mAdapter);
        FloatingActionButton floatingActionButton =
                (FloatingActionButton) view.findViewById(R.id.fragment_training_exerciselist_floatingactionbutton);
        floatingActionButton.setOnClickListener(this);
        return view;
    }

    private List<Exercise> createExercises() {
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
        return exercises;
    }

    private void showExerciseAddDialog() {
        CustomSnackBar.create(getView(), "Übung wurde gespeichert", null, null);
    }

    @Override
    public void onClick(View v) {
        showExerciseAddDialog();
    }
}

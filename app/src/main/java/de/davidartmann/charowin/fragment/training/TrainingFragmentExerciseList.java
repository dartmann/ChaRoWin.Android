package de.davidartmann.charowin.fragment.training;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
public class TrainingFragmentExerciseList extends Fragment {

//    private static final String TRAINING_FRAGMENT_EXERCISELIST =
//            TrainingFragmentExerciseList.class.getSimpleName();
    //TODO: view as class attr necessary?
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //TODO: just for testing, delete afterwards:
        List<Exercise> exercises = createExercises();
        mView = inflater.inflate(R.layout.fragment_training_exerciselist, container, false);
        RecyclerView mRecyclerView = (RecyclerView) mView.findViewById(R.id.fragment_training_exerciselist_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mView.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.Adapter mAdapter = new TrainingFragmentExerciseListAdapter(exercises);
        mRecyclerView.setAdapter(mAdapter);
        FloatingActionButton floatingActionButton =
                (FloatingActionButton) mView.findViewById(R.id.fragment_training_exerciselist_floatingactionbutton);
        floatingActionButton.setOnClickListener(new FabClickListener());
        ImageView imageViewSettings =
                (ImageView) mView.findViewById(R.id.fragment_training_exerciselist_cardlayout_imageview_settings);
        imageViewSettings.setOnClickListener(new ImageViewSettingsClickListener());
        return mView;
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
        if (mView != null) {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(
                        getActivity(), android.R.style.Theme_Material_Light_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(
                        getActivity(), android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth);
            }
            builder.setTitle("Neue Übung");
            builder.setView(getActivity().getLayoutInflater().inflate(
                    R.layout.fragment_training_exerciselist_dialog, null))
                .setPositiveButton("Speichern", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        CustomSnackBar.create(mView, "Übung wurde gespeichert", null, null);
                        //TODO: save exercise
                    }
                })
                .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //nothing to do
                    }
                });
            Dialog dialog = builder.create();
//            dialog.setTitle("Neue Übung");
            dialog.show();
            //claiming divider color is only possible to change programmatically
            int titleDividerId = getResources().getIdentifier("titleDivider", "id", "android");
            View titleDividerView = dialog.findViewById(titleDividerId);
            if (titleDividerView != null) {
                titleDividerView.setBackgroundColor(ContextCompat.getColor(mView.getContext(), R.color.green));
            }
        }
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

    private class FabClickListener implements View.OnClickListener {

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            showExerciseAddDialog();
        }
    }
}

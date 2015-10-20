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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import de.davidartmann.charowin.R;
import de.davidartmann.charowin.adapter.training.TrainingFragmentExerciseListAdapter;
import de.davidartmann.charowin.adapter.training.model.ExerciseAdapterModel;
import de.davidartmann.charowin.db.ChaRoWinDatabaseManager;
import de.davidartmann.charowin.db.model.Exercise;
import de.davidartmann.charowin.util.CustomSnackBar;

/**
 * Fragment for the exercise list.
 *
 * Created by David on 05.10.2015.
 */
public class TrainingFragmentExerciseList extends Fragment implements View.OnClickListener {

    private static final String TRAINING_FRAGMENT_EXERCISELIST =
            TrainingFragmentExerciseList.class.getSimpleName();

    //TODO: view as class attr necessary?
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //TODO: just for testing, delete afterwards:
        List<ExerciseAdapterModel> exerciseAdapterModels = createExercises();
        mView = inflater.inflate(R.layout.fragment_training_exerciselist, container, false);
        RecyclerView mRecyclerView = (RecyclerView) mView.findViewById(R.id.fragment_training_exerciselist_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mView.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.Adapter mAdapter = new TrainingFragmentExerciseListAdapter(exerciseAdapterModels);
        mRecyclerView.setAdapter(mAdapter);
        FloatingActionButton floatingActionButton =
                (FloatingActionButton) mView.findViewById(R.id.fragment_training_exerciselist_floatingactionbutton);
        floatingActionButton.setOnClickListener(this);
        return mView;
    }

    private List<ExerciseAdapterModel> createExercises() {
        List<ExerciseAdapterModel> exerciseAdapterModels = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            ExerciseAdapterModel exerciseAdapterModel = new ExerciseAdapterModel();
            exerciseAdapterModel.setImageUrl("http://www.shapefit.com/wp-content/uploads/2015/04/chest-exerciseAdapterModels-barbell-bench-press-medium-grip.gif");
            exerciseAdapterModel.setExerciseName("ExerciseAdapterModel " + i);
            exerciseAdapterModel.setSets("3");
            exerciseAdapterModel.setReps("10,10,10,8");
            exerciseAdapterModel.setRestTime("120");
            exerciseAdapterModels.add(exerciseAdapterModel);
        }
        return exerciseAdapterModels;
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
                        ChaRoWinDatabaseManager chaRoWinDatabaseManager = new ChaRoWinDatabaseManager(getContext());
                        //TODO: create exercise and then add it to db
                        //chaRoWinDatabaseManager.createExercise();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_training_exerciselist_floatingactionbutton:
                showExerciseAddDialog();
                break;
            default:
                Log.d(TRAINING_FRAGMENT_EXERCISELIST, "Default path in onClick()");
        }
    }
}

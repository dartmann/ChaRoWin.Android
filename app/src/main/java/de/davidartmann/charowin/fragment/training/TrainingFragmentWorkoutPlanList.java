package de.davidartmann.charowin.fragment.training;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.davidartmann.charowin.R;

/**
 * Created by David on 05.10.2015.
 */
public class TrainingFragmentWorkoutPlanList extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_training_workoutplanlist, container, false);
    }
}

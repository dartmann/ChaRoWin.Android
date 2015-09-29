package de.davidartmann.charowin.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import de.davidartmann.charowin.R;
import de.davidartmann.charowin.adapter.TrainingAdapter;
import de.davidartmann.charowin.model.Exercise;

/**
 * Created by David on 28.08.2015.
 */
public class TrainingFragment extends Fragment {

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
            exercise.setName("Exercise " + i);
            exercise.setImageUrl("https://fitness.davidartmann.de/wp-content/uploads/2015/04/IMG_20150408_142700.jpg");
            exercises.add(exercise);
        }
//        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_training, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_training_recyclerview);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new TrainingAdapter(exercises);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }
}

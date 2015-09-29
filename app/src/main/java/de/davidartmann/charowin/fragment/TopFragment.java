package de.davidartmann.charowin.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
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
import de.davidartmann.charowin.adapter.TopAdapter;
import de.davidartmann.charowin.model.TopAdapterModel;

/**
 * Created by David on 28.08.2015.
 */
public class TopFragment extends Fragment {

    private static final String TOP_FRAGMENT = "TopFragment";

    //TODO: instead of a recyclerview we create a fixed gui with the two "cards"
    
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
        View view = inflater.inflate(R.layout.fragment_top, container, false);
        if (view != null) {
            mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_top_recyclerview);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(view.getContext());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new TopAdapter(createTopAdapterModels());//TODO: debugging, change to real data when possible
            mRecyclerView.setAdapter(mAdapter);
            /*
            imageView = (ImageView) view.findViewById(R.id.fragment_top_imageview_debug);
            Picasso.with(view.getContext()).setLoggingEnabled(true);
            Picasso.with(view.getContext())
                    .load("http://4.bp.blogspot.com/-a77bEtz0S48/USJ5cZL_ByI/AAAAAAAAFD8/LHJ_JWbYCOU/s1600/Arnold+Schwarzenegger+0.jpg")
                    .into(imageView);

            mCircularImageViewTraining.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    replaceFragment(new TrainingFragment());
                }
            });
            mCircularImageViewDiet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    replaceFragment(new DietFragment());
                }
            });
            */
        } else {
            Log.w(TOP_FRAGMENT, "view was null");
        }
        return view;
    }

    /**
     * Helper method for creating the models for the recyclerview.
     *
     * @return List of TopAdapterModel
     */
    private List<TopAdapterModel> createTopAdapterModels() {
        List<TopAdapterModel> topAdapterModels = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            TopAdapterModel topAdapterModel = new TopAdapterModel();;
            switch (i) {
                case 0:
                    topAdapterModel.setTitle(getString(R.string.fragment_top_next_training_title));
                    topAdapterModel.setTrainingOrMealName("Brust und Rücken");
                    topAdapterModel.setTrainingOrMealDay("Montag");
                    topAdapterModel.setAmountExercisesOrEnergyValue("5");
                    topAdapterModel.setTimeSinceLastExercise("Vor 4 Stunden");
                    topAdapterModel.setAverageTrainingsTime("60 Minuten");
                    break;
                case 1:
                    topAdapterModel.setTitle(getString(R.string.fragment_top_next_meal_title));
                    topAdapterModel.setTrainingOrMealName("Frühstück");
                    topAdapterModel.setTrainingOrMealDay("Montag");
                    topAdapterModel.setAmountExercisesOrEnergyValue("750 kCal");
                    topAdapterModel.setTimeSinceLastExercise("");
                    topAdapterModel.setAverageTrainingsTime("");
                    break;
            }
            topAdapterModels.add(topAdapterModel);
        }
        return topAdapterModels;
    }

    /**
     * Helper method to call #getFragmentManager and replace a given Fragment
     * @param fragment
     */
    private void replaceFragment(Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.drawer_frame_layout, fragment)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}

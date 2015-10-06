package de.davidartmann.charowin.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.davidartmann.charowin.R;

/**
 * FragmentView which is the main entrance point to the app.
 *
 * Created by David on 28.08.2015.
 */
public class TopFragment extends Fragment {

    private static final String TOP_FRAGMENT = "TopFragment";

    private RelativeLayout mRelativeLayoutNextTraining;
    private ImageView mImageViewSettingsNextTraining;
    private TextView mTextViewTrainingName;
    private TextView mTextViewTrainingDay;
    private TextView mTextViewNumberOfExercises;
    private TextView mTextViewLastTimeExercised;
    private TextView mTextViewTrainingTime;
    private RelativeLayout mRelativeLayoutNextMeal;
    private ImageView mImageViewSettingsNextMeal;
    private TextView mTextViewMealName;
    private TextView mTextViewMealTime;
    private TextView mTextViewEnergySum;
    private ListView mListViewMeals;
    /*
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
        final View view = inflater.inflate(R.layout.fragment_top_static, container, false);
        if (view != null) {
            mRelativeLayoutNextTraining = (RelativeLayout) view.findViewById(
                    R.id.fragment_top_static_relativelayout_training);
            mRelativeLayoutNextTraining.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(view.getContext(), "to the trains...", Toast.LENGTH_LONG).show();
                }
            });
            mImageViewSettingsNextTraining = (ImageView) view.findViewById(
                    R.id.fragment_top_cardview_linearlayout_imageview_cardsettings);
            mImageViewSettingsNextTraining.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(view.getContext(), "show training settings...", Toast.LENGTH_LONG).show();
                }
            });
            mTextViewTrainingName = (TextView) view.findViewById(
                    R.id.fragment_top_static_relativelayout_training_textview_trainingname);
            //TODO: here we should add the name of the next training
            mTextViewTrainingDay = (TextView) view.findViewById(
                    R.id.fragment_top_static_relativelayout_training_textview_trainingday);
            //TODO: here we should add the day of the next training
            mTextViewNumberOfExercises = (TextView) view.findViewById(
                    R.id.fragment_top_static_relativelayout_training_textview_numberofexercices);
            //TODO: here we should add the number of exercises
            mTextViewLastTimeExercised = (TextView) view.findViewById(
                    R.id.fragment_top_static_relativelayout_training_textview_lasttimeexercised);
            //TODO: here we should add the last time exercised
            mTextViewTrainingTime = (TextView) view.findViewById(
                    R.id.fragment_top_static_relativelayout_training_textview_trainingtime);
            //TODO: here we should add the training time
            mRelativeLayoutNextMeal = (RelativeLayout) view.findViewById(
                    R.id.fragment_top_static_relativelayout_meal);
            mRelativeLayoutNextMeal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(view.getContext(), "to the meals...", Toast.LENGTH_LONG).show();
                }
            });
            mImageViewSettingsNextMeal = (ImageView) view.findViewById(
                    R.id.fragment_top_static_relativelayout_meal_relativelayout_cardsettings);
            mImageViewSettingsNextMeal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(view.getContext(), "show meal settings...", Toast.LENGTH_LONG).show();
                }
            });
            mTextViewMealName = (TextView) view.findViewById(
                    R.id.fragment_top_static_relativelayout_meal_textview_mealname);
            //TODO: here we should add the name of the next meal
            mTextViewEnergySum = (TextView) view.findViewById(
                    R.id.fragment_top_static_relativelayout_meal_textview_energysum);
            //TODO: here we should add the energy amount of the meal
            mListViewMeals = (ListView) view.findViewById(
                    R.id.fragment_top_static_relativelayout_meal_listview_meals);
            //TODO: maybe later the list gets more complex (adding more infos e.g. weight,
            // energy of single items), so we have to change the adapter
            mListViewMeals.setAdapter(new ArrayAdapter<String>(
                    view.getContext(), R.layout.fragment_top_static_meal_listitem, createMeals()));
            /*
            mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_top_recyclerview);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(view.getContext());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new TopAdapter_OLD(createTopAdapterModels());//TODO: debugging, change to real data when possible
            mRecyclerView.setAdapter(mAdapter);
            */
            /*
            imageView = (ImageView) view.findViewById(R.id.fragment_top_imageview_debug);
            Picasso.with(view.getContext()).setLoggingEnabled(true);
            Picasso.with(view.getContext())
                    .load("http://4.bp.blogspot.com/-a77bEtz0S48/USJ5cZL_ByI/AAAAAAAAFD8/LHJ_JWbYCOU/s1600/Arnold+Schwarzenegger+0.jpg")
                    .into(imageView);

            mCircularImageViewTraining.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    replaceFragment(new TrainingFragmentExerciseList());
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
     * Little mock method for the meal list
     *
     * @return list of meals
     */
    private List<String> createMeals() {
        List<String> meals = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            meals.add("Meal "+i);
        }
        return meals;
    }
}

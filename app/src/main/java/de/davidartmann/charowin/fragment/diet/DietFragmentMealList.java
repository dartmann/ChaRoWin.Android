package de.davidartmann.charowin.fragment.diet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import de.davidartmann.charowin.R;
import de.davidartmann.charowin.adapter.diet.DietFragmentMealListAdapter;
import de.davidartmann.charowin.adapter.diet.model.MealAdapterModel;

/**
 * Adapter class for the meal list.
 *
 * Created by David on 05.10.2015.
 */
public class DietFragmentMealList extends Fragment implements View.OnClickListener {

    private static final String DIET_FRAGMENT_MEALLIST =
            DietFragmentMealList.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //TODO: just for testing, delete afterwards:
        List<MealAdapterModel> mealAdapterModels = createMockupMeals();
        View view = inflater.inflate(R.layout.fragment_diet_meallist, container, false);
        RecyclerView mRecyclerView =
                (RecyclerView) view.findViewById(R.id.fragment_diet_meallist_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.Adapter mAdapter = new DietFragmentMealListAdapter(mealAdapterModels);
        mRecyclerView.setAdapter(mAdapter);
        FloatingActionButton fab =
                (FloatingActionButton) view.findViewById(R.id.fragment_diet_meallist_floatingactionbutton);
        fab.setOnClickListener(this);
        return view;
    }

    private List<MealAdapterModel> createMockupMeals() {
        List<MealAdapterModel> mealAdapterModels = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            MealAdapterModel mealAdapterModel;
            switch (i) {
                case 0:
                    mealAdapterModel = new MealAdapterModel(
                            "http://www.ahpeel.com/images/collection/banana.jpg",
                            "Essen" + i,
                            "08:00",
                            String.valueOf(100 * i));
                    break;
                case 1:
                    mealAdapterModel = new MealAdapterModel(
                            "http://www.jazzybonez.com/images/meals.jpg",
                            "Essen" + i,
                            "08:00",
                            String.valueOf(100 * i));
                    break;
                case 2:
                    mealAdapterModel = new MealAdapterModel(
                            "http://sandiegobargainmama.com/wp-content/uploads/2011/09/balanced-meal.png",
                            "Essen" + i,
                            "08:00",
                            String.valueOf(100 * i));
                    break;
                case 3:
                    mealAdapterModel = new MealAdapterModel(
                            "http://www.beginwithinnutrition.com/wp-content/uploads/2014/02/sqaure1.jpg?w=490",
                            "Essen" + i,
                            "08:00",
                            String.valueOf(100 * i));
                    break;
                case 4:
                    mealAdapterModel = new MealAdapterModel(
                            "http://i.livescience.com/images/i/000/052/900/i02/chicken-meal-120831.jpg",
                            "Essen" + i,
                            "08:00",
                            String.valueOf(100 * i));
                    break;
                case 5:
                    mealAdapterModel = new MealAdapterModel(
                            "http://www.fitbodybistro.com/wp-content/uploads/2013/06/shutterstock_99620972.jpg",
                            "Essen" + i,
                            "08:00",
                            String.valueOf(100 * i));
                    break;
                default:
                    mealAdapterModel = new MealAdapterModel(
                            "http://www.ahpeel.com/images/collection/banana.jpg",
                            "Essen" + i,
                            "08:00",
                            String.valueOf(100 * i));
            }
            mealAdapterModels.add(mealAdapterModel);
        }
        return mealAdapterModels;
    }

    @Override
    public void onClick(View v) {
        Log.d(DIET_FRAGMENT_MEALLIST, "FAB click");
        //TODO: show dialog to save new meal
    }
}

package de.davidartmann.charowin.fragment.diet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import de.davidartmann.charowin.R;
import de.davidartmann.charowin.adapter.diet.DietFragmentMealListAdapter;
import de.davidartmann.charowin.adapter.diet.model.Meal;
import de.davidartmann.charowin.util.CustomSnackBar;

/**
 * Adapter class for the meal list.
 *
 * Created by David on 05.10.2015.
 */
public class DietFragmentMealList extends Fragment {

//    private static final String DIET_FRAGMENT_MEALLIST =
//            DietFragmentMealList.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //TODO: just for testing, delete afterwards:
        List<Meal> meals = createMockupMeals();
        View view = inflater.inflate(R.layout.fragment_diet_meallist, container, false);
        RecyclerView mRecyclerView =
                (RecyclerView) view.findViewById(R.id.fragment_diet_meallist_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.Adapter mAdapter = new DietFragmentMealListAdapter(meals);
        mRecyclerView.setAdapter(mAdapter);
        FloatingActionButton fab =
                (FloatingActionButton) view.findViewById(R.id.fragment_diet_meallist_floatingactionbutton);
        fab.setOnClickListener(new FabClickListener());
        ImageView imageViewSettings =
                (ImageView) view.findViewById(R.id.fragment_diet_meallist_cardview_imageview_settings);
        imageViewSettings.setOnClickListener(new ImageViewSettingsClickListener());
        return view;
    }

    private List<Meal> createMockupMeals() {
        List<Meal> meals = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            Meal meal;
            switch (i) {
                case 0:
                    meal = new Meal(
                            "http://www.ahpeel.com/images/collection/banana.jpg",
                            "Essen" + i,
                            "08:00",
                            String.valueOf(100 * i));
                    break;
                case 1:
                    meal = new Meal(
                            "http://www.jazzybonez.com/images/meals.jpg",
                            "Essen" + i,
                            "08:00",
                            String.valueOf(100 * i));
                    break;
                case 2:
                    meal = new Meal(
                            "http://sandiegobargainmama.com/wp-content/uploads/2011/09/balanced-meal.png",
                            "Essen" + i,
                            "08:00",
                            String.valueOf(100 * i));
                    break;
                case 3:
                    meal = new Meal(
                            "http://www.beginwithinnutrition.com/wp-content/uploads/2014/02/sqaure1.jpg?w=490",
                            "Essen" + i,
                            "08:00",
                            String.valueOf(100 * i));
                    break;
                case 4:
                    meal = new Meal(
                            "http://i.livescience.com/images/i/000/052/900/i02/chicken-meal-120831.jpg",
                            "Essen" + i,
                            "08:00",
                            String.valueOf(100 * i));
                    break;
                case 5:
                    meal = new Meal(
                            "http://www.fitbodybistro.com/wp-content/uploads/2013/06/shutterstock_99620972.jpg",
                            "Essen" + i,
                            "08:00",
                            String.valueOf(100 * i));
                    break;
                default:
                    meal = new Meal(
                            "http://www.ahpeel.com/images/collection/banana.jpg",
                            "Essen" + i,
                            "08:00",
                            String.valueOf(100 * i));
            }
            meals.add(meal);
        }
        return meals;
    }

    private class ImageViewSettingsClickListener implements View.OnClickListener {

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            CustomSnackBar.create(v, "Einstellungen zeigen", null, null);
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
            //TODO: show dialog to save new meal
        }
    }
}

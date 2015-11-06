package de.davidartmann.charowin.fragment.diet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import de.davidartmann.charowin.R;
import de.davidartmann.charowin.adapter.diet.DietFragmentDietplanListAdapter;
import de.davidartmann.charowin.adapter.diet.model.DietplanAdapterModel;

/**
 * Adapter class for the dietplan list.
 *
 * Created by David on 05.10.2015.
 */
public class DietFragmentDietplanList extends Fragment {

    private static final String DIET_FRAGMENT_DIETPLANLIST =
            DietFragmentDietplanList.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diet_dietplanlist, container, false);
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_diet_dietplanlist_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        List<DietplanAdapterModel> dietplanAdapterModels = createDietPlans();
        RecyclerView.Adapter mAdapter =
                new DietFragmentDietplanListAdapter(dietplanAdapterModels,
                        R.layout.fragment_diet_dietplanlist_cardlayout);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    //TODO: replace this with data from the database
    private List<DietplanAdapterModel> createDietPlans() {
        List<DietplanAdapterModel> dietplanAdapterModels = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            DietplanAdapterModel dietplanAdapterModel =
                    new DietplanAdapterModel("Sommerfigur", "Plan für die Sommerfigur", "2", "5", "2500", false);
            dietplanAdapterModels.add(dietplanAdapterModel);
        }
        return dietplanAdapterModels;
    }
}

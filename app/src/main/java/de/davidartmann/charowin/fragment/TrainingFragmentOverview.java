package de.davidartmann.charowin.fragment;

import android.app.ActionBar;
import android.app.Activity;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.davidartmann.charowin.R;
import de.davidartmann.charowin.adapter.TrainingFragmentExerciseListAdapter;
import de.davidartmann.charowin.adapter.TrainingFragmentOverviewAdapter;
import de.davidartmann.charowin.adapter.model.TrainingFragmentOverviewItem;
import de.davidartmann.charowin.fragment.contract.TrainingFragmentOverviewRecyclerClickListener;

/**
 * Main entrance point when the "Training" item is clicked in the Drawer.
 *
 * Created by David on 01.10.2015.
 */
public class TrainingFragmentOverview extends Fragment
        implements TrainingFragmentOverviewRecyclerClickListener {

    private static final String TRAINING_FRAGMENT_OVERVIEW = TrainingFragmentOverview.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] mTitles;
    private List<TrainingFragmentOverviewItem> items;

    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     * <p/>
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        mTitles = getResources().getStringArray(R.array.fragment_training_overview_titles);
        View view = inflater.inflate(R.layout.fragment_training_overview, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_training_overview_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        items = createItems();
        mAdapter = new TrainingFragmentOverviewAdapter(items, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "click", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    /**
     * Helper method to create the static list of the trainings overview.
     *
     * @return List of {@link TrainingFragmentOverviewItem}
     */
    private List<TrainingFragmentOverviewItem> createItems() {
        List<TrainingFragmentOverviewItem> items = new ArrayList<>();
        TrainingFragmentOverviewItem item = null;
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    item = new TrainingFragmentOverviewItem(R.drawable.ic_grade_black_48dp, mTitles[i]);
                    break;
                case 1:
                    item = new TrainingFragmentOverviewItem(R.drawable.ic_assignment_black_48dp, mTitles[i]);
                    break;
                case 2:
                    item = new TrainingFragmentOverviewItem(R.drawable.ic_event_note_black_48dp, mTitles[i]);
                    break;
                case 3:
                    item = new TrainingFragmentOverviewItem(R.drawable.ic_insert_chart_black_48dp, mTitles[i]);
                    break;
                default:
                    Log.w(TRAINING_FRAGMENT_OVERVIEW, "Default path of createItems()");
            }
            items.add(item);
        }
        return items;
    }

    /**
     * Helper method to call #getFragmentManager and replace a given Fragment
     * @param fragment the Fragment which will be replacing the actual
     */
    private void replaceFragment(Fragment fragment) {
        getFragmentManager()
            .beginTransaction()
            .replace(R.id.drawer_frame_layout, fragment)
            /**
             * TODO: maybe here we want to add old fragment to backstack?!
             *
             .addToBackStack(null)
             */
            .disallowAddToBackStack()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit();
    }

    @Override
    public void recyclerViewListClicked(View view, int position) {
        Log.w(TRAINING_FRAGMENT_OVERVIEW, String.valueOf(position));
        setTitle(position);
        selectItem(position);
    }

    /**
     * Helper method to replace the actual Fragment with the new one which was clicked.
     *
     * @param position of the item which was clicked
     */
    public void selectItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new TrainingFragmentExerciseList();
                break;
            case 1:
                //TODO
                fragment = new TrainingFragmentExerciseList();
                break;
            case 2:
                //TODO
                fragment = new TrainingFragmentExerciseList();
                break;
            case 3:
                //TODO
                fragment = new TrainingFragmentExerciseList();
                break;
            default:
                Log.w(TRAINING_FRAGMENT_OVERVIEW, "Default path in recyclerViewListClicked()");
                fragment = new TrainingFragmentExerciseList();
        }
        replaceFragment(fragment);
    }

    /**
     * Helper method to set the Title
     *
     * @param position of the item which was clicked
     */
    private void setTitle(int position) {
        Activity activity = getActivity();
        if (activity != null) {
            ActionBar actionBar = activity.getActionBar();
            if (actionBar != null) {
                actionBar.setTitle(mTitles[position]);
            } else {
                Log.w(TRAINING_FRAGMENT_OVERVIEW, "ActionBar is null");
            }
        } else {
            Log.w(TRAINING_FRAGMENT_OVERVIEW, "Activity is null");
        }
    }
}

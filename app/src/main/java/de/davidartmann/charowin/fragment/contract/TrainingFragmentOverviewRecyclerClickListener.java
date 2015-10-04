package de.davidartmann.charowin.fragment.contract;

import android.view.View;

/**
 * Interface for getting the information about the clicked item of the adapter to fragment.
 *
 * Created by David on 04.10.2015.
 */
public interface TrainingFragmentOverviewRecyclerClickListener {
    void recyclerViewListClicked(View view, int position);
}

package de.davidartmann.charowin.adapter;

import android.app.Fragment;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import de.davidartmann.charowin.R;
import de.davidartmann.charowin.adapter.model.TrainingFragmentOverviewItem;
import de.davidartmann.charowin.fragment.TrainingFragmentOverview;
import de.davidartmann.charowin.fragment.contract.TrainingFragmentOverviewRecyclerClickListener;

/**
 * Adapter class for the training overview.
 *
 * Created by David on 01.10.2015.
 */
public class TrainingFragmentOverviewAdapter
        extends RecyclerView.Adapter<TrainingFragmentOverviewAdapter.ViewHolder> {

    private static final String TRAINING_FRAGMENT_OVERVIEW_ADAPTER =
            TrainingFragmentOverviewAdapter.class.getSimpleName();

    private List<TrainingFragmentOverviewItem> trainingFragmentOverviewItems;
    private static TrainingFragmentOverviewRecyclerClickListener listener;

    public TrainingFragmentOverviewAdapter(List<TrainingFragmentOverviewItem> items,
                                           TrainingFragmentOverviewRecyclerClickListener listener) {
        trainingFragmentOverviewItems = items;
        TrainingFragmentOverviewAdapter.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTextView;
        public ImageView mImageView;
        private Context context;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.fragment_training_overview_textview);
            mImageView = (ImageView) view.findViewById(R.id.fragment_training_overview_imageview);
            context = view.getContext();
            view.setOnClickListener(this);
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            listener.recyclerViewListClicked(v, getLayoutPosition());
        }
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method
     * should update the contents of the {@link ViewHolder#itemView} to reflect the item at
     * the given position.
     * <p/>
     * Note that unlike {@link ListView}, RecyclerView will not call this
     * method again if the position of the item changes in the data set unless the item itself
     * is invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside this
     * method and should not keep a copy of it. If you need the position of an item later on
     * (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will have
     * the updated adapter position.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        TrainingFragmentOverviewItem item = trainingFragmentOverviewItems.get(position);
        holder.mImageView.setImageResource(item.getIcon());
        holder.mTextView.setText(item.getText());
        /*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        Toast.makeText(v.getContext(), "Aktuelles Training öffnen...", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(v.getContext(), "Trainingspläne öffnen...", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(v.getContext(), "Kalender öffnen...", Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(v.getContext(), "Statistik öffnen...", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(v.getContext(), "DEFAULT!!!", Toast.LENGTH_LONG).show();
                }
//                Toast.makeText(v.getContext(), String.valueOf(position), Toast.LENGTH_LONG).show();
            }
        });
        */
    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p/>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p/>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(ViewHolder, int)}. Since it will be re-used to display different
     * items in the data set, it is a good idea to cache references to sub views of the View to
     * avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_training_overview_cardlayout, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return trainingFragmentOverviewItems.size();
    }
}

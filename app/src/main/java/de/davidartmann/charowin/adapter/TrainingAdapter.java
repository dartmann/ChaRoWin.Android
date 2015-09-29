package de.davidartmann.charowin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.davidartmann.charowin.R;
import de.davidartmann.charowin.model.Exercise;

/**
 * Created by David on 26.09.2015.
 */
public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.TrainingViewHolder> {

    private List<Exercise> exercises;

    public TrainingAdapter(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public static class TrainingViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView mImageView;
        private Context context;

        public TrainingViewHolder(View view) {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.fragment_training_cardview_linearlayout_imageview);
            mTextView = (TextView) view.findViewById(R.id.fragment_training_cardview_linearlayout_textview);
            context = view.getContext();
        }
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method
     * should update the contents of the
     * {@link android.support.v7.widget.RecyclerView.ViewHolder#itemView}
     * to reflect the item at the given position.
     * <p/>
     * Note that unlike {@link ListView}, RecyclerView will not call this
     * method again if the position of the item changes in the data set unless the item itself
     * is invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside this
     * method and should not keep a copy of it. If you need the position of an item later on
     * (e.g. in a click listener), use
     * {@link android.support.v7.widget.RecyclerView.ViewHolder#getAdapterPosition()}
     * which will have the updated adapter position.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(TrainingViewHolder holder, int position) {
        Exercise exercise = exercises.get(position);
        holder.mTextView.setText(exercise.getName());
        //TODO
//        Picasso.with(holder.context).setLoggingEnabled(true);
//        Picasso.with(holder.context)
//                .load(exercise.getImageUrl())
//                .placeholder(android.R.drawable.ic_menu_add)
//                .error(android.R.drawable.ic_menu_delete)
//                .into(holder.mImageView);
    }

    /**
     * Called when RecyclerView needs a new
     * {@link de.davidartmann.charowin.adapter.TrainingAdapter.TrainingViewHolder}
     * of the given type to represent an item.
     * <p/>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p/>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(TrainingViewHolder, int)}. Since it will be re-used to display different
     * items in the data set, it is a good idea to cache references to sub views of the View to
     * avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     */
    @Override
    public TrainingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_training_cardlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters...
        return new TrainingViewHolder(view);
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return exercises.size();
    }
}

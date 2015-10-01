package de.davidartmann.charowin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import de.davidartmann.charowin.R;
import de.davidartmann.charowin.model.TopAdapterModel;

/**
 * Adapter class for the {@link de.davidartmann.charowin.fragment.TopFragment}
 *
 * Created by David on 29.09.2015.
 */
public class TopAdapter_OLD extends RecyclerView.Adapter<TopAdapter_OLD.TopViewHolder> {

    private List<TopAdapterModel> topAdapterModels;

    public TopAdapter_OLD(List<TopAdapterModel> topAdapterModels) {
        this.topAdapterModels = topAdapterModels;
    }

    public static class TopViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitleTextView;
        public ImageView mMoveVerticalImageView;
        public TextView mTrainingNameTextView;
        public TextView mTrainingsDayTextView;
        public TextView mAmountExercisesTextView;
        public ImageView mCardSettingsImageView;
        public TextView mTimeSinceLastExerciseTextView;
        public ImageView mAverageTrainingTimeImageView;
        public TextView mAverageTrainingsTimeTextView;
        public Context context;

        public TopViewHolder(View view) {
            super(view);
            mTitleTextView = (TextView) view.findViewById(R.id.fragment_top_cardview_linearlayout_textview_title);
            mMoveVerticalImageView = (ImageView) view.findViewById(R.id.fragment_top_cardview_linearlayout_imageview_moveverticalicon);
            mTrainingNameTextView = (TextView) view.findViewById(R.id.fragment_top_cardview_linearlayout_textview_trainingname);
            mTrainingsDayTextView = (TextView) view.findViewById(R.id.fragment_top_cardview_linearlayout_textview_trainingday);
            mAmountExercisesTextView = (TextView) view.findViewById(R.id.fragment_top_cardview_linearlayout_textview_amountexercices);
            mCardSettingsImageView = (ImageView) view.findViewById(R.id.fragment_top_cardview_linearlayout_imageview_cardsettings);
            mTimeSinceLastExerciseTextView = (TextView) view.findViewById(R.id.fragment_top_cardview_linearlayout_textview_lastexercisedate);
            mAverageTrainingTimeImageView = (ImageView) view.findViewById(R.id.fragment_top_cardview_linearlayout_imageview_averagetrainingtimeicon);
            mAverageTrainingsTimeTextView = (TextView) view.findViewById(R.id.fragment_top_cardview_linearlayout_textview_averagetrainingtimetext);
            context = view.getContext();
        }
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method
     * should update the contents of the {@link TopViewHolder#itemView} to reflect the item at
     * the given position.
     * <p/>
     * Note that unlike {@link ListView}, RecyclerView will not call this
     * method again if the position of the item changes in the data set unless the item itself
     * is invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside this
     * method and should not keep a copy of it. If you need the position of an item later on
     * (e.g. in a click listener), use {@link TopViewHolder#getAdapterPosition()} which will have
     * the updated adapter position.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(TopViewHolder holder, int position) {
        TopAdapterModel topAdapterModel = topAdapterModels.get(position);
        holder.mTitleTextView.setText(topAdapterModel.getTitle());
//        holder.mMoveVerticalImageView.setImageResource(topAdapterModel.getMoveVertivalImageResourceId());
        holder.mTrainingNameTextView.setText(topAdapterModel.getTrainingOrMealName());
        holder.mTrainingsDayTextView.setText(topAdapterModel.getTrainingOrMealDay());
        holder.mAmountExercisesTextView.setText(topAdapterModel.getAmountExercisesOrEnergyValue());
//        holder.mCardSettingsImageView.setImageResource(topAdapterModel.getCardSettingsImageResourceId());
        holder.mTimeSinceLastExerciseTextView.setText(topAdapterModel.getTimeSinceLastExercise());
//        holder.mAverageTrainingTimeImageView.setImageResource(topAdapterModel.getAverageTrainingsImageResourceId());
        holder.mAverageTrainingsTimeTextView.setText(topAdapterModel.getAverageTrainingsTime());
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return topAdapterModels.size();
    }

    /**
     * Called when RecyclerView needs a new {@link TopViewHolder} of the given type to represent
     * an item.
     * <p/>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p/>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(TopViewHolder, int)}. Since it will be re-used to display different
     * items in the data set, it is a good idea to cache references to sub views of the View to
     * avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(TopViewHolder, int)
     */
    @Override
    public TopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_top_cardlayout, parent, false);
        return new TopViewHolder(view);
    }
}

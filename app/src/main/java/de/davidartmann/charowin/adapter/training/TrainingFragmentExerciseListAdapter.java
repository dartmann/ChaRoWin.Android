package de.davidartmann.charowin.adapter.training;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.davidartmann.charowin.R;
import de.davidartmann.charowin.adapter.training.model.ExerciseAdapterModel;
import de.davidartmann.charowin.util.CustomSnackBar;

/**
 * Adapter class for the list of exercise of a trainingsplan.
 *
 * Created by David on 26.09.2015.
 */
public class TrainingFragmentExerciseListAdapter extends RecyclerView.Adapter<TrainingFragmentExerciseListAdapter.ViewHolder> {

    private static final String TRAINING_FRAGMENT_EXERCISELIST_ADAPTER =
            TrainingFragmentExerciseListAdapter.class.getSimpleName();

    private List<ExerciseAdapterModel> exerciseAdapterModels;

    public TrainingFragmentExerciseListAdapter(List<ExerciseAdapterModel> exerciseAdapterModels) {
        this.exerciseAdapterModels = exerciseAdapterModels;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mImageViewExerciseIcon;
        public TextView mTextViewExerciseName;
        public TextView mTextViewSets;
        public TextView mTextViewReps;
        public TextView mTextViewRestTime;
        private Context context;
        private ImageView mImageViewSettings;

        public ViewHolder(View view) {
            super(view);
            mImageViewExerciseIcon = (ImageView) view.findViewById(R.id.fragment_training_exerciselist_cardlayout_imageview_exerciseicon);
            mTextViewExerciseName = (TextView) view.findViewById(R.id.fragment_training_exerciselist_cardlayout_textview_exercisename);
            mTextViewSets = (TextView) view.findViewById(R.id.fragment_training_exerciselist_cardlayout_textview_sets);
            mTextViewReps = (TextView) view.findViewById(R.id.fragment_training_exerciselist_cardlayout_textview_reps);
            mTextViewRestTime = (TextView) view.findViewById(R.id.fragment_training_exerciselist_cardlayout_textview_resttime);
            context = view.getContext();
            mImageViewSettings = (ImageView) view.findViewById(R.id.fragment_training_exerciselist_cardlayout_imageview_settings);
            mImageViewSettings.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fragment_training_exerciselist_cardlayout_imageview_settings:
                    CustomSnackBar.create(v, "Einstellungen zeigen", null, null);
                    break;
                default:
                    Log.d(TRAINING_FRAGMENT_EXERCISELIST_ADAPTER, "Default path in onClick()");
            }
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        ExerciseAdapterModel exerciseAdapterModel = exerciseAdapterModels.get(position);
        Picasso.with(holder.context)
                .load(exerciseAdapterModel.getImageUrl())
                .placeholder(android.R.drawable.ic_menu_add)
                .error(android.R.drawable.ic_menu_delete)
                .into(holder.mImageViewExerciseIcon);
        holder.mTextViewExerciseName.setText(exerciseAdapterModel.getExerciseName());
        holder.mTextViewSets.setText(exerciseAdapterModel.getSets());
        holder.mTextViewReps.setText(exerciseAdapterModel.getReps());
        holder.mTextViewRestTime.setText(exerciseAdapterModel.getRestTime());
    }

    /**
     * Called when RecyclerView needs a new
     * {@link ViewHolder}
     * of the given type to represent an item.
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
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_training_exerciselist_cardlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters...
        return new ViewHolder(view);
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return exerciseAdapterModels.size();
    }
}

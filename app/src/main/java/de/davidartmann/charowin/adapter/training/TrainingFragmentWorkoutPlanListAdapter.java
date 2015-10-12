package de.davidartmann.charowin.adapter.training;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import de.davidartmann.charowin.R;
import de.davidartmann.charowin.adapter.training.model.WorkoutPlan;
import de.davidartmann.charowin.util.CustomSnackBar;

/**
 * Adapter class for the list of exercise of a trainingsplan.
 *
 * Created by David on 08.10.2015.
 */
public class TrainingFragmentWorkoutPlanListAdapter extends RecyclerView.Adapter<TrainingFragmentWorkoutPlanListAdapter.ViewHolder> {

    private static final String TRAINING_FRAGMENT_WORKOUT_PLANLIST_ADAPTER =
            TrainingFragmentWorkoutPlanListAdapter.class.getSimpleName();

    private List<WorkoutPlan> workoutPlans;

    public TrainingFragmentWorkoutPlanListAdapter(List<WorkoutPlan> workoutPlans) {
        this.workoutPlans = workoutPlans;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextViewName;
        private TextView mTextViewDescription;
        private TextView mTextViewAmountDays;
        private Context context;
        private ImageView mImageViewSettings;
        private ImageView mImageViewPinAsCurrent;
        private LinearLayout mLinearLayoutCard;

        public ViewHolder(View view) {
            super(view);
            mTextViewName = (TextView) view.findViewById(R.id.fragment_training_workoutplanlist_cardview_textview_workoutplanname);
            mTextViewDescription = (TextView) view.findViewById(R.id.fragment_training_workoutplanlist_cardview_textview_workoutdescription);
            mTextViewAmountDays = (TextView) view.findViewById(R.id.fragment_training_workoutplanlist_cardview_textview_amountdays);
            context = view.getContext();
            mImageViewSettings =
                    (ImageView) view.findViewById(R.id.fragment_training_workoutplanlist_cardview_imageview_settings);
            mImageViewSettings.setOnClickListener(this);
            mImageViewPinAsCurrent =
                (ImageView) view.findViewById(R.id.fragment_training_workoutplanlist_cardview_imageview_pinascurrent);
            mImageViewPinAsCurrent.setOnClickListener(this);
            mLinearLayoutCard =
                (LinearLayout) view.findViewById(R.id.fragment_training_workoutplanlist_cardview_linearlayout_card);
            mLinearLayoutCard.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fragment_training_workoutplanlist_cardview_imageview_settings:
                    CustomSnackBar.create(v, "Einstellungen zeigen", null, null);
                    break;
                case R.id.fragment_training_workoutplanlist_cardview_imageview_pinascurrent:
                    //TODO: check if another plan is pinned as current, then reset its image first
                    mImageViewPinAsCurrent.setImageResource(R.drawable.ic_favorite_black_48dp);
                    break;
                case R.id.fragment_training_workoutplanlist_cardview_linearlayout_card:
                    CustomSnackBar.create(v, "Einzelnes Workout in DialogView zeigen?", null, null);
                    break;
                default:
                    Log.w(TRAINING_FRAGMENT_WORKOUT_PLANLIST_ADAPTER, "Default path in onCreate()");
            }
        }
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method
     * should update the contents of the
     * {@link RecyclerView.ViewHolder#itemView}
     * to reflect the item at the given position.
     * <p/>
     * Note that unlike {@link ListView}, RecyclerView will not call this
     * method again if the position of the item changes in the data set unless the item itself
     * is invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside this
     * method and should not keep a copy of it. If you need the position of an item later on
     * (e.g. in a click listener), use
     * {@link RecyclerView.ViewHolder#getAdapterPosition()}
     * which will have the updated adapter position.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WorkoutPlan workoutPlan = workoutPlans.get(position);
        holder.mTextViewName.setText(workoutPlan.getName());
        holder.mTextViewDescription.setText(workoutPlan.getDescription());
        holder.mTextViewAmountDays.setText(workoutPlan.getAmountDays());
        //TODO: check which plan is pinned as current or if none
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
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_training_workoutplanlist_cardlayout, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return workoutPlans.size();
    }
}

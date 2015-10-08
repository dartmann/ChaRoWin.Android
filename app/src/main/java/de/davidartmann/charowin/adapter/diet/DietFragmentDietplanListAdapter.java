package de.davidartmann.charowin.adapter.diet;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import de.davidartmann.charowin.R;
import de.davidartmann.charowin.adapter.diet.model.Dietplan;

/**
 * Adapter class for the list of dietplans of a trainingsplan.
 *
 * Created by David on 26.09.2015.
 */
public class DietFragmentDietplanListAdapter extends RecyclerView.Adapter<DietFragmentDietplanListAdapter.ViewHolder> {

    private List<Dietplan> dietplans;

    public DietFragmentDietplanListAdapter(List<Dietplan> dietplans) {
        this.dietplans = dietplans;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewDietplanName;
        public TextView mTextViewDescription;
        public TextView mTextViewAmountDays;
        public TextView mTextViewAmountMeals;
        public TextView mTextViewEnergyKcal;
        private Context context;

        public ViewHolder(View view) {
            super(view);
            mTextViewDietplanName = (TextView) view.findViewById(R.id.fragment_diet_dietplanlist_cardview_textview_dietplanname);
            mTextViewDescription = (TextView) view.findViewById(R.id.fragment_diet_dietplanlist_cardview_textview_description);
            mTextViewAmountDays = (TextView) view.findViewById(R.id.fragment_diet_dietplanlist_cardview_textview_amountdays);
            mTextViewAmountMeals = (TextView) view.findViewById(R.id.fragment_diet_dietplanlist_cardview_textview_amountmeals);
            mTextViewEnergyKcal = (TextView) view.findViewById(R.id.fragment_diet_dietplanlist_cardview_textview_energykcal);
            context = view.getContext();
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
        Dietplan dietplan = dietplans.get(position);
        holder.mTextViewDietplanName.setText(dietplan.getDietplanName());
        holder.mTextViewDescription.setText(dietplan.getDescription());
        holder.mTextViewAmountDays.setText(dietplan.getAmountDays());
        holder.mTextViewAmountMeals.setText(dietplan.getAmountMeals());
        holder.mTextViewEnergyKcal.setText(dietplan.getEnergyKcal());
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
                .inflate(R.layout.fragment_diet_dietplanlist_cardlayout, parent, false);
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
        return dietplans.size();
    }
}

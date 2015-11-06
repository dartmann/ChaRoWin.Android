package de.davidartmann.charowin.adapter.diet;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import de.davidartmann.charowin.R;
import de.davidartmann.charowin.adapter.diet.model.DietplanAdapterModel;
import de.davidartmann.charowin.util.CustomSnackBar;

/**
 * Adapter class for the list of dietplanAdapterModels of a trainingsplan.
 *
 * Created by David on 26.09.2015.
 */
public class DietFragmentDietplanListAdapter extends RecyclerView.Adapter<DietFragmentDietplanListAdapter.ViewHolder> {

    private static final String TAG =
            DietFragmentDietplanListAdapter.class.getSimpleName();

    private List<DietplanAdapterModel> dietplanAdapterModels;
    private int mResourceId;

    public DietFragmentDietplanListAdapter(List<DietplanAdapterModel> dietplanAdapterModels, int resourceId) {
        this.dietplanAdapterModels = dietplanAdapterModels;
        this.mResourceId = resourceId;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTextViewDietplanName;
        public TextView mTextViewDescription;
        public TextView mTextViewAmountDays;
        public TextView mTextViewAmountMeals;
        public TextView mTextViewEnergyKcal;
        public ImageView mImageViewPinAsCurrent;
        public ImageView mImageViewSettings;
        public CardView mCardView;
        private Context context;

        public ViewHolder(View view) {
            super(view);
            mTextViewDietplanName = (TextView) view.findViewById(R.id.fragment_diet_dietplanlist_cardview_textview_dietplanname);
            mTextViewDescription = (TextView) view.findViewById(R.id.fragment_diet_dietplanlist_cardview_textview_description);
            mTextViewAmountDays = (TextView) view.findViewById(R.id.fragment_diet_dietplanlist_cardview_textview_amountdays);
            mTextViewAmountMeals = (TextView) view.findViewById(R.id.fragment_diet_dietplanlist_cardview_textview_amountmeals);
            mTextViewEnergyKcal = (TextView) view.findViewById(R.id.fragment_diet_dietplanlist_cardview_textview_energykcal);
            mImageViewPinAsCurrent = (ImageView) view.findViewById(R.id.fragment_diet_dietplanlist_cardview_imagebutton_pinascurrent);
            mImageViewPinAsCurrent.setOnClickListener(this);
            mImageViewSettings = (ImageView) view.findViewById(R.id.fragment_diet_dietplanlist_cardview_imageview_settings);
            mImageViewSettings.setOnClickListener(this);
            mCardView = (CardView) view.findViewById(R.id.fragment_diet_dietplanlist_cardview);
            mCardView.setOnClickListener(this);
            context = view.getContext();
        }

        public void assignData(DietplanAdapterModel dietplanAdapterModel) {
            mTextViewDietplanName.setText(dietplanAdapterModel.getDietplanName());
            mTextViewDescription.setText(dietplanAdapterModel.getDescription());
            mTextViewAmountDays.setText(dietplanAdapterModel.getAmountDays());
            mTextViewAmountMeals.setText(dietplanAdapterModel.getAmountMeals());
            mTextViewEnergyKcal.setText(dietplanAdapterModel.getEnergyKcal());
            if (dietplanAdapterModel.isCurrentFavorite()) {
                mImageViewPinAsCurrent.setImageResource(R.drawable.ic_favorite_black_48dp);
            } else {
                mImageViewPinAsCurrent.setImageResource(R.drawable.ic_favorite_border_black_48dp);
            }
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fragment_diet_dietplanlist_cardview_imageview_settings:
                    CustomSnackBar.create(v, "Einstellungen zeigen", null, null);
                    break;
                case R.id.fragment_diet_dietplanlist_cardview_imagebutton_pinascurrent:
                    checkOtherDietplansForFavoriteTagAndResetIfTagged(getAdapterPosition());
                    break;
                case R.id.fragment_diet_dietplanlist_cardview:
                    CustomSnackBar.create(v, "show detailview of dietplan", null, null);
                    break;
                default:
                    Log.w(TAG, "Default path in onClick()");
                    break;
            }
        }
    }

    private void checkOtherDietplansForFavoriteTagAndResetIfTagged(int adapterPosition) {
        for (DietplanAdapterModel model : dietplanAdapterModels) {
            if (model.isCurrentFavorite()) {
                model.setCurrentFavorite(false);
            }
        }
        dietplanAdapterModels.get(adapterPosition).setCurrentFavorite(true);
        notifyDataSetChanged();
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
        holder.assignData(dietplanAdapterModels.get(position));
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
                .inflate(mResourceId, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return dietplanAdapterModels.size();
    }
}

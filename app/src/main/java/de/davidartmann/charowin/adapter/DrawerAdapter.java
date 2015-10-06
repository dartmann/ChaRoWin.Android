package de.davidartmann.charowin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import de.davidartmann.charowin.R;
import de.davidartmann.charowin.adapter.model.DrawerItem;

/**
 * Custom adapter class for the NavigationDrawer.
 *
 * Created by David on 28.09.2015.
 */
public class DrawerAdapter extends ArrayAdapter<DrawerItem> {

    private static final String DRAWER_ADAPTER = DrawerAdapter.class.getSimpleName();

    private final Context context;
    private final int layoutResourceId;
    private List<DrawerItem> drawerItems;

    public DrawerAdapter(Context context, int layoutResourceId, List<DrawerItem> drawerItems) {
        super(context, layoutResourceId, drawerItems);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.drawerItems = drawerItems;
    }

    /**
     * {@inheritDoc}
     *
     * @param position
     * @param convertView
     * @param parent
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (position == 0) {
            view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.drawer_list_item_firstitem, parent, false);
            ImageView circularImageView =
                    (ImageView) view.findViewById(
                            R.id.drawer_list_item_test_firstitem_circularimageview);
            TextView drawerHeaderUserWeight =
                    (TextView) view.findViewById(R.id.drawer_list_item_test_firstitem_textview_userweight);
            TextView drawerHeaderUserBodyFat =
                    (TextView) view.findViewById(R.id.drawer_list_item_test_firstitem_textview_userbodyfat);
            TextView lastSyncTextView =
                    (TextView) view.findViewById(R.id.drawer_list_item_test_firstitem_textview_last_sync);
            circularImageView.setImageResource(drawerItems.get(position).getIcon());
            drawerHeaderUserWeight.setText("90.0");
            drawerHeaderUserBodyFat.setText("14.0");
            Date mockLastSyncDate = new Date();
//            Log.d(DRAWER_ADAPTER, String.valueOf(mockLastSyncDate.getTime()));
            lastSyncTextView.setText("Vor "+(mockLastSyncDate.getTime()%365)%24+" Stunden");
        } else {
            view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(layoutResourceId, parent, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.drawer_list_item_test_imageview);
            TextView textView = (TextView) view.findViewById(R.id.drawer_list_item_test_textview);
            imageView.setImageResource(drawerItems.get(position).getIcon());
            textView.setText(drawerItems.get(position).getText());
        }
        return view;
    }
}

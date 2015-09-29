package de.davidartmann.charowin.adapter;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;

import java.text.SimpleDateFormat;
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
                    .inflate(R.layout.drawer_list_item_test_firstitem, parent, false);
            CircularImageView circularImageView =
                    (CircularImageView) view.findViewById(
                            R.id.drawer_list_item_test_firstitem_circularimageview);
            TextView drawerHeaderUserInfoTextView =
                    (TextView) view.findViewById(R.id.drawer_list_item_test_firstitem_textview_userdata);
            ImageView imageView =
                    (ImageView) view.findViewById(R.id.drawer_list_item_test_firstitem_imageview_sync);
            TextView lastSyncTextView =
                    (TextView) view.findViewById(R.id.drawer_list_item_test_firstitem_textview_last_sync);
            circularImageView.setImageResource(drawerItems.get(position).getIcon());
            drawerHeaderUserInfoTextView.setText(drawerItems.get(position).getText());//TODO: this should be the username later...
            imageView.setImageResource(R.drawable.ic_autorenew_black_48dp);
            Date mockLastSyncDate = new Date();
            Log.d(DRAWER_ADAPTER, String.valueOf(mockLastSyncDate.getTime()));
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

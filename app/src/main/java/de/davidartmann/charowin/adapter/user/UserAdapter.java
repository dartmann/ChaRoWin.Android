package de.davidartmann.charowin.adapter.user;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.davidartmann.charowin.R;
import de.davidartmann.charowin.adapter.user.model.UserElements;

/**
 * Adapter class for the listview of the user view.
 *
 * Created by David on 06.10.2015.
 */
public class UserAdapter extends ArrayAdapter<UserElements> {

    private static final String USER_ADAPTER = UserAdapter.class.getSimpleName();

    private final Context context;
    private final int layoutResourceId;
    private List<UserElements> userElements;

    public UserAdapter(Context context, int layoutResourceId, List<UserElements> userElements) {
        super(context, layoutResourceId, userElements);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.userElements = userElements;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
//        ImageView icon = (ImageView) view.findViewById(R.id.fragment_user_list_item_imageview_icon);
//        CheckBox checkBox = (CheckBox) view.findViewById(R.id.fragment_user_list_item_checkbox);
        if (position == 0 || position == 4 || position == 8) {
            view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.fragment_user_list_item_delimiter, parent, false);
            TextView delimTitle = (TextView) view.findViewById(R.id.fragment_user_list_item_delimiter_textview_title);
            switch (position) {
                case 0:
                    delimTitle.setText("Profil");
                    break;
                case 4:
                    delimTitle.setText("Generelles");
                    break;
                case 8:
                    delimTitle.setText("Energiesparen");
                    break;
                default:
                    Log.w(USER_ADAPTER, "default path in getItem() of cases 0, 4 and 8");
            }
        } else {
            view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.fragment_user_list_item, parent, false);
            ImageView icon = (ImageView) view.findViewById(R.id.fragment_user_list_item_imageview_icon);
            TextView headline = (TextView) view.findViewById(R.id.fragment_user_list_item_textview_headline);
            TextView subHeadline = (TextView) view.findViewById(R.id.fragment_user_list_item_textview_subheadline);
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.fragment_user_list_item_checkbox);
            switch (position) {
                case 1:
                    headline.setText("Geschlecht");
                    subHeadline.setText("Männlich");
                    break;
                case 2:
                    headline.setText("Geburtsdatum");
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.YYYY");
                    subHeadline.setText(simpleDateFormat.format(new Date()));
                    break;
                case 3:
                    headline.setText("Privatssphäre");
                    break;
                case 5:
                    headline.setText("Sprache");
                    subHeadline.setText("Deutsch");
                    break;
                case 6:
                    headline.setText("Standard Pause Alarm");
                    subHeadline.setText("Nur Sound");
                    break;
                case 7:
                    headline.setText("Standard Einheit");
                    subHeadline.setText("cm / kg");
                    break;
                case 9:
                    headline.setText("Pause Bildschirm eingeschalten lassen");
                    subHeadline.setText("Ja");
                    checkBox.setChecked(true);
                    break;
                default:
                    Log.w(USER_ADAPTER, "default path in else path switch case");
            }
        }
        return view;
    }
}
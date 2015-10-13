package de.davidartmann.charowin.adapter.user;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
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
import java.util.Locale;

import de.davidartmann.charowin.R;
import de.davidartmann.charowin.adapter.user.model.UserElement;

/**
 * Adapter class for the listview of the user view.
 *
 * Created by David on 06.10.2015.
 */
public class UserAdapter extends ArrayAdapter<UserElement> {

    private static final String USER_ADAPTER = UserAdapter.class.getSimpleName();

    private final Context mContext;
    private final int layoutResourceId;
    private List<UserElement> mUserElements;

    public UserAdapter(Context context, int layoutResourceId, List<UserElement> userElements) {
        super(context, layoutResourceId, userElements);
        this.mContext = context;
        this.layoutResourceId = layoutResourceId;
        this.mUserElements = userElements;
    }

    /**
     * {@inheritDoc}
     *
     * @param position
     */
    @Override
    public UserElement getItem(int position) {
        UserElement userElement = null;
        if (mUserElements != null) {
            userElement = mUserElements.get(position);
        }
        return userElement;
    }

    public UserElement setValue(String value, int position) {
        UserElement userElement = null;
        if (value != null) {
            userElement = getItem(position);
            if (userElement != null) {
                mUserElements.remove(position);
                userElement.setSubHeadline(value);
                mUserElements.add(position, userElement);
                notifyDataSetChanged();
            } else {
                Log.w(USER_ADAPTER, "Invalid position in setValue()");
            }
        } else {
            Log.w(USER_ADAPTER, "Null value in setValue()");
        }
        return userElement;
    }

    /**
     * Returns the context associated with this array adapter. The context is used
     * to create views from the resource passed to the constructor.
     *
     * @return The Context associated with this adapter.
     */
    @Override
    public Context getContext() {
        Context context = null;
        if (mContext != null) {
            context = mContext;
        }
        return context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        UserElement userElement = mUserElements.get(position);
        Integer icon = null;
        String headline = "";
        String subHeadline = "";
        Boolean isCheckable = false;
        if (userElement.getHeadline() != null) {
            headline = userElement.getHeadline();
        }
        if (userElement.getSubHeadline() != null) {
            subHeadline = userElement.getSubHeadline();
        }
        if (userElement.isCheckableItem()) {
            isCheckable = userElement.isCheckableItem();
        }
        if (userElement.getIcon() != null) {
            icon = userElement.getIcon();
        }
        if (position == 0 || position == 4 || position == 8) {
            view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.fragment_user_list_item_delimiter, parent, false);
            TextView textViewDelimTitle = (TextView) view.findViewById(R.id.fragment_user_list_item_delimiter_textview_title);
            switch (position) {
                case 0:
                    textViewDelimTitle.setText(headline);
                    break;
                case 4:
                    textViewDelimTitle.setText(headline);
                    break;
                case 8:
                    textViewDelimTitle.setText(headline);
                    break;
                default:
                    Log.w(USER_ADAPTER, "default path in getItem() of cases 0, 4 and 8");
            }
        } else {
            view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.fragment_user_list_item, parent, false);
            ImageView imageViewIcon = (ImageView) view.findViewById(R.id.fragment_user_list_item_imageview_icon);
            TextView textViewHeadline = (TextView) view.findViewById(R.id.fragment_user_list_item_textview_headline);
            TextView textViewSubHeadline = (TextView) view.findViewById(R.id.fragment_user_list_item_textview_subheadline);
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.fragment_user_list_item_checkbox);
            checkBox.setVisibility(View.GONE);
            switch (position) {
                case 1:
                    textViewHeadline.setText(headline);
                    textViewSubHeadline.setText(userElement.getSubHeadline());
                    break;
                case 2:
                    textViewHeadline.setText(headline);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY);
                    if (!subHeadline.equals("")) {
                        textViewSubHeadline.setText(simpleDateFormat.format(new Date(Long.parseLong(subHeadline))));
                    }
                    break;
                case 3:
                    if (icon != null) {
                        imageViewIcon.setImageResource(icon);
                    }
                    textViewHeadline.setText(headline);
                    textViewSubHeadline.setText(subHeadline);
                    break;
                case 5:
                    textViewHeadline.setText(headline);
                    textViewSubHeadline.setText(subHeadline);
                    break;
                case 6:
                    textViewHeadline.setText(headline);
                    textViewSubHeadline.setText(subHeadline);
                    break;
                case 7:
                    textViewHeadline.setText(headline);
                    textViewSubHeadline.setText(subHeadline);
                    break;
                case 9:
                    textViewHeadline.setText(headline);
                    textViewSubHeadline.setText(subHeadline);
                    checkBox.setVisibility(View.VISIBLE);
                    checkBox.setChecked(isCheckable);
                    break;
                default:
                    Log.w(USER_ADAPTER, "default path in else path of switch case");
            }
        }
        return view;
    }
}

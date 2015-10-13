package de.davidartmann.charowin.fragment.user;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import de.davidartmann.charowin.R;
import de.davidartmann.charowin.adapter.user.UserAdapter;
import de.davidartmann.charowin.adapter.user.model.UserElement;
import de.davidartmann.charowin.util.CustomSnackBar;

/**
 * Fragment class of the user view.
 *
 * Created by David on 06.10.2015.
 */
public class UserFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final String USER_FRAGMENT = UserFragment.class.getSimpleName();

    private CharSequence[] mGenderStrings;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        ListView mListView = (ListView) view.findViewById(R.id.fragment_user_listview);
        mListView.setAdapter(new UserAdapter(
                view.getContext(), R.layout.fragment_user_list_item, createUserElements()));
        mListView.setOnItemClickListener(this);
        mGenderStrings = getResources().getStringArray(R.array.gender_chooser_dialog);
        return view;
    }

    private List<UserElement> createUserElements() {
        List<UserElement> userElements = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserElement userElement = new UserElement(R.drawable.ic_drawer, "testHeadline", "testSubHeadline", true);
            userElements.add(userElement);
        }
        return userElements;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 1:
                showGenderChooserDialog(parent, view, position, id);
                break;
            default:
                Log.w(USER_FRAGMENT, "Default path in onItemClick()");
        }
    }

    private void showGenderChooserDialog(AdapterView<?> parent, final View view, int position, long id) {
        AlertDialog.Builder builder;
        Context context = view.getContext();
        if (context != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(
                        context, android.R.style.Theme_Material_Light_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(
                        context, R.style.ChaRoWin_AppCompatDialog);
            }
            builder.setSingleChoiceItems(mGenderStrings, -1, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //TODO: save gender to user
                    switch (which) {
                        case 0:
                            CustomSnackBar.create(view, "Männlich ausgewählt", null, null);
                            break;
                        case 1:
                            CustomSnackBar.create(view, "Weiblich ausgewählt", null, null);
                            break;
                        default:
                            Log.w(USER_FRAGMENT, "Default path in setSinglChoidItems()");
                    }
                    dialog.dismiss();
                }
            });
            builder.setTitle("GESCHLECHT");
            Dialog dialog = builder.create();
            dialog.show();
            /**
             * not in use because of material design of dialogs
             *
             //claiming divider color is only possible to change programmatically
             int titleDividerId = getResources().getIdentifier("titleDivider", "id", "android");
             View titleDividerView = dialog.findViewById(titleDividerId);
             if (titleDividerView != null) {
             titleDividerView.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.green_dark));
             }
             */
        } else {
            Log.w(USER_FRAGMENT, "Themed context was null");
        }
    }
}

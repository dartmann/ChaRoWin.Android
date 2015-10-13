package de.davidartmann.charowin.fragment.user;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    private UserAdapter mUserAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        mGenderStrings = getResources().getStringArray(R.array.fragment_user_gender_chooser_dialog);
        ListView mListView = (ListView) view.findViewById(R.id.fragment_user_listview);
        mUserAdapter = new UserAdapter(
                view.getContext(), R.layout.fragment_user_list_item, createUserElements());
        mListView.setAdapter(mUserAdapter);
        mListView.setOnItemClickListener(this);
        return view;
    }

    private List<UserElement> createUserElements() {
        List<UserElement> userElements = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserElement userElement = new UserElement();
            if (i == 0 || i == 4 || i == 8) {
                switch (i) {
                    case 0:
                        userElement.setHeadline("Profil");
                        break;
                    case 4:
                        userElement.setHeadline("Generelles");
                        break;
                    case 8:
                        userElement.setHeadline("Energiesparen");
                        break;
                    default:
                        Log.w(USER_FRAGMENT, "default path in getItem() of cases 0, 4 and 8");
                }
            } else {
                switch (i) {
                    case 1:
                        userElement.setHeadline("Geschlecht");
//                        subHeadline.setText("Männlich");
                        break;
                    case 2:
                        userElement.setHeadline("Geburtsdatum");
//                        subHeadline.setText(simpleDateFormat.format(new Date()));
                        break;
                    case 3:
                        //TODO: privacy is not needed at the moment -> remove
                        userElement.setIcon(R.drawable.ic_visibility_black_48dp);
                        userElement.setHeadline("Privatssphäre");
                        break;
                    case 5:
                        userElement.setHeadline("Sprache");
//                        subHeadline.setText("Deutsch");
                        break;
                    case 6:
                        userElement.setHeadline("Standard Pause Alarm");
//                        subHeadline.setText("Nur Sound");
                        break;
                    case 7:
                        userElement.setHeadline("Standard Einheit");
//                        subHeadline.setText("cm / kg");
                        break;
                    case 9:
                        userElement.setHeadline("Pause Bildschirm aktiv");
//                        subHeadline.setText("An");
                        userElement.setCheckableItem(true);
//                        checkBox.setVisibility(View.VISIBLE);
//                        checkBox.setChecked(true);
                        break;
                    default:
                        Log.w(USER_FRAGMENT, "default path in else path of switch case");
                }
            }
//            UserElement userElement = new UserElement(R.drawable.ic_drawer, "testHeadline", "testSubHeadline", true);
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
            case 2:
                showBirthdayChooserDialog(parent, view, position, id);
                break;
            default:
                Log.w(USER_FRAGMENT, "Default path in onItemClick()");
        }
    }

    private void showBirthdayChooserDialog(AdapterView<?> parent, View view, final int position, long id) {
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
            final DatePicker picker = new DatePicker(context);
            styleDatePicker(picker, context);
            picker.setCalendarViewShown(false);
            picker.setBackgroundColor(ContextCompat.getColor(context, R.color.grey_lighter));
            builder.setView(picker);
            builder.setTitle("GEBURTSDATUM");
            builder.setPositiveButton("Speichern", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mUserAdapter.setValue(String.valueOf(picker.getCalendarView().getDate()), position);
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        } else {
            Log.w(USER_FRAGMENT, "Context was null in showBirthdayChooserDialog()");
        }
    }

    private void styleDatePicker(DatePicker picker, Context context) {
        LinearLayout linearLayout = (LinearLayout) picker.getChildAt(0);
        LinearLayout linearLayout2 = (LinearLayout)linearLayout.getChildAt(0);
        for (int i = 0; i < linearLayout2.getChildCount(); i++) {
            NumberPicker numberPicker = (NumberPicker) linearLayout2.getChildAt(i);
            Field[] pickerFields = NumberPicker.class.getDeclaredFields();
            for (Field pf : pickerFields) {
                if (pf.getName().equals("mSelectionDivider")) {
                    pf.setAccessible(true);
                    try {
                        pf.set(numberPicker, new ColorDrawable(ContextCompat.getColor(context, R.color.green_header_center)));
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }

    private void showGenderChooserDialog(AdapterView<?> parent, final View view, final int position, long id) {
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
                    mUserAdapter.setValue(mGenderStrings[which].toString(), position);
                    dialog.dismiss();
                }
            });
            builder.setTitle("GESCHLECHT");
            Dialog dialog = builder.create();
            dialog.show();
        } else {
            Log.w(USER_FRAGMENT, "Context was null in showGenderChooserDialog()");
        }
    }
}

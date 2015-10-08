package de.davidartmann.charowin.fragment.diet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.davidartmann.charowin.R;
import de.davidartmann.charowin.util.CustomSnackBar;

/**
 * Fragment for the calender view of the diet section.
 *
 * Created by David on 05.10.2015.
 */
public class DietFragmentCalender extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diet_calender, container, false);
        CaldroidFragment caldroidFragment = new CaldroidFragment();
        caldroidFragment.setCaldroidListener(new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {
                CustomSnackBar.create(view, new SimpleDateFormat("dd.MM.yyyy")
                        .format(new Date(date.getTime())), null, null);
            }
        });
        Bundle bundle = new Bundle();
        Calendar calendar = Calendar.getInstance();
        bundle.putInt(CaldroidFragment.MONTH, calendar.get(Calendar.MONTH) + 1);
        bundle.putInt(CaldroidFragment.YEAR, calendar.get(Calendar.YEAR));
        bundle.putInt(CaldroidFragment.START_DAY_OF_WEEK, CaldroidFragment.MONDAY);
        bundle.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
        caldroidFragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_diet_calender_linearlayout, caldroidFragment)
                .commit();
        return view;
    }
}

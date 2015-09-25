package de.davidartmann.charowin.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pkmmte.view.CircularImageView;

import de.davidartmann.charowin.R;

/**
 * Created by David on 28.08.2015.
 */
public class TopFragment extends Fragment {

    private CircularImageView mCircularImageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //getView() returns the root view for the fragment and then call findViewById()
        View view = inflater.inflate(R.layout.fragment_top, container, false);
        if (view != null) {
            mCircularImageView = (CircularImageView) view.findViewById(R.id.fragment_top_imageview1);

            mCircularImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    replaceFragment(new TrainingFragment());
                }
            });
        } else {
            Log.w("NP", "getView() returned null");
        }

//        return inflater.inflate(R.layout.fragment_top, container, false);
        return view;
    }

    /**
     * Helper method to call #getFragmentManager and replace a given Fragment
     * @param fragment
     */
    private void replaceFragment(Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.drawer_frame_layout, fragment)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}

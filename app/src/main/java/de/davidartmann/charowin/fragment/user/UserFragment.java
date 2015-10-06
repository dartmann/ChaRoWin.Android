package de.davidartmann.charowin.fragment.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.davidartmann.charowin.R;

/**
 * Fragment class of the user view.
 *
 * Created by David on 06.10.2015.
 */
public class UserFragment extends Fragment {
    private static final String USER_FRAGMENT = UserFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        return view;
    }
}

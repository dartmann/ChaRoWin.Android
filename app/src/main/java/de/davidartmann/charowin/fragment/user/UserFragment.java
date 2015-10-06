package de.davidartmann.charowin.fragment.user;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import de.davidartmann.charowin.R;
import de.davidartmann.charowin.adapter.user.UserAdapter;
import de.davidartmann.charowin.adapter.user.model.UserElement;

/**
 * Fragment class of the user view.
 *
 * Created by David on 06.10.2015.
 */
public class UserFragment extends Fragment {
    private static final String USER_FRAGMENT = UserFragment.class.getSimpleName();

    private ListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        mListView = (ListView) view.findViewById(R.id.fragment_user_listview);
        mListView.setAdapter(new UserAdapter(
                view.getContext(), R.layout.fragment_user_list_item, createUserElements()));
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
}

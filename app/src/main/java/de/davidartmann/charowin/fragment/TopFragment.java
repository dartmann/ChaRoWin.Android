package de.davidartmann.charowin.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.media.Image;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import de.davidartmann.charowin.R;

/**
 * Created by David on 28.08.2015.
 */
public class TopFragment extends Fragment {

    private static final String TOP_FRAGMENT = "TopFragment";

    private CircularImageView mCircularImageViewTraining;
    private CircularImageView mCircularImageViewDiet;
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //TODO: debugging, delete tp when finished
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        View view = inflater.inflate(R.layout.fragment_top, container, false);
        if (view != null) {
            /*
            mCircularImageViewTraining = (CircularImageView) view.findViewById(R.id.fragment_top_imageview_training);
            mCircularImageViewDiet = (CircularImageView) view.findViewById(R.id.fragment_top_imageview_diet);
            */
            //TODO: debugging
            imageView = (ImageView) view.findViewById(R.id.fragment_top_imageview_debug);
            Picasso.with(view.getContext()).setLoggingEnabled(true);
            Picasso.with(view.getContext())
                    .load("http://4.bp.blogspot.com/-a77bEtz0S48/USJ5cZL_ByI/AAAAAAAAFD8/LHJ_JWbYCOU/s1600/Arnold+Schwarzenegger+0.jpg")
                    .into(imageView);

            /*
            mCircularImageViewTraining.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    replaceFragment(new TrainingFragment());
                }
            });
            mCircularImageViewDiet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    replaceFragment(new DietFragment());
                }
            });
            */
        } else {
            Log.w(TOP_FRAGMENT, "view was null");
        }
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

package de.davidartmann.charowin.util;

import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import de.davidartmann.charowin.R;

/**
 * Util class for styling the {@link android.support.design.widget.Snackbar}.
 *
 * Created by David on 08.10.2015.
 */
public class CustomSnackBar {

    public static void create(View view, String text, CharSequence actionText, View.OnClickListener onClickListener) {
        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT);
        View snackView = snackbar.getView();
        TextView textView = (TextView) snackView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(view.getContext(), R.color.green_light));
        if (actionText != null && onClickListener != null) {
            snackbar.setAction(actionText, onClickListener);
            snackbar.setActionTextColor(ContextCompat.getColor(view.getContext(), R.color.white));
        }
        snackbar.show();
    }
}

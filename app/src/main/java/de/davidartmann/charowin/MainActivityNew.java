package de.davidartmann.charowin;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import de.davidartmann.charowin.fragment.diet.DietFragmentOverview;
import de.davidartmann.charowin.fragment.top.TopFragment;
import de.davidartmann.charowin.fragment.training.TrainingFragmentOverview;
import de.davidartmann.charowin.fragment.user.UserFragment;

public class MainActivityNew extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String MAIN_ACTIVITY = "MainActivity";

    private String[] mTitles;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
        mTitles = getResources().getStringArray(R.array.drawer_list_item_titles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_new);
        mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.drawer_list_view_new);
        mNavigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            replaceFragment(new TopFragment());
        }

//        DaoMaster.OpenHelper openHelper = new DaoMaster.DevOpenHelper(this, "charowin.db", null);
        //TODO
    }

    /**
     * Method to set the title of the ActionBar.
     * @param position the position of the clicked DrawerItem
     */
    private void setActionBarTitle(int position) {
        String title = mTitles[position];
//        if (position == 0) {
//            //Home clicked -> AppName as title
//            title = getResources().getString(R.string.app_name);
//        }
//        else {
//            title = mTitles[position];
//        }
        ActionBar actionBar = getSupportActionBar();/*getActionBar();*/
        if (actionBar != null) {
            actionBar.setTitle(title);
        } else {
            Log.w(MAIN_ACTIVITY, "ActionBar in MainActivity was null");
        }
    }

    //TODO: check if needed
    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
//        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerListView);
//        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mNavigationView);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_example:
                Toast.makeText(this, "example", Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "settings", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Helper method to call #getFragmentManager and replace a given Fragment
     * @param fragment the Fragment which will be replacing the actual
     */
    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.drawer_frame_layout, fragment)
                .disallowAddToBackStack()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                replaceFragment(new TopFragment());
                break;
            case R.id.nav_training:
                replaceFragment(new TrainingFragmentOverview());
                break;
            case R.id.nav_diet:
                replaceFragment(new DietFragmentOverview());
                break;
            case R.id.nav_settings:
                replaceFragment(new UserFragment());
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_new);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Called when a key was pressed down and not handled by any of the views
     * inside of the activity. So, for example, key presses while the cursor
     * is inside a TextView will not trigger the event (unless it is a navigation
     * to another object) because TextView handles its own key presses.
     * <p/>
     * <p>If the focused view didn't want this event, this method is called.
     * <p/>
     * <p>The default implementation takes care of {@link KeyEvent#KEYCODE_BACK}
     * by calling {@link #onBackPressed()}, though the behavior varies based
     * on the application compatibility mode: for
     * {@link Build.VERSION_CODES#ECLAIR} or later applications,
     * it will set up the dispatch to call {@link #onKeyUp} where the action
     * will be performed; for earlier applications, it will perform the
     * action immediately in on-down, as those versions of the platform
     * behaved.
     * <p/>
     * <p>Other additional default key handling may be performed
     * if configured with {@link #setDefaultKeyMode}.
     *
     * @param keyCode the key code
     * @param event the key event
     * @return Return <code>true</code> to prevent this event from being propagated
     * further, or <code>false</code> to indicate that you have not handled
     * this event and it should continue to be propagated.
     * @see #onKeyUp
     * @see KeyEvent
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (mDrawerLayout.isDrawerOpen(mNavigationView)) {
                    mDrawerLayout.closeDrawer(mNavigationView);
//                    finish();
                } else {
//                    mDrawerLayout.openDrawer(mNavigationView);
                    finish();
                }
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Called after {@link #onStop} when the current activity is being
     * re-displayed to the user (the user has navigated back to it).  It will
     * be followed by {@link #onStart} and then {@link #onResume}.
     * <p/>
     * <p>For activities that are using raw {@link android.database.Cursor} objects (instead of
     * creating them through
     * {@link #managedQuery(Uri, String[], String, String[], String)},
     * this is usually the place
     * where the cursor should be requeried (because you had deactivated it in
     * {@link #onStop}.
     * <p/>
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onStop
     * @see #onStart
     * @see #onResume
     */
    @Override
    protected void onRestart() {
        Log.d(MAIN_ACTIVITY, "Restarting...");
        super.onRestart();
    }

    /**
     * Called after {@link #onCreate} &mdash; or after {@link #onRestart} when
     * the activity had been stopped, but is now again being displayed to the
     * user.  It will be followed by {@link #onResume}.
     * <p/>
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onCreate
     * @see #onStop
     * @see #onResume
     */
    @Override
    protected void onStart() {
        Log.d(MAIN_ACTIVITY, "Starting...");
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

package de.davidartmann.charowin;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import de.davidartmann.charowin.fragment.DietFragment;
import de.davidartmann.charowin.fragment.TrainingFragment;

public class MainActivity extends Activity {

    private String[] mTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    private ActionBarDrawerToggle mDrawerToggle;

    private static final String LOG_WARN_MAIN_ACTIVITY = "LOG_WARN_MAIN_ACTIVITY";
    private static final String WARN_ACTIONBAR_NULL = "ActionBar in MainActivity was null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        setTitle(R.string.app_name);

        mTitles = getResources().getStringArray(R.array.drawer_titles);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerListView = (ListView) findViewById(R.id.drawer_list_view);

        // Set the adapter for the list view
        mDrawerListView.setAdapter(new ArrayAdapter<>(this,
                R.layout.drawer_list_item, mTitles));
        // Set the list's click listener
        mDrawerListView.setOnItemClickListener(new DrawerItemClickListener());

        if (getActionBar() != null) {
            //back button is removed by disabling this
            getActionBar().setDisplayHomeAsUpEnabled(false);
            getActionBar().setHomeButtonEnabled(true);
        }

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.string.drawer_open,
                R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // creates call to onPrepareOptionsMenu()
                invalidateOptionsMenu();
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // creates call to onPrepareOptionsMenu()
                invalidateOptionsMenu();
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    /**
     * Method to set the title of the ActionBar.
     * @param position the position of the clicked DrawerItem
     */
    private void setActionBarTitle(int position) {
        String title;
        if (position == 0) {
            //Home clicked -> AppName as title
            title = getResources().getString(R.string.app_name);
        }
        else {
            title = mTitles[position];
        }
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        } else {
            Log.w(LOG_WARN_MAIN_ACTIVITY, WARN_ACTIONBAR_NULL);
        }
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerListView);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * Method to set the fragment for the clicked DrawerItem.
     * @param position the postition of the clicked DrawerItem
     */
    private void selectItem(int position) {
        Fragment fragment;
        switch (position) {
            case 1:
                fragment = new TrainingFragment();
                break;
            case 2:
                fragment = new DietFragment();
                break;
            default:
                fragment = new TopFragment();
        }
        setActionBarTitle(position);
        mDrawerListView.setItemChecked(position, true);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.drawer_frame_layout, fragment)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
        mDrawerLayout.closeDrawer(mDrawerListView);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
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

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
            setActionBarTitle(position);
        }
    }
}

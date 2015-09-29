package de.davidartmann.charowin;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.davidartmann.charowin.adapter.DrawerAdapter;
import de.davidartmann.charowin.adapter.model.DrawerItem;
import de.davidartmann.charowin.fragment.DietFragment;
import de.davidartmann.charowin.fragment.TopFragment;
import de.davidartmann.charowin.fragment.TrainingFragment;

public class MainActivity extends Activity {

    private String[] mTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    private ActionBarDrawerToggle mDrawerToggle;
//    private RecyclerView mRecyclerView;
    private List<DrawerItem> drawerItems;

    private static final String MAIN_ACTIVITY = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitles = getResources().getStringArray(R.array.drawer_list_item_titles);
        drawerItems = createDrawerItems();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerListView = (ListView) findViewById(R.id.drawer_list_view);
//        mRecyclerView = (RecyclerView) findViewById(R.id.topFragmentRecyclerViewList);

        // Set the adapter for the list view
        /*mDrawerListView.setAdapter(new ArrayAdapter<>(this,
                R.layout.drawer_list_item, mTitles));*/
        mDrawerListView.setAdapter(new DrawerAdapter(this,
                R.layout.drawer_list_item_test, drawerItems));
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

        /*
         * we get to that later...
         *
        mRecyclerView.setHasFixedSize(true);
        //positions item views inside the row and determines when it is time to recycle the views
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        */
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
            Log.w(MAIN_ACTIVITY, "ActionBar in MainActivity was null");
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
        replaceFragment(fragment);
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

    /**
     * Helper method to call #getFragmentManager and replace a given Fragment
     * @param fragment the Fragment which will be replacing the actual
     */
    private void replaceFragment(Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.drawer_frame_layout, fragment)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
            setActionBarTitle(position);
        }
    }

    /**
     * Helper method to create the static DrawerItems
     *
     * @return List of {@link DrawerItem} entries for the NavigationDrawer
     */
    private List<DrawerItem> createDrawerItems() {
        List<DrawerItem> drawerItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            DrawerItem drawerItem = null;
            switch (i) {
                case 0:
                    drawerItem = new DrawerItem(R.drawable.ic_person_black_48dp, mTitles[i]);
                    break;
                case 1:
                    drawerItem = new DrawerItem(R.drawable.ic_home_black_24dp, mTitles[i]);
                    break;
                case 2:
                    drawerItem = new DrawerItem(R.drawable.ic_flash_on_black_24dp, mTitles[i]);
                    break;
                case 3:
                    drawerItem = new DrawerItem(R.drawable.ic_local_dining_black_24dp, mTitles[i]);
                    break;
                case 4:
                    drawerItem = new DrawerItem(R.drawable.ic_settings_black_24dp, mTitles[i]);
                    break;
                default:
                    Log.e(MAIN_ACTIVITY, "Default branch in createDrawerItems()");
            }
            drawerItems.add(drawerItem);
        }
        return drawerItems;
    }
}

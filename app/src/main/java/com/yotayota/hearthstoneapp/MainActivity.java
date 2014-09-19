package com.yotayota.hearthstoneapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.yotayota.hearthstoneapp.fragments.ListMultiSelectFragment;
import com.yotayota.hearthstoneapp.fragments.ListSingleSelectFragment;


public class MainActivity extends Activity {

    public static String PACKAGE_NAME;

    public static final String KEY_SELECTED_CARDS_ARRAY = "com.yotayota.hearthstoneapp.fragments" +
            ".SelectedCards";

    ListSingleSelectFragment mSingleSelectFragment;
    ListMultiSelectFragment mMultiSelectFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PACKAGE_NAME = getApplicationContext().getPackageName();

        mSingleSelectFragment = new ListSingleSelectFragment();
        mMultiSelectFragment = new ListMultiSelectFragment();

        ActionBar tabBar = getActionBar();
        tabBar.setLogo(getResources().getDrawable(R.drawable.ic_logo));
        tabBar.setTitle("Hearth");

        tabBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        tabBar.addTab(tabBar.newTab()
                .setText("SingleSelect")
                .setTabListener(new TabListener(mSingleSelectFragment)));
        tabBar.addTab(tabBar.newTab()
                .setText("MultiSelect")
                .setTabListener(new TabListener(mMultiSelectFragment)));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /* Inner TabListener class. */
    protected static class TabListener implements ActionBar.TabListener {

        private final Fragment mFragment;

        public TabListener(Fragment fragment) {
            mFragment = fragment;
        }

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            if (null != fragmentTransaction) {
                fragmentTransaction.replace(R.id.container, mFragment);
            }
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            if (null != fragmentTransaction) {
                fragmentTransaction.remove(mFragment);
            }
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        }
    }
}

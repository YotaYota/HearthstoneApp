package com.yotayota.hearthstoneapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.yotayota.hearthstoneapp.fragments.ListCompareFragment;
import com.yotayota.hearthstoneapp.fragments.ListMultiSelectFragment;

import java.util.ArrayList;


public class CompareCardsActivity extends Activity {

    private ListCompareFragment mListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_cards);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setLogo(getResources().getDrawable(R.drawable.ic_logo));
        actionBar.setTitle("Compare");

        mListFragment = new ListCompareFragment();

        Intent intent = getIntent();

        ArrayList<Integer> ids = intent.getIntegerArrayListExtra(MainActivity
                .KEY_SELECTED_CARDS_ARRAY);
        System.out.println("############# Comapare Activity: onCreate " + ids + "#############");

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().add(R.id.activity_compare_container, mListFragment).commit();

        mListFragment.setItemsFromDatabaseIds(ids);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.compare_cards, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_spells:
                Toast.makeText(this, "Spells pressed", Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_weapons:
                Toast.makeText(this, "Weapons pressed", Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_vanilla_test:
                Toast.makeText(this, "Vanilla pressed", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

package com.yotayota.hearthstoneapp.fragments;


import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.yotayota.hearthstoneapp.CompareCardsActivity;
import com.yotayota.hearthstoneapp.MainActivity;
import com.yotayota.hearthstoneapp.R;
import com.yotayota.hearthstoneapp.adapters.MultiSelectAdapter;

import java.util.ArrayList;

/**
 *
 */
public class ListMultiSelectFragment extends ListFragment {


    private MultiSelectAdapter mAdapter;

    public ListMultiSelectFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new MultiSelectAdapter(
                getActivity().getApplicationContext(),
                R.layout.list_item,
                R.id.list_item_text);
        setListAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_fragment_item:
                ArrayList<Integer> selectedCards = mAdapter.getSelectedCardsDatabaseIds();
                Intent intent = new Intent(getActivity(), CompareCardsActivity.class);
                intent.putIntegerArrayListExtra(MainActivity.KEY_SELECTED_CARDS_ARRAY,
                        selectedCards);
                getActivity().startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.action_compare_button, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


}

package com.yotayota.hearthstoneapp.fragments;


import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.yotayota.hearthstoneapp.FocusCardActivity;
import com.yotayota.hearthstoneapp.R;
import com.yotayota.hearthstoneapp.adapters.SingleSelectAdapter;

/**
 *
 */
public class ListSingleSelectFragment extends ListFragment {

    private SingleSelectAdapter mAdapter;

    public ListSingleSelectFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new SingleSelectAdapter(
                getActivity().getApplicationContext(),
                R.layout.list_item,
                R.id.list_item_text);
        setListAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_fragment_item:

                // TODO: Send id of selected item in some way..
                // ArrayList<String> selectedCards = mAdapter.getSelectedCards();

                //if (selectedCards.size() == 2) {
                Intent intent = new Intent(getActivity(), FocusCardActivity.class);
                // intent.putArrayList(KEY_SELECTED_CARDS_ARRAY, selectedCards);
                startActivity(intent);
                //} else {
                //}
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.action_focus_card, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}

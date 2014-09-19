package com.yotayota.hearthstoneapp.fragments;


import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;

import com.yotayota.hearthstoneapp.R;
import com.yotayota.hearthstoneapp.adapters.CompareAdapter;

import java.util.ArrayList;

public class ListCompareFragment extends ListFragment {

    private CompareAdapter mAdapter;

    // TODO: this function generates an error for some reason.
    public void setItemsFromDatabaseIds(ArrayList<Integer> idArray) {
        mAdapter.setItemsFromDatabaseId(idArray);
    }

    public ListCompareFragment() {
        mAdapter = new CompareAdapter(
                getActivity().getApplicationContext(),
                R.layout.list_item_compare);
        System.out.println("#############" + getActivity() + "#############");
        setListAdapter(mAdapter);
    }
}

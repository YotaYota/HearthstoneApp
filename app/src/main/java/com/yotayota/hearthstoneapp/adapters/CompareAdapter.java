package com.yotayota.hearthstoneapp.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.yotayota.hearthstoneapp.R;
import com.yotayota.hearthstoneapp.database.Card;
import com.yotayota.hearthstoneapp.database.CardsDataSource;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Shows requested items.
 * <p/>
 * Align left: name
 * Align right: result
 */
public class CompareAdapter extends ArrayAdapter<Card> {

    private Context mContext;
    private CardsDataSource mDataSource;
    private ArrayList<Card> mCards = new ArrayList<Card>();

    // TODO: maybe the resource should not be a parameter...
    public CompareAdapter(Context context, int resource) {

        super(context, resource);
        mContext = context;
        mDataSource = new CardsDataSource(mContext);

        // TEST:
        ArrayList<Integer> al = new ArrayList<Integer>();
        al.add(45);
        al.add(96);

        al.add(200);
        setItemsFromDatabaseId(al);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {

            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.list_item_compare, null);

            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.list_compare_name);
            viewHolder.result = (TextView) view.findViewById(R.id.list_compare_result);
            view.setTag(viewHolder);

        } else {
            ((ViewHolder) view.getTag()).name.setTag(mCards.get(position));
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.name.setTag(mCards.get(position));

        holder.name.setText(getItem(position).getName());
        holder.result.setText(String.valueOf(getItem(position).getCost()));

        return view;
    }

    /**
     * Takes database ids as input and sets adapter's mCards to corresponding cards.
     */
    public void setItemsFromDatabaseId(ArrayList<Integer> idArray) {
        mCards.clear();
        try {
            mDataSource.open();
            for (int id : idArray) {
                try {
                    mCards.add(mDataSource.getCardbyId(id));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //System.out.println("\n\nCompare Activity Fragment Adapter\n\n");
        mDataSource.close();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mCards.size();
    }

    @Override
    public Card getItem(int position) {
        try {
            return mCards.get(position);
        } catch (Exception e) {
            // TODO: should it return null?
            return null;
        }
    }

    // Implementing the ViewHolder pattern
    static class ViewHolder {
        protected TextView name;
        protected TextView result;
    }
}

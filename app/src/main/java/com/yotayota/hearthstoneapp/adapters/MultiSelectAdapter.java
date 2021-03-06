package com.yotayota.hearthstoneapp.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yotayota.hearthstoneapp.R;
import com.yotayota.hearthstoneapp.database.Card;
import com.yotayota.hearthstoneapp.database.CardsDataSource;
import com.yotayota.hearthstoneapp.objects.SelectedModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ArrayAdapter that takes model objects. The model object has a selected field that is toggled
 * by an onClickListener on the TextView.
 * <p/>
 * view has Tag: viewHolder
 * viewHolder.text has Tag: mCards(position)
 */
public class MultiSelectAdapter extends ArrayAdapter<SelectedModel> {

    private ArrayList<SelectedModel> mCards = new ArrayList<SelectedModel>();
    private Context mContext;

    public MultiSelectAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
        mContext = context;

        populateArrayFromDatabase();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (convertView == null) {

            LayoutInflater inflator = LayoutInflater.from(mContext);
            view = inflator.inflate(R.layout.list_item, null);

            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) view.findViewById(R.id.list_item_text);
            viewHolder.image = (ImageView) view.findViewById(R.id.list_item_image);

            // click -> select item
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Get the model object
                    SelectedModel model = (SelectedModel) viewHolder.text.getTag();
                    model.setSelected(!model.isSelected());
                    notifyDataSetChanged();
                }
            });

            view.setTag(viewHolder);
        } else {
            view = convertView;
            ((ViewHolder) view.getTag()).text.setTag(mCards.get(position));
        }

        // Sets background color
        if (getItem(position).isSelected()) {
            //view.setBackgroundColor(Color.argb(255, 203, 176, 83));
            // destroys padding...
            view.setBackground(mContext.getResources().getDrawable(R.drawable.box_orange));
        } else {
            //view.setBackgroundColor(Color.argb(255, 0, 102, 170));
            view.setBackground(mContext.getResources().getDrawable(R.drawable.box_darker_blue));
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.text.setTag(mCards.get(position)); // model for selection.
        holder.text.setText(getItem(position).getCard().getName() + "    " + getItem(position)
                .isSelected());
        // TODO: set icon

        return view;
    }

    @Override
    public int getCount() {
        return mCards.size();
    }

    public ArrayList<Card> getSelectedCards() {
        ArrayList<Card> selectedCards = new ArrayList<Card>();
        for (SelectedModel model : mCards) {
            if (model.isSelected()) selectedCards.add(model.getCard());
        }
        return selectedCards;
    }

    public ArrayList<Integer> getSelectedCardsDatabaseIds(){
        ArrayList<Integer> selectedCardsIds = new ArrayList<Integer>();
        for ( SelectedModel model : mCards) {
            if (model.isSelected()) selectedCardsIds.add(model.getCard().getId());
        }
        return selectedCardsIds;
    }

    @Override
    public SelectedModel getItem(int position) {
        try {
            return mCards.get(position);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Clears the adapter's items and adds new ones. Encapsulates each card in a SelectedModel.
     *
     * @param allItems new items to populate the adapter's list.
     */
    public void setItems(ArrayList<Card> allItems) {
        mCards.clear();
        for (Card card : allItems) {
            mCards.add(new SelectedModel(card));
        }
        notifyDataSetChanged();
    }

    private void populateArrayFromDatabase() {
        mCards.clear();
        CardsDataSource dataSource = new CardsDataSource(mContext);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Card> cards = dataSource.getAllCards();
        dataSource.close();
        for (Card card : cards) {
            mCards.add(new SelectedModel(card));
        }
        notifyDataSetChanged();
    }


    // implementing the ViewHolder pattern
    static class ViewHolder {
        protected TextView text;
        protected ImageView image;
        protected int position;
    }
}

package com.yotayota.hearthstoneapp.objects;


import com.yotayota.hearthstoneapp.database.Card;

/**
 * Node class used by the Adapter in order to set selected.
 */
public class SelectedModel {

    private Card mCard;
    private boolean mSelected = false;

    public SelectedModel(Card card) {
        mCard = card;
    }

    public Card getCard() {
        return mCard;
    }

    public void setmCard(Card card) {
        mCard = card;
    }

    public boolean isSelected() {
        return mSelected;
    }

    public void setSelected(boolean selected) {
        mSelected = selected;
    }
}

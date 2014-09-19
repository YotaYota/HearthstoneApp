package com.yotayota.hearthstoneapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardsDataSource {

    private SQLiteDatabase mDatabase;
    private CardsDatabaseHelper mDBHelper;

    // All columns of table cards in database hs_cards.db
    private String[] mAllColumns = {
            CardsDatabaseHelper.COLUMN_ID,
            CardsDatabaseHelper.COLUMN_NAME,
            CardsDatabaseHelper.COLUMN_TYPE,
            CardsDatabaseHelper.COLUMN_DURABILITY,
            CardsDatabaseHelper.COLUMN_RACE,
            CardsDatabaseHelper.COLUMN_ELITE,
            CardsDatabaseHelper.COLUMN_QUALITY,
            CardsDatabaseHelper.COLUMN_HSCLASS,
            CardsDatabaseHelper.COLUMN_COST,
            CardsDatabaseHelper.COLUMN_ATTACK,
            CardsDatabaseHelper.COLUMN_HEALTH,
            CardsDatabaseHelper.COLUMN_DESCRIPTION,
            CardsDatabaseHelper.COLUMN_FLAVOUR,
            CardsDatabaseHelper.COLUMN_FACTION,
            CardsDatabaseHelper.COLUMN_IMG_URL
    };


    public CardsDataSource(Context context) {
        mDBHelper = new CardsDatabaseHelper(context);
    }

    public void open() throws SQLException {
        mDatabase = mDBHelper.getWritableDatabase();
    }

    public void close() {
        mDBHelper.close();
    }

    public List<Card> getAllCards() {
        List<Card> cards = new ArrayList<Card>();
        Cursor cursor = mDatabase.query(
                CardsDatabaseHelper.TABLE_CARDS, null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Card card = cursorToCard(cursor);
            cards.add(card);
            cursor.moveToNext();
        }

        cursor.close();
        return cards;
    }

    public Card getCardbyId(int id) {
        Cursor cursor = mDatabase.query(true, CardsDatabaseHelper.TABLE_CARDS, null,
                CardsDatabaseHelper.COLUMN_ID + " LIKE ?", new String[]{String.valueOf(id)},
                null, null, null, null, null);
        cursor.moveToFirst();
        Card card = cursorToCard(cursor);
        cursor.close();
        return card;
    }

    private Card cursorToCard(Cursor cursor) {
        Card card = new Card();

        // Columns from DB --> card object
        card.setId(cursor.getInt(0));
        card.setName(cursor.getString(1));
        card.setType(cursor.getString(2));
        card.setDurability(cursor.getInt(3));
        card.setRace(cursor.getString(4));
        card.setElite(cursor.getInt(5));
        card.setQuality(cursor.getString(6));
        card.setHSClass(cursor.getString(7));
        card.setCost(cursor.getInt(8));
        card.setAttack(cursor.getInt(9));
        card.setHealth(cursor.getInt(10));
        card.setDescription(cursor.getString(11));
        card.setFlavour(cursor.getString(12));
        card.setFaction(cursor.getString(13));
        card.setImgUrl(cursor.getString(14));

        return card;
    }
}
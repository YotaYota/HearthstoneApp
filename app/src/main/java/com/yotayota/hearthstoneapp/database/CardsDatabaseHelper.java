package com.yotayota.hearthstoneapp.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.yotayota.hearthstoneapp.MainActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Database Helper. Copies database from assets folder to standard data/data/databases folder.
 */
public class CardsDatabaseHelper extends SQLiteOpenHelper {

    // Database hs_cards.db
    private static final String DATABASE_NAME = "hearthstone_cards.db";
    private static final int DATABASE_VERSION = 1;

    // Table Card
    public static final String TABLE_CARDS = "cards";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_DURABILITY = "durability";
    public static final String COLUMN_RACE = "race";
    public static final String COLUMN_ELITE = "elite";
    public static final String COLUMN_QUALITY = "quality";
    public static final String COLUMN_HSCLASS = "class";
    public static final String COLUMN_COST = "cost";
    public static final String COLUMN_ATTACK = "attack";
    public static final String COLUMN_HEALTH = "health";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_FLAVOUR = "flavour";
    public static final String COLUMN_FACTION = "faction";
    public static final String COLUMN_IMG_URL = "img_url";

    // Create database String
    private static final String DATABASE_CREATE =
            "CREATE TABLE " + TABLE_CARDS + "("
                    + COLUMN_ID + " integer primary key, "
                    + COLUMN_NAME + " text not null, "
                    + COLUMN_TYPE + " text, "
                    + COLUMN_DURABILITY + " integer, "
                    + COLUMN_RACE + " text, "
                    + COLUMN_ELITE + " integer, "
                    + COLUMN_QUALITY + " text, "
                    + COLUMN_HSCLASS + " text, "
                    + COLUMN_COST + " integer, "
                    + COLUMN_ATTACK + " integer, "
                    + COLUMN_HEALTH + " integer, "
                    + COLUMN_DESCRIPTION + " text, "
                    + COLUMN_FLAVOUR + " text"
                    + COLUMN_FACTION + " text, "
                    + COLUMN_IMG_URL + " text );";

    private static final String DATABASE_PATH = "/data/data/" + MainActivity.PACKAGE_NAME +
            "/databases/";

    public SQLiteDatabase mDataBase;
    private Context mContext;


    public CardsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
        boolean dbExist = checkDatabase();
        if (dbExist) {
            openDatabase();
        } else {
            System.out.println("Database doesn't exist");
            try {
                createDatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void createDatabase() throws IOException {
        boolean dbExist = checkDatabase();
        if (!dbExist) {
            this.getReadableDatabase();
            try {
                copyDatabase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDatabase() {
        boolean dbExists = false;
        try {
            String dbPath = DATABASE_PATH + DATABASE_NAME;
            File dbFile = new File(dbPath);
            dbExists = dbFile.exists();
        } catch (SQLiteException e) {
            System.out.println("Database doesn't exist");
        }
        return dbExists;
    }

    private void copyDatabase() throws IOException {
        // Open local db as the input stream
        InputStream input = mContext.getAssets().open(DATABASE_NAME);

        // Open new db as the output stream
        OutputStream output = new FileOutputStream(DATABASE_PATH + DATABASE_NAME);

        // Transfer bytes
        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }

        // Close the streams
        output.flush();
        output.close();
        input.close();
    }

    public void openDatabase() throws SQLException {
        // Open database
        String path = DATABASE_PATH + DATABASE_NAME;
        mDataBase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void close() {
        if (mDataBase != null) {
            mDataBase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

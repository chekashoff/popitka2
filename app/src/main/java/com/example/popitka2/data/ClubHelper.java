package com.example.popitka2.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class ClubHelper extends SQLiteOpenHelper {
    public ClubHelper( Context context) {
        super(context, ClubContract.DATABASE_NAME, null, ClubContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MEMBERS_TABLE = "CREATE TABLE " + ClubContract.MemberEntry.TABLE_NAME + "("
                + ClubContract.MemberEntry._ID + " INTEGER PRIMARY KEY,"
                + ClubContract.MemberEntry.COLUMN_FIRST_NAME + " TEXT,"
                + ClubContract.MemberEntry.COLUMN_LAST_NAME + " TEXT,"
                + ClubContract.MemberEntry.COLUMN_GENDER + " INTEGER,"
                + ClubContract.MemberEntry.COLUMN_SPORT + " TEXT,"
                + ClubContract.MemberEntry.COLUMN_MONEY + " TEXT" + ")";

        db.execSQL(CREATE_MEMBERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ClubContract.DATABASE_NAME );
        onCreate(db);

    }
}

package com.example.popitka2.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.Selection;
import android.util.Log;
import android.widget.Toast;

import com.example.popitka2.data.ClubContract.*;


public class  ClubContentProvider extends ContentProvider {

    ClubHelper dbOpenHelper;
    private static final int MEMBERS = 111;
    private static final int MEMBER_ID = 222;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static{
        uriMatcher.addURI(ClubContract.AUTORITY, ClubContract.PATH_MEMBERS, 111);
        uriMatcher.addURI(ClubContract.AUTORITY, ClubContract.PATH_MEMBERS + "/#", 222);


    }

    @Override
    public boolean onCreate() {
        dbOpenHelper = new ClubHelper(getContext());
        return true;
    }


    @Override
    public Cursor query( Uri uri,  String[] projection, String selection,  String[] selectionArgs,  String sortOrder) {

        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor cursor;

        int match = uriMatcher.match(uri);
        switch(match){
            case MEMBERS:
                cursor = db.query(MemberEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case MEMBER_ID:
                selection = MemberEntry._ID +"=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(MemberEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                Toast.makeText(getContext(), "Incorrect URI", Toast.LENGTH_LONG).show();
                throw new IllegalArgumentException("Cant quary incorrect uri " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public Uri insert( Uri uri,  ContentValues values) {
        String firstName = values.getAsString(MemberEntry.COLUMN_FIRST_NAME);
        if (firstName == null){
            throw new IllegalArgumentException("Я тебе побалуюсь!где имя?");
        }
        String lastName = values.getAsString(MemberEntry.COLUMN_LAST_NAME);
        if (lastName == null){
            throw new IllegalArgumentException("Где колеса?");
        }
        String sport = values.getAsString(MemberEntry.COLUMN_SPORT);
        if (sport == null){
            throw new IllegalArgumentException("Где дата? я ща поудаляю тут все к хуям");
        }
        String money = values.getAsString(MemberEntry.COLUMN_MONEY);
        if (money == null){
            throw new IllegalArgumentException("Где бабки?");
        }
        String gender = values.getAsString(MemberEntry.COLUMN_GENDER);
        if (gender == null){
            throw new IllegalArgumentException("Какой сезон");
        }
        SQLiteOpenHelper dbHelper;
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        int match = uriMatcher.match(uri);
        switch(match){
            case MEMBERS:
               long id = db.insert(MemberEntry.TABLE_NAME, null, values);
               if (id == -1) {
               Log.e("insertMethod", "Insertion of data in the table failed for " + uri);
               return null;
               }
               getContext().getContentResolver().notifyChange(uri, null);
               return ContentUris.withAppendedId(uri, id);

            default:
                throw new IllegalArgumentException("Insertion of data in the table failed for " + uri);
        }


    }

    @Override
    public int delete( Uri uri,  String selection,  String[] selectionArgs) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        int match = uriMatcher.match(uri);

        int rowsDeleted;

        switch(match){
            case MEMBERS:
                rowsDeleted = db.delete(MemberEntry.TABLE_NAME, selection, selectionArgs);
                break;


            case MEMBER_ID:
                selection = MemberEntry._ID +"=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = db.delete(MemberEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Нихуя не получается " + uri);
        }
        if (rowsDeleted != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;

    }

    @Override
    public int update( Uri uri,  ContentValues values,  String selection,  String[] selectionArgs) {
        if (values.containsKey(MemberEntry.COLUMN_FIRST_NAME)){
            String firstName = values.getAsString(MemberEntry.COLUMN_FIRST_NAME);
            if (firstName == null){
                throw new IllegalArgumentException("Я тебе побалуюсь!где имя? ");
            }
        }
        if (values.containsKey(MemberEntry.COLUMN_LAST_NAME)){
            String lastName = values.getAsString(MemberEntry.COLUMN_LAST_NAME);
            if (lastName == null){
                throw new IllegalArgumentException("Где колеса?");
            }
        }
        if (values.containsKey(MemberEntry.COLUMN_LAST_NAME)){
            String lastName = values.getAsString(MemberEntry.COLUMN_LAST_NAME);
            if (lastName == null){
                throw new IllegalArgumentException("Где колеса? ");
            }
        }
        if (values.containsKey(MemberEntry.COLUMN_SPORT)){
            String sport = values.getAsString(MemberEntry.COLUMN_SPORT);
            if (sport == null){
                throw new IllegalArgumentException("Где дата? я ща поудаляю тут все к хуям ");
            }
        }
        if (values.containsKey(MemberEntry.COLUMN_MONEY)){
            String money = values.getAsString(MemberEntry.COLUMN_MONEY);
            if (money == null){
                throw new IllegalArgumentException("Где Бабки? ");
            }
        }

         int rowsUpdated;
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        int match = uriMatcher.match(uri);
        switch(match){
            case MEMBERS:
                rowsUpdated = db.update(MemberEntry.TABLE_NAME, values, selection, selectionArgs);

                break;

            case MEMBER_ID:
                selection = MemberEntry._ID +"=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsUpdated = db.update(MemberEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Ты шо ебанутый? " + uri);
        }
        if(rowsUpdated !=0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }
    @Override
    public String getType( Uri uri) {

        int match = uriMatcher.match(uri);
        switch(match){
            case MEMBERS:
                return MemberEntry.CONTENT_MULTIPLE_ITEMS;

            case MEMBER_ID:
                return MemberEntry.CONTENT_SINGLE_ITEM;
            default:
                throw new IllegalArgumentException("Все плохо " + uri);
        }

    }

}

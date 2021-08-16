package com.example.popitka2;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.popitka2.data.ClubContract.MemberEntry;

public class MemberCursorAdapter extends CursorAdapter {
    public MemberCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.member_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView firstNameTextView = view.findViewById(R.id.firstNameTextView);


        String firstName = cursor.getString(cursor.getColumnIndexOrThrow(MemberEntry.COLUMN_FIRST_NAME));

        firstNameTextView.setText(firstName);


    }
}

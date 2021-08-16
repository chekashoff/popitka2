package com.example.popitka2;


import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import com.example.popitka2.data.ClubContract.MemberEntry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int MEMBER_LOADER = 123;
    MemberCursorAdapter memberCursorAdapter;
    ListView dataListView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataListView = findViewById(R.id.dataListView);
        FloatingActionButton floatingActionButton4 = findViewById(R.id.floatingActionButton4);
        floatingActionButton4.setOnClickListener ((v) -> {
            Intent intent2 = new Intent(MainActivity.this, Calendar.class);
            startActivity(intent2);

        });
        FloatingActionButton floatingActionButton3 = findViewById(R.id.floatingActionButton3);
        floatingActionButton3.setOnClickListener ((v) -> {
            Intent intent2 = new Intent(MainActivity.this, CalculatorActivity.class);
            startActivity(intent2);

        });


        FloatingActionButton floatingActionButton2 = findViewById(R.id.floatingActionButton2);
        floatingActionButton2.setOnClickListener ((v) -> {
            Intent intent2 = new Intent(MainActivity.this, zapisActivity.class);
            startActivity(intent2);

        });



        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener ((v) -> {
                Intent intent = new Intent(MainActivity.this, AddMemberAct.class);
                startActivity(intent);

        });

        memberCursorAdapter = new MemberCursorAdapter(this, null,false);
        dataListView.setAdapter(memberCursorAdapter);


        dataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, AddMemberAct.class);
                Intent intent2 = new Intent(MainActivity.this, zapisActivity.class);
                Uri currentMemberUri = ContentUris.withAppendedId(MemberEntry.CONTENT_URI,
                        id);
                intent.setData(currentMemberUri);
                startActivity(intent);

            }
        });
        getSupportLoaderManager().initLoader(MEMBER_LOADER, null, this);


    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        String[] projection ={
                MemberEntry._ID,
                MemberEntry.COLUMN_FIRST_NAME,
                MemberEntry.COLUMN_LAST_NAME,
                MemberEntry.COLUMN_SPORT,
                MemberEntry.COLUMN_MONEY,
                MemberEntry.COLUMN_GENDER
        };
        CursorLoader cursorLoader = new CursorLoader(this,
                MemberEntry.CONTENT_URI,
                projection, null, null, null
        );
        return cursorLoader;
    }

    @Override
    public void onLoadFinished( Loader<Cursor> loader, Cursor cursor) {
        memberCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset( Loader<Cursor> loader) {

        memberCursorAdapter.swapCursor(null);
    }
}
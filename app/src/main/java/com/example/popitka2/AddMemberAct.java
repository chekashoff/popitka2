package com.example.popitka2;

import android.app.Activity;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.popitka2.data.ClubContract;

import java.util.ArrayList;

public class AddMemberAct extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static int EDIT_MEMBER_LOADER = 111;
    Uri currentMemberUri;

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText sportEditText;
    private EditText moneyEditText;
    private Spinner genderSpinner;
    private int gender = 0;
    private ArrayAdapter spinnerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
        Intent intent = getIntent();
        currentMemberUri = intent.getData();

        if (currentMemberUri == null){
            setTitle("Добавить клиента");
            invalidateOptionsMenu();

        } else {
            setTitle("Редактировать клиента");
            getSupportLoaderManager().initLoader(EDIT_MEMBER_LOADER, null, this);
        }

        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        sportEditText = findViewById(R.id.sportEditText);
        genderSpinner = findViewById(R.id.genderSpinner);
        moneyEditText = findViewById(R.id.moneyEditText);


        spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_gender, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(spinnerAdapter);

        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedGender = (String) parent.getItemAtPosition(position);
                if (TextUtils.isEmpty(selectedGender)) {
                    if (selectedGender.equals("Male")) {
                        gender = ClubContract.MemberEntry.GENDER_MALE;
                    } else if (selectedGender.equals("Female")) {
                        gender = ClubContract.MemberEntry.GENDER_FEMALE;
                    } else {
                        gender = ClubContract.MemberEntry.GENDER_UNKNOWN;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                gender = 0;

            }
        });
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (currentMemberUri == null){
            MenuItem menuItem = menu.findItem(R.id.delete_member);
            menuItem.setVisible(false);
        }

        return true;
    }

    //return super.onCreateOptionsMenu(menu); было
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_member_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_member:
                saveMember();

                Intent o = new Intent(this, MainActivity.class);
                startActivity(o);
                break;

            case R.id.delete_member:
                showDeleteMemberDialog();
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private  void  saveMember(){
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String sport = sportEditText.getText().toString().trim();
        String money = moneyEditText.getText().toString().trim();
        String gender= genderSpinner.getSelectedItem().toString().trim();
        if(TextUtils.isEmpty(firstName)){
            Toast.makeText(this, "Клиента запиши ебантяй",Toast.LENGTH_LONG).show();
            return;
        }
        else if(TextUtils.isEmpty(lastName)){
            Toast.makeText(this, "Какие колеса? ",Toast.LENGTH_LONG).show();
            return;
        }
        else if(TextUtils.isEmpty(sport)){
            Toast.makeText(this, "Дату введи собака",Toast.LENGTH_LONG).show();
            return;
        }
        else if(TextUtils.isEmpty(money)){
            Toast.makeText(this, "Сколько денег",Toast.LENGTH_LONG).show();
            return;
        }




        ContentValues contentValues = new ContentValues();
        contentValues.put(ClubContract.MemberEntry.COLUMN_FIRST_NAME, firstName);
        contentValues.put(ClubContract.MemberEntry.COLUMN_LAST_NAME, lastName);
        contentValues.put(ClubContract.MemberEntry.COLUMN_SPORT, sport);
        contentValues.put(ClubContract.MemberEntry.COLUMN_MONEY, money);
        contentValues.put(ClubContract.MemberEntry.COLUMN_GENDER, gender);

        if (currentMemberUri == null){
            ContentResolver contentResolver = getContentResolver();
            Uri uri = contentResolver.insert(ClubContract.MemberEntry.CONTENT_URI, contentValues);

            if (uri == null){
                Toast.makeText(this, "Ты все сломал к хуям, поздравляю",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this, "Сохраняется",Toast.LENGTH_LONG).show();
            }
        } else {
            int rowsChanged = getContentResolver().update(currentMemberUri, contentValues, null, null);
            if(rowsChanged == 0){
                Toast.makeText(this, "Сохранение по пизде",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, "Сохраняю изменения",Toast.LENGTH_LONG).show();
            }
        }

    }


    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String[] projection = {
                ClubContract.MemberEntry._ID,
                ClubContract.MemberEntry.COLUMN_FIRST_NAME,
                ClubContract.MemberEntry.COLUMN_LAST_NAME,
                ClubContract.MemberEntry.COLUMN_GENDER,
                ClubContract.MemberEntry.COLUMN_SPORT,
                ClubContract.MemberEntry.COLUMN_MONEY

        };


        return new CursorLoader(this,
                currentMemberUri,
                projection, null, null, null
        );
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        if (cursor.moveToFirst()) {
            int firstNameColumnIndex = cursor.getColumnIndex(ClubContract.MemberEntry.COLUMN_FIRST_NAME);
            int lastNameColumnIndex = cursor.getColumnIndex(ClubContract.MemberEntry.COLUMN_LAST_NAME);
            int genderColumnIndex = cursor.getColumnIndex(ClubContract.MemberEntry.COLUMN_GENDER);
            int sportColumnIndex = cursor.getColumnIndex(ClubContract.MemberEntry.COLUMN_SPORT);
            int moneyColumnIndex = cursor.getColumnIndex(ClubContract.MemberEntry.COLUMN_MONEY);

            String firstName = cursor.getString(firstNameColumnIndex);
            String lastName = cursor.getString(lastNameColumnIndex);
            int gender = cursor.getInt(genderColumnIndex);
            String sport = cursor.getString(sportColumnIndex);
            String money = cursor.getString(moneyColumnIndex);


            firstNameEditText.setText(firstName);
            lastNameEditText.setText(lastName);
            sportEditText.setText(sport);
            moneyEditText.setText(money);
            switch (gender){
                case ClubContract.MemberEntry.GENDER_MALE:
                    genderSpinner.setSelection(1);
                    break;
                case ClubContract.MemberEntry.GENDER_FEMALE:
                    genderSpinner.setSelection(2);
                    break;
                case ClubContract.MemberEntry.GENDER_UNKNOWN:
                    genderSpinner.setSelection(0);
                    break;
            }
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
    private void showDeleteMemberDialog(){
        Context context;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Удаляю ?");
        builder.setPositiveButton("Удалить ?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteMember();
            }
        });
        builder.setNegativeButton("Отменяю", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(dialog != null);
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void deleteMember() {
        if (currentMemberUri != null) {
            int rowsDeleted = getContentResolver().delete(currentMemberUri, null, null);
            if (rowsDeleted == 0) {
                Toast.makeText(this, "Ну все хуйня",Toast.LENGTH_LONG).show();
            } else  {
                Toast.makeText(this, "Сохраняется",Toast.LENGTH_LONG).show();
            }
            finish();
        }

    }
}
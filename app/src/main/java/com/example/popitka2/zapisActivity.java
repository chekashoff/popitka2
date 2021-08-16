package com.example.popitka2;

import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.v4.os.IResultReceiver;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.NavUtils;
import com.example.popitka2.zapisActivity;



public class zapisActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText etText;
    private EditText etText1;
    private EditText etText2;
    private EditText etText3;
    private EditText etText4;
    private EditText etText5;
    private EditText etText6;
    private EditText etText7;
    private EditText etText8;
    private EditText etText9;
    private EditText etText10;
    private EditText etText11;
    private EditText etText12;
    private EditText etText13;
    private EditText etText14;
    private EditText etText15;
    private EditText etText16;
    private EditText etText17;
    private EditText etText18;
    private EditText etText19;
    private EditText etText20;
    private EditText etText21;
    private EditText etText22;
    private EditText etText23;
    private EditText etText24;


    Button btnSave, btnLoad, btnDelete;

    SharedPreferences sPref;

    final String SAVED_TEXT = "saved_text";
    final String SAVED_TEXT1 = "saved_text1";
    final String SAVED_TEXT2 = "saved_text2";
    final String SAVED_TEXT3 = "saved_text3";
    final String SAVED_TEXT4 = "saved_text4";
    final String SAVED_TEXT5 = "saved_text5";
    final String SAVED_TEXT6 = "saved_text6";
    final String SAVED_TEXT7 = "saved_text7";
    final String SAVED_TEXT8 = "saved_text8";
    final String SAVED_TEXT9 = "saved_text9";
    final String SAVED_TEXT10 = "saved_text10";
    final String SAVED_TEXT11 = "saved_text11";
    final String SAVED_TEXT12 = "saved_text12";
    final String SAVED_TEXT13 = "saved_text13";
    final String SAVED_TEXT14 = "saved_text14";
    final String SAVED_TEXT15 = "saved_text15";
    final String SAVED_TEXT16 = "saved_text16";
    final String SAVED_TEXT17 = "saved_text17";
    final String SAVED_TEXT18 = "saved_text18";
    final String SAVED_TEXT19 = "saved_text19";
    final String SAVED_TEXT20 = "saved_text20";
    final String SAVED_TEXT21 = "saved_text21";
    final String SAVED_TEXT22 = "saved_text22";
    final String SAVED_TEXT23 = "saved_text23";
    final String SAVED_TEXT24 = "saved_text24";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Запись клиентов");

        setContentView(R.layout.activity_zapis);
        etText = (EditText) findViewById(R.id.id800);
        etText1 = (EditText) findViewById(R.id.id830);
        etText2 = (EditText) findViewById(R.id.id900);
        etText3 = (EditText) findViewById(R.id.id930);
        etText4 = (EditText) findViewById(R.id.id100);
        etText5 = (EditText) findViewById(R.id.id103);
        etText6 = (EditText) findViewById(R.id.id110);
        etText7 = (EditText) findViewById(R.id.id113);
        etText8 = (EditText) findViewById(R.id.id120);
        etText9 = (EditText) findViewById(R.id.id123);
        etText10 = (EditText) findViewById(R.id.id130);
        etText11 = (EditText) findViewById(R.id.id133);
        etText12 = (EditText) findViewById(R.id.id140);
        etText13 = (EditText) findViewById(R.id.id143);
        etText14 = (EditText) findViewById(R.id.id150);
        etText15 = (EditText) findViewById(R.id.id153);
        etText16 = (EditText) findViewById(R.id.id160);
        etText17 = (EditText) findViewById(R.id.id163);
        etText18 = (EditText) findViewById(R.id.id170);
        etText19 = (EditText) findViewById(R.id.id173);
        etText20 = (EditText) findViewById(R.id.id180);
        etText21 = (EditText) findViewById(R.id.id183);
        etText22 = (EditText) findViewById(R.id.id190);
        etText23 = (EditText) findViewById(R.id.id193);
        etText24 = (EditText) findViewById(R.id.id200);


        btnSave = (Button) findViewById(R.id.button2);
        btnSave.setOnClickListener(this);

        btnLoad = (Button) findViewById(R.id.button3);
        btnLoad.setOnClickListener(this);

        btnDelete = (Button) findViewById(R.id.button4) ;
        btnDelete.setOnClickListener(this);

        loadText();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button2:
                saveText();
                break;
            case R.id.button3:
                loadText();
                break;
            case R.id.button4:
                setPizda();
                break;
            default:
                break;
        }
    }

    void saveText() {
        sPref = getPreferences(MODE_PRIVATE);

        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, etText.getText().toString());
        ed.putString(SAVED_TEXT1, etText1.getText().toString());
        ed.putString(SAVED_TEXT2, etText2.getText().toString());
        ed.putString(SAVED_TEXT3, etText3.getText().toString());
        ed.putString(SAVED_TEXT4, etText4.getText().toString());
        ed.putString(SAVED_TEXT5, etText5.getText().toString());
        ed.putString(SAVED_TEXT6, etText6.getText().toString());
        ed.putString(SAVED_TEXT7, etText7.getText().toString());
        ed.putString(SAVED_TEXT8, etText8.getText().toString());
        ed.putString(SAVED_TEXT9, etText9.getText().toString());
        ed.putString(SAVED_TEXT10, etText10.getText().toString());
        ed.putString(SAVED_TEXT11, etText11.getText().toString());
        ed.putString(SAVED_TEXT12, etText12.getText().toString());
        ed.putString(SAVED_TEXT13, etText13.getText().toString());
        ed.putString(SAVED_TEXT14, etText14.getText().toString());
        ed.putString(SAVED_TEXT15, etText15.getText().toString());
        ed.putString(SAVED_TEXT16, etText16.getText().toString());
        ed.putString(SAVED_TEXT17, etText17.getText().toString());
        ed.putString(SAVED_TEXT18, etText18.getText().toString());
        ed.putString(SAVED_TEXT19, etText19.getText().toString());
        ed.putString(SAVED_TEXT20, etText20.getText().toString());
        ed.putString(SAVED_TEXT21, etText21.getText().toString());
        ed.putString(SAVED_TEXT22, etText22.getText().toString());
        ed.putString(SAVED_TEXT23, etText23.getText().toString());
        ed.putString(SAVED_TEXT24, etText24.getText().toString());
        ed.apply();
        Toast.makeText(this, "Сохраняю", Toast.LENGTH_SHORT).show();
    }

   public void loadText() {

        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        etText.setText(savedText);
        String savedText1 = sPref.getString(SAVED_TEXT1, "");
        etText1.setText(savedText1);
        String savedText2 = sPref.getString(SAVED_TEXT2, "");
        etText2.setText(savedText2);
        String savedText3 = sPref.getString(SAVED_TEXT3, "");
        etText3.setText(savedText3);
        String savedText4 = sPref.getString(SAVED_TEXT4, "");
        etText4.setText(savedText4);
        String savedText5 = sPref.getString(SAVED_TEXT5, "");
        etText5.setText(savedText5);
        String savedText6 = sPref.getString(SAVED_TEXT6, "");
        etText6.setText(savedText6);
        String savedText7 = sPref.getString(SAVED_TEXT7, "");
        etText7.setText(savedText7);
        String savedText8 = sPref.getString(SAVED_TEXT8, "");
        etText8.setText(savedText8);
        String savedText9 = sPref.getString(SAVED_TEXT9, "");
        etText9.setText(savedText9);
        String savedText10 = sPref.getString(SAVED_TEXT10, "");
        etText10.setText(savedText10);
        String savedText11 = sPref.getString(SAVED_TEXT11, "");
        etText11.setText(savedText11);
        String savedText12 = sPref.getString(SAVED_TEXT12, "");
        etText12.setText(savedText12);
        String savedText13 = sPref.getString(SAVED_TEXT13, "");
        etText13.setText(savedText13);

        String savedText14 = sPref.getString(SAVED_TEXT14, "");
        etText14.setText(savedText14);

        String savedText15 = sPref.getString(SAVED_TEXT15, "");
        etText15.setText(savedText15);
        String savedText16 = sPref.getString(SAVED_TEXT16, "");
        etText16.setText(savedText16);
        String savedText17 = sPref.getString(SAVED_TEXT17, "");
        etText17.setText(savedText17);
        String savedText18 = sPref.getString(SAVED_TEXT18, "");
        etText18.setText(savedText18);
        String savedText19 = sPref.getString(SAVED_TEXT19, "");
        etText19.setText(savedText19);
        String savedText20 = sPref.getString(SAVED_TEXT20, "");
        etText20.setText(savedText20);
        String savedText21 = sPref.getString(SAVED_TEXT21, "");
        etText21.setText(savedText21);
        String savedText22 = sPref.getString(SAVED_TEXT22, "");
        etText22.setText(savedText22);
        String savedText23 = sPref.getString(SAVED_TEXT23, "");
        etText23.setText(savedText23);
        String savedText24 = sPref.getString(SAVED_TEXT24, "");
        etText24.setText(savedText24);

        Toast.makeText(this, "Загружаю", Toast.LENGTH_SHORT).show();
    }
    private void setPizda() {

        sPref = getPreferences(MODE_PRIVATE);
        etText.setText(null);
        etText1.setText(null);
        etText2.setText(null);
        etText3.setText(null);
        etText4.setText(null);
        etText5.setText(null);
        etText6.setText(null);
        etText7.setText(null);
        etText8.setText(null);
        etText9.setText(null);
        etText10.setText(null);
        etText11.setText(null);
        etText12.setText(null);
        etText13.setText(null);
        etText14.setText(null);
        etText15.setText(null);
        etText16.setText(null);
        etText17.setText(null);
        etText18.setText(null);
        etText19.setText(null);
        etText20.setText(null);
        etText21.setText(null);
        etText22.setText(null);
        etText23.setText(null);
        etText24.setText(null);

        Toast.makeText(this, "Ща все поудаляю к херам", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }


}


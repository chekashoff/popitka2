package com.example.popitka2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.collection.ArraySet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

public class CalculatorActivity extends AppCompatActivity {
    TextView lastClient;
    ArrayList<String> users = new ArrayList<String>();
    ArrayList<String> selectedUsers = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView usersList;
    TextView allClient;
    Button btnSave;
    final String SAVED_TEXT = "saved_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        setTitle("Дневная касса" + R.id.userName + "рублей");
        usersList = (ListView) findViewById(R.id.usersList);
        // добавляем начальные элементы
        Collections.addAll(users);
        // получаем элемент ListView
        usersList = (ListView) findViewById(R.id.usersList);
        // создаем адаптер
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, users);
        // устанавливаем для списка адаптер
        usersList.setAdapter(adapter);
        btnSave = (Button) findViewById(R.id.save1);


        // обработка установки и снятия отметки в списке
        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // получаем нажатый элемент
                String user = adapter.getItem(position);
                if (usersList.isItemChecked(position))
                    selectedUsers.add(user);
                else
                    selectedUsers.remove(user);

            }
        });

    }


    public void add(View view) {

        EditText userName = (EditText) findViewById(R.id.userName);
        String user = userName.getText().toString();
        if (!user.isEmpty()) {
            adapter.add(user);
            userName.setText("");
            adapter.notifyDataSetChanged();
        }
    }

    public void remove(View view) {
        // получаем и удаляем выделенные элементы
        for (int i = 0; i < selectedUsers.size(); i++) {
            adapter.remove(selectedUsers.get(i));
        }
        // снимаем все ранее установленные отметки
        usersList.clearChoices();
        // очищаем массив выбраных объектов
        selectedUsers.clear();
        adapter.notifyDataSetChanged();

    }




}

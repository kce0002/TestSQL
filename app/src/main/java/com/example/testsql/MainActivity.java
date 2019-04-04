package com.example.testsql;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String DB_FILE = "sql.db";

    TextView nameTextView;
    Button prevButton;
    Button nextButton;
    Button formButton;

    SQLiteDatabase myDB;
    ArrayList<String> peopleList;
    int personId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameTextView = findViewById(R.id.nameView);
        prevButton = findViewById(R.id.prevBtn);
        nextButton = findViewById(R.id.nextBtn);
        formButton = findViewById(R.id.enterForm);

        peopleList = new ArrayList<>();
        personId = 0;

        boolean dbexist = getDatabasePath(DB_FILE).exists();

        myDB = openOrCreateDatabase(DB_FILE, Context.MODE_PRIVATE, null);
        if (!dbexist) {
            myDB.execSQL("CREATE TABLE People(ID int primary key, Name TEXT)");
            ContentValues row = new ContentValues();

            row.put("ID", 0);
            row.put("Name", "Kyle Ehlers");
            myDB.insert("People", null, row);

            row.put("ID", 1);
            row.put("Name", "Mary Adele Rackley");
            myDB.insert("People", null, row);

            row.put("ID", 2);
            row.put("Name", "Kody Ehlers");
            myDB.insert("People", null, row);

            row.put("ID", 3);
            row.put("Name", "Thomas Curow");
            myDB.insert("People", null, row);

            row.put("ID", 4);
            row.put("Name", "Sam Ehlers");
            myDB.insert("People", null, row);
        }

        Cursor cursor = myDB.query("People", null, null, null, null, null, null, null);
        cursor.moveToFirst();
        do {
            int id = cursor.getInt(0);
            String person = cursor.getString(1);
            peopleList.add(person);
        } while (cursor.moveToNext());
        cursor.close();
        nameTextView.setText(peopleList.get(personId));

    }

    public void clickPrev(View v) {
        if (personId == 0) {
            personId = peopleList.size() - 1;
        }
        else {
            personId--;
        }
        nameTextView.setText(peopleList.get(personId));
    }

    public void clickNext(View v) {
        if (personId == peopleList.size() - 1) {
            personId = 0;
        }
        else {
            personId++;
        }
        nameTextView.setText(peopleList.get(personId));
    }

    public void openForm(View v) {
        Intent newItemIntent = new Intent(this, Form.class);
        startActivity(newItemIntent);
    }
}

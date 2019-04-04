package com.example.testsql;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Form extends AppCompatActivity {

    EditText nameText;
    EditText idText;
    Button submitBtn;

    SQLiteDatabase myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        nameText = findViewById(R.id.name);
        idText = findViewById(R.id.nameId);
        submitBtn = findViewById(R.id.submitButton);
    }

    public void submit(View v) {
        int id = Integer.parseInt(idText.getText().toString());
        String name = nameText.getText().toString().trim();
        String query = "INSERT INTO People VALUES (" + id + ", '" + name + "')";

        boolean dbexist = getDatabasePath(MainActivity.DB_FILE).exists();
        myDB = openOrCreateDatabase(MainActivity.DB_FILE, Context.MODE_PRIVATE, null);
        if (dbexist) {
            myDB.execSQL(query);
        }

        Intent returnToMain = new Intent(this, MainActivity.class);
        startActivity(returnToMain);
    }
}

package com.example.hw3qestion6;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.LinkedList;

public class AddActivity extends AppCompatActivity
{
    private DataBaseManager dataBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //display Main screen
        setContentView(R.layout.activity_add);

        //create a database manager
        dataBaseManager = new DataBaseManager(this);

    }

    //event handler ends current screen
    public void back(View v)
    {
        finish();
    }

    //event handler adds new password
    public void add(View v)
    {
        //get the place
        EditText placeInput = findViewById(R.id.edtTxtPlace);
        String place = placeInput.getText().toString();

        //get the password
        EditText passwordInput = findViewById(R.id.editTxtPassword);
        String password = passwordInput.getText().toString();

        //create a password
        Password password1 = new Password(place,password);

        //adding password to database
        dataBaseManager.insert(password1);
    }



}

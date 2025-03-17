package com.example.hw3qestion6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;

public class DataBaseManager extends SQLiteOpenHelper
{
    //name of the data base
    private static final String DATABASE_NAME = "PASSWORD_DATABASE";

    //version of data vase
    private static final int DATABASE_VERSION = 1;

    //table name
    private static final String TABLE_NAME = "PASSWORD_TABLE";


    public DataBaseManager(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //SQL lite command to create table
        String command = "create table " + TABLE_NAME +"("+
                         "PLACE text,"+
                         "PASSWORD text)";

        db.execSQL(command);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    //method inserts a new password to data base
    public void insert(Password password)
    {
        //create SQL lite object
        SQLiteDatabase db = getWritableDatabase();

        //create a row
        ContentValues row = new ContentValues();

        //adding data to the row
        row.put("PLACE",password.getPlace());
        row.put("PASSWORD",password.getPassword());

        //insert row to database
        db.insert(TABLE_NAME,null,row);

        //close database
        db.close();
    }

    //method deletes a password from data base
    public void delete(String place)
    {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_NAME,"PLACE = ?",new String[]{place});

        db.close();
    }


    public void update(Password password)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put("PLACE",password.getPlace());
        row.put("PASSWORD",password.getPassword());
        db.update(TABLE_NAME,row,"PLACE = ?",new String[]{password.getPlace()});

        db.close();
    }

    //method returns a list of password stored in data base
    public LinkedList<Password> all()
    {
        SQLiteDatabase db = getWritableDatabase();

        LinkedList<Password> list = new LinkedList<>();

        Cursor cursor = db.query(TABLE_NAME,new String[]{"PLACE","PASSWORD"},null,null
                ,null, null,null);

        while (cursor.moveToNext())
        {
            //get place from table
            String place = cursor.getString(0);

            //get password from table
            String password = cursor.getString(1);

            //create password object
            Password password1 = new Password(place,password);

            //add password object to the list
            list.add(password1);
        }

        //close cursor
        cursor.close();

        //close database
        db.close();

        //return the list of passwords
        return list;
    }
}

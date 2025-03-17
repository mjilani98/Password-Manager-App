package com.example.hw3qestion6;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.widget.Toolbar;
import com.example.hw3qestion6.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //display Main screen
        setContentView(R.layout.activity_main);

        //create a tool bar , display
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //get the id of item selected from the menu
        int id = item.getItemId();

        if (id == R.id.Add) // id add is selected from menu
        {
            //start add activity
            Intent addActivity = new Intent(this,AddActivity.class);
            startActivity(addActivity);
        }
        else if (id == R.id.Delete) //if delete is selected from menu
        {
            //start delete activity
            Intent deleteActivity = new Intent(this,DeleteActivity.class);
            startActivity(deleteActivity);
        }
        else if(id == R.id.Update)
        {
            Intent updateActivity = new Intent(this,UpdateActivity.class);
            startActivity(updateActivity);
        }

        return super.onOptionsItemSelected(item);
    }


}
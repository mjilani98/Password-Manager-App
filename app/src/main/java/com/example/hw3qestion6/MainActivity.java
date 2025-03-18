package com.example.hw3qestion6;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.widget.Toolbar;
import com.example.hw3qestion6.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.LinkedList;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private DataBaseManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //display Main screen
        setContentView(R.layout.activity_main);

        //create a tool bar , display
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbManager = new DataBaseManager(this);



        updateView();

    }

    protected void onStart()
    {
        super.onStart();

        updateView();
    }

    private void updateView()
    {
        LinkedList<Password> list = dbManager.all();

        if (list.size() > 0)
        {
            int screenWidth = getWindowManager().getCurrentWindowMetrics().getBounds().width();
            int buttonWidth = screenWidth/2;
            int DP = (int)(getResources().getDisplayMetrics().density);
            int rows = (list.size() + 1)/2;
            int columns = 2;

            GridLayout grid = new GridLayout(this);
            grid.setRowCount(rows);
            grid.setColumnCount(columns);
            ScrollView.LayoutParams params = new ScrollView.LayoutParams(0, 0);
            params.width = columns*buttonWidth;
            params.height = rows*buttonWidth;
            grid.setLayoutParams(params);

            Button[] buttons = new Button[list.size()];
            for (int i = 0; i < list.size(); i++)
            {
                Password password = list.get(i);

                buttons[i] = new Button(this);
                buttons[i].setText(password.getPlace() );
                buttons[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                buttons[i].setTextColor(Color.parseColor("#000000"));
                buttons[i].setBackgroundColor(Color.parseColor("#BC8F8F"));
                buttons[i].setPadding(10*DP, 10*DP, 10*DP, 10*DP);
                buttons[i].setGravity(Gravity.CENTER);

                GridLayout.LayoutParams params1 = new GridLayout.LayoutParams();
                params1.width = buttonWidth;
                params1.height = buttonWidth;
                params1.rowSpec = GridLayout.spec(i/columns, 1);
                params1.columnSpec = GridLayout.spec(i%columns, 1);
                params1.topMargin = params1.bottomMargin = 1;
                params1.leftMargin = params1.rightMargin = 1;
                buttons[i].setLayoutParams(params1);

                ButtonHandler temp = new ButtonHandler(password.getPassword());
                buttons[i].setOnClickListener(temp);

                grid.addView(buttons[i]);
            }

            ScrollView scroll = findViewById(R.id.scroll);
            scroll.removeAllViewsInLayout();
            scroll.addView(grid);
        }
        else
        {
            ScrollView scroll = findViewById(R.id.scroll);
            scroll.removeAllViewsInLayout();
        }
    }

    private class ButtonHandler implements View.OnClickListener
    {
        private String password;

        public ButtonHandler(String password)
        {
            this.password = password;
        }

        public void onClick(View view)
        {
            Toast.makeText(MainActivity.this, password, Toast.LENGTH_SHORT).show();
        }
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
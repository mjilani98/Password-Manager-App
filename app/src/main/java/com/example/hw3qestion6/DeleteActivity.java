package com.example.hw3qestion6;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.LinkedList;

public class DeleteActivity extends AppCompatActivity
{
    private DataBaseManager dataBaseManager;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        dataBaseManager = new DataBaseManager(this);

        updateView();
    }

    public void updateView()
    {

        int DP = (int) (getResources().getDisplayMetrics().density);

        //get a list of password from the database
        LinkedList<Password> list = dataBaseManager.all();

        if(list.size()>1)
        {
            //create a radio group
            RadioGroup group = new RadioGroup(this);

            //setting parameters for radio group
            ScrollView.LayoutParams params = new ScrollView.LayoutParams(0, 0);
            params.width = ScrollView.LayoutParams.WRAP_CONTENT;
            params.height = ScrollView.LayoutParams.WRAP_CONTENT;
            params.topMargin = 500 * DP;
            group.setLayoutParams(params);

            RadioButton[] buttons = new RadioButton[list.size()];

            for (int i = 0; i < list.size(); i++)
            {
                Password password = list.get(i);

                buttons[i] = new RadioButton(this);
                buttons[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                buttons[i].setText("Place: "+password.getPlace().toUpperCase() + " \nPassword: " + password.getPassword());

                RadioButtonHandler temp = new RadioButtonHandler(password.getPlace());
                buttons[i].setOnClickListener(temp);

                group.addView(buttons[i]);
            }

            ScrollView scroll = findViewById(R.id.scroll);
            scroll.removeAllViewsInLayout();
            scroll.addView(group);

        }
        else
        {
            ScrollView scroll = findViewById(R.id.scroll);
            scroll.removeAllViewsInLayout();
        }
    }


    private class RadioButtonHandler implements View.OnClickListener
    {
        private String name;

        public RadioButtonHandler(String name)
        {
            this.name = name;
        }

        public void onClick(View view)
        {
            dataBaseManager.delete(name);

            updateView();
        }
    }

    public void back(View v)
    {
        finish();
    }
}

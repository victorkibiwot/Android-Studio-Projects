package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button calculate;
    private TextView resultTxt, nameText;
    private RadioButton male;
    private RadioButton female;
    private EditText age;
    private EditText feet;
    private EditText inches;
    private EditText weight;
    private int ageInt, feetInt, weightInt, inchInt;
    private String feedback, gender, status, bmi, ageText, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Initiate views
        initiateViews();


        //Setting listener with the driver methods
        calculateButtonListener();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menu = item.getItemId();

        if (menu == R.id.history_menu) {
            Intent historyIntent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(historyIntent);
        }
        return super.onOptionsItemSelected(item);
    }


    private void initiateViews() {
        resultTxt = findViewById(R.id.text_view_output);
        nameText = findViewById(R.id.name_text);

        //Radio references
        male = findViewById(R.id.male_button);
        female = findViewById(R.id.female_button);

        //EditText references
        age = findViewById(R.id.age_text);
        feet = findViewById(R.id.feet_text);
        inches = findViewById(R.id.inches_text);
        weight = findViewById(R.id.weight_text);


        //Button references
        calculate = findViewById(R.id.calculate_button);
    }

    private void getValues() {
        //Getting user value inputs as strings
        String weightText = weight.getText().toString().trim();
        String feetText = feet.getText().toString().trim();
        String inchesText = inches.getText().toString().trim();
        ageText = age.getText().toString().trim();
        name = nameText.getText().toString().trim();


        //Converting string values to integers
        feetInt = Integer.parseInt(feetText);
        inchInt = Integer.parseInt(inchesText);
        weightInt = Integer.parseInt(weightText);
        ageInt = Integer.parseInt(ageText);

        gender = returnGender();
    }

    private void calculateButtonListener() {
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Validate login
                int login = validateLogin();
                if (login == 6) {
                    //Getting values
                    getValues();

                    //Creating BMICalculator object
                    BMICalculator bmiCalculator = new BMICalculator(gender, ageInt, feetInt, inchInt, weightInt);
                    feedback = bmiCalculator.getFinalFeedback();

                    //Set Feedback to user
                    resultTxt.setText(feedback);

                    //Get status
                    status = bmiCalculator.getStatus();

                    //Getting bmi
                    bmi = bmiCalculator.getBMI();

                    //create Record
                    Record record = new Record(name, ageText, bmi, status);

                    //Making a reference to the new customer database
                    DatabaseManager databaseManager = new DatabaseManager(MainActivity.this);

                    //Add record to database
                    databaseManager.addOne(record);
                } else {
                    //Warning
                    View view = findViewById(R.id.main_act);
                    Snackbar snackbar = Snackbar.make(view, "All the fields need to be filled. Try again!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    resultTxt.setText("");
                }

            }
        });
    }

    private String returnGender() {
        if (male.isChecked()) {
            return "Male";
        } else {
            return "Female";
        }
    }

    private int validateLogin() {
        int login = 0;
        if (nameText.getText().toString().length() > 0) {
            ++login;
        }
        if (male.isChecked() == true || female.isChecked() == true) {
            ++login;
        }

        if (age.getText().toString().length() > 0) {
            ++login;
        }

        if (feet.getText().toString().length() > 0) {
            ++login;
        }

        if (weight.getText().toString().length() > 0) {
            ++login;
        }

        if (inches.getText().toString().length() > 0) {
            ++login;
        }
        return login;
    }

}
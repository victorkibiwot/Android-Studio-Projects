package com.example.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {
ListView listView;
Button back_button, clear_history;
DatabaseManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);
        dbManager = new DatabaseManager(HistoryActivity.this);

        //Getting the view references
        listView = findViewById(R.id.list_view);
        back_button = findViewById(R.id.back_button);
        clear_history = findViewById(R.id.clear_history);

        //Setting button listeners
        clear_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.clearDatabase();
                //Print Everyone
                printEveryone();

                //Notification
                View view = findViewById(R.id.history_activity);
                Snackbar snackbar = Snackbar.make(view, "History Cleared", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(HistoryActivity.this, MainActivity.class);
                startActivity(intentHome);
            }
        });

        //Print Everyone
        printEveryone();

    }

    private void printEveryone() {
        //Getting everyone information on a list
        List<Record> everyone = dbManager.getEveryone();

        //Array adapter
        ArrayAdapter recordArrayAdapter = new ArrayAdapter<Record>(HistoryActivity.this, android.R.layout.simple_list_item_1, everyone);
        listView.setAdapter(recordArrayAdapter);
    }
}

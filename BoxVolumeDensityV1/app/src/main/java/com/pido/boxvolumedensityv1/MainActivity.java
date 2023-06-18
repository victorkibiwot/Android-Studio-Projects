package com.pido.boxvolumedensityv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView mTextView, denText;
Button button;
int height, width, length, volume;
float density, weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Inflates the main activity view

        // Get volume
        // Create Object (you have to pass in h, w, l, weight)
        // How you get them is up to the controller.
        update_height_width_length();

        // Get values
        getValues();


        VolumeDensity vd = new VolumeDensity(height, width, length, weight);

        density = vd.getDensity();
        denText = findViewById(R.id.density);
        denText.setText((String.format("%.4f" ,density)));

        volume = vd.getVolume();
        mTextView = findViewById(R.id.volume);
        mTextView.setText(String.format("%d",volume));

        // Configure the button to next activity
        configureButton();


    }

// Default (initial) values
    private void update_height_width_length(){
        mTextView = findViewById(R.id.height);
        String heightStr = mTextView.getText().toString();
        height = Integer.parseInt(heightStr);  //TODO shortening these 3 steps

        mTextView = findViewById(R.id.width);
        String widthStr = mTextView.getText().toString();
        width = Integer.parseInt(widthStr);

        mTextView = findViewById(R.id.length);
        String lengthStr = mTextView.getText().toString();
        length = Integer.parseInt(lengthStr);

        mTextView = findViewById(R.id.weight);
        String weightStr = mTextView.getText().toString();
        weight = Float.parseFloat(weightStr);


    }

    private void configureButton(){
        button = findViewById(R.id.change_data);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }


    // Method to Receive values through Intent
    private void getValues(){
        Intent i = getIntent();
        weight = i.getFloatExtra("weight_", 10);
        height = i.getIntExtra("height_", 10);
        length = i.getIntExtra("length_", 10);
        width = i.getIntExtra("width_", 10);

        // Updating values on TextViews
        mTextView = findViewById(R.id.height);
        mTextView.setText(String.valueOf(height));
        mTextView = findViewById(R.id.width);
        mTextView.setText(String.valueOf(width));
        mTextView = findViewById(R.id.length);
        mTextView.setText(String.valueOf(length));
        mTextView = findViewById(R.id.weight);
        mTextView.setText(String.valueOf(weight));
    }

}
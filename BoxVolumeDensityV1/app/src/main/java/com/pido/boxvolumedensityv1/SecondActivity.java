package com.pido.boxvolumedensityv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity{
    Button conf;
    EditText edl, edw, edh, edm;
    int h, l, w;
    float m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Configure button to go back to first activity
        configureUpdateButton();
    }

    private void configureUpdateButton() {
        conf = findViewById(R.id.done);
        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendValues();
            }
        });
    }

    private void sendValues(){
        edh = findViewById(R.id.height_edit);
        edl = findViewById(R.id.length_edit);
        edw = findViewById(R.id.width_edit);
        edm = findViewById(R.id.weight_edit);


        Intent intent2 = new Intent(SecondActivity.this, MainActivity.class);


        // Store the data respective variables
        if((edh.getText().toString()).length() > 0){
            h = Integer.parseInt(edh.getText().toString().trim());
            intent2.putExtra("height_", h);
        }
        if((edl.getText().toString()).length() > 0){
            l = Integer.parseInt(edl.getText().toString().trim());
            intent2.putExtra("length_", l);
        }
        if((edw.getText().toString()).length() > 0){
            w = Integer.parseInt(edw.getText().toString().trim());
            intent2.putExtra("width_", w);
        }
        if((edm.getText().toString()).length() > 0){
            m = Float.parseFloat(edm.getText().toString().trim());
            intent2.putExtra("weight_", m);
        }

        startActivity(intent2);

        // An alternative is to use finish() to go back to the previous
        // activity instead of creating a new intent
    }

}

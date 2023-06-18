package com.example.asignpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    Button buttonApply;
    private CheckBox check1, check2, check3, check4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radio_group);
        buttonApply = findViewById(R.id.apply_btn);
        init();
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                StringBuffer res = new StringBuffer();
                res.append("Burger Type: \n");
                res.append(radioButton.getText().toString()).append("\n\n");


                res.append("Toppings: \n");
                if(check1.isChecked()){
                    res.append(check1.getText().toString()).append("\n");
                }
                if(check2.isChecked()){
                    res.append(check2.getText().toString()).append("\n");
                }
                if(check3.isChecked()){
                res.append(check3.getText().toString()).append("\n");
                }
                if(check4.isChecked()){
                res.append(check4.getText().toString()).append("\n");
                }

                Toast.makeText(MainActivity.this,
                        res.toString(),
                        Toast.LENGTH_LONG).show();

            }
        });

    }

    public void checkButton(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "Selected Radio Button: " + radioButton.getText(), Toast.LENGTH_SHORT).show();

    }



    private void init(){
        check1 = findViewById(R.id.check1);
        check2 = findViewById(R.id.check2);
        check3 = findViewById(R.id.check3);
        check4 = findViewById(R.id.check4);
        buttonApply = findViewById(R.id.apply_btn);
    }
}
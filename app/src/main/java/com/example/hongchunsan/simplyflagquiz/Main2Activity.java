package com.example.hongchunsan.simplyflagquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final Button btnA = (Button) findViewById(R.id.buttonA);
        final Button btnB = (Button) findViewById(R.id.buttonB);
        final Button btnC = (Button) findViewById(R.id.buttonC);
        final Button btnD = (Button) findViewById(R.id.buttonD);

        final TextView result = (TextView) findViewById(R.id.questionnumber);


        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("Your answer is corret!");

            }
        });

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("Incorrect!");
            }
        });

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("Incorrect!");
            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("Incorrect!");
            }
        });
    }
}

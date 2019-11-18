package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class second_age extends AppCompatActivity {
    private TextView q1,q2,q3,q4,q5,q6,q7,q8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_age);

        q1 = (TextView)findViewById(R.id.second_q1);
        q2 = (TextView)findViewById(R.id.second_q2);
        q3 = (TextView)findViewById(R.id.second_q3);
        q4 = (TextView)findViewById(R.id.second_q4);
        q5 = (TextView)findViewById(R.id.second_q5);
        q6 = (TextView)findViewById(R.id.second_q6);
        q7 = (TextView)findViewById(R.id.second_q7);
        q8 = (TextView)findViewById(R.id.second_q8);

        final Intent intent = new Intent(getApplicationContext(),popup.class);

        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        q5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        q6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        q7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        q8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}

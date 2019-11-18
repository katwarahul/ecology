package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class first_age extends AppCompatActivity {
    private TextView q1,q2,q3,q4,q5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_age);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        q1 = (TextView)findViewById(R.id.first_q1);
        q2 = (TextView)findViewById(R.id.first_q2);
        q3 = (TextView)findViewById(R.id.first_q3);
        q4 = (TextView)findViewById(R.id.first_q4);
        q5 = (TextView)findViewById(R.id.first_q5);
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

    }
}

package com.example.maciej.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Intent intent = getIntent();
        int sum = intent.getIntExtra("addition", 0);
        TextView addNumbers = findViewById(R.id.textView2);
        addNumbers.setText((Integer.toString(sum)));
    }
}

package com.maciej.calc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        //Intent intent = getIntent();

    }
    public void clearBox(View view) {
        TextView historyBox = findViewById(R.id.resultBox);
        historyBox.setText("History cleared!");
    }
}

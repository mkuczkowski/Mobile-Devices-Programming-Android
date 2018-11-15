package com.maciej.calc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Intent intent = getIntent();
        String formulas = intent.getStringExtra("package");
        TextView historyBox = findViewById(R.id.resultBox);
        historyBox.setMovementMethod(new ScrollingMovementMethod());
        historyBox.append(formulas);
    }
}

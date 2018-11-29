package com.maciej.calc;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class History extends AppCompatActivity {
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Intent intent = getIntent();
        String formulas = intent.getStringExtra("package");
        myDb = new DatabaseHelper(this);
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()) {
            buffer.append("ID: " + res.getString(0) + "\n");
            buffer.append("Expression: " + res.getString(1) + "\n");
        }
        TextView historyBox = findViewById(R.id.resultBox);
        historyBox.setMovementMethod(new ScrollingMovementMethod());
        historyBox.append(buffer.toString());
    }
}

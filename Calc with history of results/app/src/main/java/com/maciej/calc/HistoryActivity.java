package com.maciej.calc;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class HistoryActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        databaseHelper = new DatabaseHelper(this);
        displayHistory();
    }

    private String readDataFromDatabase() {
        Cursor historyData = databaseHelper.getAllData();
        if(historyData.getCount() == 0) return null;
        StringBuilder operationsHistory = new StringBuilder();
        while(historyData.moveToNext()) {
            operationsHistory.append(historyData.getString(1)).append("\n");
        }
        return operationsHistory.toString();
    }

    private void displayHistory() {
        String historyData = readDataFromDatabase();
        if(historyData == null) return;
        TextView historyBox = findViewById(R.id.resultBox);
        historyBox.setMovementMethod(new ScrollingMovementMethod());
        historyBox.append(historyData);
    }
}
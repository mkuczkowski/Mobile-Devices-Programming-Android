package com.maciej.calc;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {
    public String toSend = "\n";
    public String currentExp = "";
    EditText exp;
    Button btnSend;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        exp = findViewById(R.id.editText);
        btnSend = findViewById(R.id.calculate);
    }

    public void onClick(View view) {
        EditText expression = findViewById(R.id.editText);
        String formula = expression.getText().toString();
        Expression e = new Expression(formula);
        TextView res = findViewById(R.id.result);
        String wholeFormula = e.getExpressionString() + " = " + e.calculate();
        res.setText(wholeFormula);
        toSend += wholeFormula + "\n";
        currentExp = wholeFormula;
        if(myDb.insertData(currentExp))
            Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();

    }

    public void showResults(View view) {
        Intent intent = new Intent(this, History.class);
        intent.putExtra("package", toSend);
        startActivity(intent);
    }

    public void clearHistory(View view) {
        myDb.deleteData();
        Toast.makeText(MainActivity.this, "History cleared", Toast.LENGTH_LONG).show();

        toSend = "\n";
    }
}

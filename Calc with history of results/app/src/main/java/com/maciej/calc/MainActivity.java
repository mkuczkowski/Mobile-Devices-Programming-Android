package com.maciej.calc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {
    public String toSend = "\n";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View view) {
        EditText expression = findViewById(R.id.editText);
        String formula = expression.getText().toString();
        Expression e = new Expression(formula);
        TextView res = findViewById(R.id.result);
        String wholeFormula = e.getExpressionString() + " = " + e.calculate();
        res.setText(wholeFormula);
        toSend += wholeFormula + "\n";
    }

    public void showResults(View view) {
        Intent intent = new Intent(this, History.class);
        intent.putExtra("package", toSend);
        startActivity(intent);
    }

    public void clearHistory(View view) {
        toSend = "\n";
    }
}

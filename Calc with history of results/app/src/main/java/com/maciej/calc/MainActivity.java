package com.maciej.calc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper myDb;
    private TextView expressionField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expressionField = findViewById(R.id.display);
        myDb = new DatabaseHelper(this);
    }

    public void calculateCurrentExpression(View view) {
        String formulaToCalculate = expressionField.getText().toString();
        Expression expression = new Expression(formulaToCalculate);
        String wholeFormula = expression.getExpressionString() + " = " + expression.calculate();
        displayResult(String.valueOf(expression.calculate()));
        insertNewOperation(wholeFormula);
    }

    private void displayResult(String resultValue) { expressionField.setText(resultValue); }

    private void insertNewOperation(String operationToInsert) {
        if (myDb.insertData(operationToInsert))
            Toast.makeText(MainActivity.this, "New operation saved!", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
    }

    public void showHistory(View view) {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    public void clearHistory(View view) {
        myDb.deleteData();
        Toast.makeText(MainActivity.this, "History has been cleared!", Toast.LENGTH_LONG).show();
    }

    public void btn1Clicked(View view) { expressionField.append("1"); }
    public void btn2Clicked(View view) { expressionField.append("2"); }
    public void btn3Clicked(View view) { expressionField.append("3"); }
    public void btn4Clicked(View view) { expressionField.append("4"); }
    public void btn5Clicked(View view) { expressionField.append("5"); }
    public void btn6Clicked(View view) { expressionField.append("6"); }
    public void btn7Clicked(View view) { expressionField.append("7"); }
    public void btn8Clicked(View view) { expressionField.append("8"); }
    public void btn9Clicked(View view) { expressionField.append("9"); }
    public void btn0Clicked(View view) { expressionField.append("0"); }
    public void btnDotClicked(View view) { expressionField.append("."); }
    public void btnClearClicked(View view) { expressionField.setText(""); }
    public void btnPlusClicked(View view) { expressionField.append("+"); }
    public void btnMinusClicked(View view) { expressionField.append("-"); }
    public void btnMultiplyClicked(View view) { expressionField.append("*"); }
    public void btnDivideClicked(View view) { expressionField.append("/"); }
    public void btnPercentClicked(View view) { expressionField.append("%"); }
}
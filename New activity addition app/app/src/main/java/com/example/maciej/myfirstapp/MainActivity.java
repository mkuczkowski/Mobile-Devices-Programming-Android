package com.example.maciej.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText firstNumber;
    EditText secondNumber;
    Button btnAdd;
    int number1,number2,sum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstNumber = findViewById(R.id.txtNumber1);
        secondNumber = findViewById(R.id.txtNumber2);
        btnAdd = findViewById(R.id.btnAdd);
    }

    public void sendAdditionResult(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        number1 = Integer.parseInt(firstNumber.getText().toString());
        number2 = Integer.parseInt(secondNumber.getText().toString());
        sum = number1 + number2;
        intent.putExtra("addition", sum);
        startActivity(intent);
    }
}

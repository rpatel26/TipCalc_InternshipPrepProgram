package com.example.tipcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button12;
    Button button15;
    Button button18;
    Button button20;

    TextView tipTextView;
    TextView totalTextView;

    EditText billAmountEditText;
    EditText tipPercentageEditText;
    Double tipPercentage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tipPercentage = 0.0;

        button12 = findViewById(R.id.button12);
        button15 = findViewById(R.id.button15);
        button18 = findViewById(R.id.button18);
        button20 = findViewById(R.id.button20);

        tipTextView = findViewById(R.id.tipTextView);
        totalTextView = findViewById(R.id.totalTextView);

        billAmountEditText = findViewById(R.id.userInputEditText);
        tipPercentageEditText = findViewById(R.id.tipPercentageEditText);

        billAmountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateTip();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tipPercentageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tipPercentage = Double.parseDouble(s.toString()) / 100;
                updateTip();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // ignore
            }
        });
    }

    public void updateTip(){
        if(!TextUtils.isEmpty(billAmountEditText.getText().toString())){
            Double tip = tipPercentage * Double.parseDouble(billAmountEditText.getText().toString());
            System.out.println("Tip: " + tip);
            tipTextView.setText("$" + String.format("%.2f", tip));
            Double total = tip + Double.parseDouble(billAmountEditText.getText().toString());
            totalTextView.setText("$" + String.format("%.2f", total));
        }
        else{
            tipTextView.setText("$0.00");
            totalTextView.setText("$0.00" );
        }
    }

    public void buttonClick(View v) {
        if(v == button12){
            tipPercentage = 0.12;
            button12.setBackgroundTintList(getResources().getColorStateList(R.color.button_selected));
            button15.setBackgroundTintList(getResources().getColorStateList(R.color.button_deselected));
            button18.setBackgroundTintList(getResources().getColorStateList(R.color.button_deselected));
            button20.setBackgroundTintList(getResources().getColorStateList(R.color.button_deselected));
        }
        else if(v == button15){
            tipPercentage = 0.15;
            button12.setBackgroundTintList(getResources().getColorStateList(R.color.button_deselected));
            button15.setBackgroundTintList(getResources().getColorStateList(R.color.button_selected));
            button18.setBackgroundTintList(getResources().getColorStateList(R.color.button_deselected));
            button20.setBackgroundTintList(getResources().getColorStateList(R.color.button_deselected));
        }
        else if(v == button18){
            tipPercentage = 0.18;
            button12.setBackgroundTintList(getResources().getColorStateList(R.color.button_deselected));
            button15.setBackgroundTintList(getResources().getColorStateList(R.color.button_deselected));
            button18.setBackgroundTintList(getResources().getColorStateList(R.color.button_selected));
            button20.setBackgroundTintList(getResources().getColorStateList(R.color.button_deselected));
        }
        else if(v == button20){
            tipPercentage = 0.2;
            button12.setBackgroundTintList(getResources().getColorStateList(R.color.button_deselected));
            button15.setBackgroundTintList(getResources().getColorStateList(R.color.button_deselected));
            button18.setBackgroundTintList(getResources().getColorStateList(R.color.button_deselected));
            button20.setBackgroundTintList(getResources().getColorStateList(R.color.button_selected));
        }
        updateTip();
    }

    public void historyButtonClicked(View v){
        // Switch Screen....
    }
}

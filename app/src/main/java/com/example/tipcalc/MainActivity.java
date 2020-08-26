package com.example.tipcalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

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
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();

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

        billAmountEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    if(!TextUtils.isEmpty(billAmountEditText.getText().toString())) {{
                        db.collection("History").document("UserID").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                Map data = task.getResult().getData();
                                ArrayList<String> billHistory = (ArrayList) data.get("Bill Amount");
                                billHistory.add(billAmountEditText.getText().toString());
                                System.out.println("Bill History = " + billHistory);
                                db.collection("History").document("UserID").update("Bill Amount", billHistory);
                            }
                        });
                    }}
                }
                return false;
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

        tipPercentageEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    if(!TextUtils.isEmpty(tipPercentageEditText.getText().toString())) {{
                        db.collection("History").document("UserID").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                Map data = task.getResult().getData();
                                ArrayList<String> tipHistory = (ArrayList) data.get("Custom Tip Array");
                                tipHistory.add(tipPercentageEditText.getText().toString());
                                System.out.println("Bill History = " + tipHistory);
                                db.collection("History").document("UserID").update("Custom Tip Array", tipHistory);
                            }
                        });
                    }}
                }
                return false;
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
            db.collection("History").document("UserID").update("Custom Tip", "12");
        }
        else if(v == button15){
            tipPercentage = 0.15;
            button12.setBackgroundTintList(getResources().getColorStateList(R.color.button_deselected));
            button15.setBackgroundTintList(getResources().getColorStateList(R.color.button_selected));
            button18.setBackgroundTintList(getResources().getColorStateList(R.color.button_deselected));
            button20.setBackgroundTintList(getResources().getColorStateList(R.color.button_deselected));
            db.collection("History").document("UserID").update("Custom Tip", "15");
        }
        else if(v == button18){
            tipPercentage = 0.18;
            button12.setBackgroundTintList(getResources().getColorStateList(R.color.button_deselected));
            button15.setBackgroundTintList(getResources().getColorStateList(R.color.button_deselected));
            button18.setBackgroundTintList(getResources().getColorStateList(R.color.button_selected));
            button20.setBackgroundTintList(getResources().getColorStateList(R.color.button_deselected));
            db.collection("History").document("UserID").update("Custom Tip", "18");
        }
        else if(v == button20){
            tipPercentage = 0.2;
            button12.setBackgroundTintList(getResources().getColorStateList(R.color.button_deselected));
            button15.setBackgroundTintList(getResources().getColorStateList(R.color.button_deselected));
            button18.setBackgroundTintList(getResources().getColorStateList(R.color.button_deselected));
            button20.setBackgroundTintList(getResources().getColorStateList(R.color.button_selected));
            db.collection("History").document("UserID").update("Custom Tip", "20");
        }
        updateTip();
    }

    public void historyButtonClicked(View v){
        // Switch Screen....
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }
}

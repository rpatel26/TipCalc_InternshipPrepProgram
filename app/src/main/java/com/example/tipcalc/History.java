package com.example.tipcalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

public class History extends AppCompatActivity {

    ListView historyListView;
    ArrayList<String> historyList;
    ArrayList<String> tipPercentageList;
    HistoryItemAdaptor historyItemAdaptor;

    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyListView = findViewById(R.id.historyListView);
        historyList = new ArrayList<>();

        db = FirebaseFirestore.getInstance();
        db.collection("History").document("UserID").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                Map data = task.getResult().getData();
                historyList = (ArrayList) data.get("Bill Amount");
                tipPercentageList = (ArrayList) data.get("Custom Tip Array");
                System.out.println("Bill ");
                if(historyList != null && tipPercentageList != null)
                    notifyDataSetChange();
            }
        });
    }

    public void notifyDataSetChange(){
        historyItemAdaptor = new HistoryItemAdaptor(this, historyList, tipPercentageList);
        historyListView.setAdapter(historyItemAdaptor);
    }
}

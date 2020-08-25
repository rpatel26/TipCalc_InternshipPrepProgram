package com.example.tipcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    ListView historyListView;
    ArrayList<String> historyList;
    HistoryItemAdaptor historyItemAdaptor;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyListView = findViewById(R.id.historyListView);
        historyList = new ArrayList<>();
    }

    public void notifyDataSetChange(){
        historyItemAdaptor = new HistoryItemAdaptor(this, historyList);
        historyListView.setAdapter(historyItemAdaptor);
    }
}

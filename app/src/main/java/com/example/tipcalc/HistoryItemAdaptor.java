package com.example.tipcalc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryItemAdaptor extends BaseAdapter {
    LayoutInflater mInflator;
    ArrayList<String> historyList;

    public HistoryItemAdaptor(Context context, ArrayList historyList){
        this.historyList = historyList;
        mInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return historyList.size();
    }

    @Override
    public Object getItem(int position) {
        return historyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflator.inflate(R.layout.history_listview_details, null);
        TextView billAmountTextView = v.findViewById(R.id.billAmountTextView);
        TextView tip15TextView = v.findViewById(R.id.tip15);
        TextView tip18TextView = v.findViewById(R.id.tip18);
        TextView tip20TextView = v.findViewById(R.id.tip20);
        TextView total15TextView = v.findViewById(R.id.total15);
        TextView total18TextView = v.findViewById(R.id.total18);
        TextView total20TextView = v.findViewById(R.id.total20);

        Double billAmount = Double.parseDouble(historyList.get(position));

        billAmountTextView.setText("$" + String.format("%.2f", billAmount));
        tip15TextView.setText("Tip (15%): $" + String.format("%.2f", billAmount * 0.15));
        tip18TextView.setText("Tip (18%): $" + String.format("%.2f", billAmount * 0.18));
        tip20TextView.setText("Tip (20%): $" + String.format("%.2f", billAmount * 0.20));

        total15TextView.setText("Total (15%): $" + String.format("%.2f", billAmount * 1.15));
        total18TextView.setText("Total (18%): $" + String.format("%.2f", billAmount * 1.18));
        total20TextView.setText("Total (20%): $" + String.format("%.2f", billAmount * 1.20));

        return v;
    }
}

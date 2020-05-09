package com.example.astrapedia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class vehicle_adapter extends ArrayAdapter<Vehicle_data> {
    List<Vehicle_data> dataset;
    Context con;
    public vehicle_adapter(List<Vehicle_data> data, Context context) {
        super(context, R.layout.vehicle_view, data);
        this.dataset = data;
        this.con = context;
    }

    public View getView(final int pos, View v1, ViewGroup vg) {
        LayoutInflater li = LayoutInflater.from(con);

        View view = li.inflate(R.layout.vehicle_view,null,false);

        TextView tx1 = view.findViewById(R.id.tx1);
        TextView tx2 = view.findViewById(R.id.tx2);
        TextView tx3 = view.findViewById(R.id.tx3);

        tx1.setText("Name : " + dataset.get(pos).name);
        tx2.setText("Country : " + dataset.get(pos).ctry);
        tx3.setText("Type : " + dataset.get(pos).type);

        return view;
    }
}

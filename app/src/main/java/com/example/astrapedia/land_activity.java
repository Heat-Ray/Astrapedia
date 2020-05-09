package com.example.astrapedia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class land_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_activity);

        ListView lv1 = (ListView) findViewById(R.id.lv1);
        List<Vehicle_data> data = new ArrayList<>();

        VehicleDB cdb = new VehicleDB(land_activity.this);
        ArrayList<HashMap<String, String> > table_data = cdb.getvehicles("Vtype","Tank");
        for(HashMap<String , String> hs: table_data) {
            data.add(new Vehicle_data(hs.get("Name"), hs.get("Image_url"), hs.get("Country"), hs.get("Type")));
        }
        vehicle_adapter veh = new vehicle_adapter(data,this);
        lv1.setAdapter(veh);
    }
}

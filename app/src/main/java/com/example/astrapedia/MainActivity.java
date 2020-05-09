package com.example.astrapedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Document doc, extrac;
    private ArrayList <String> air = new ArrayList<String>();
    private ArrayList <String> land = new ArrayList<String>();
    private ArrayList <String> fleet = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getrestree(new String[] {"USA_aircraft","Germany_aircraft","USSR_aircraft","Britain_aircraft","Japan_aircraft","China_aircraft",
                "Italy_aircraft","France_aircraft","Sweden_aircraft"}, "Aircraft", R.id.Aircraft);
        getrestree(new String[] {"USA_helicopters","Germany_helicopters","USSR_helicopters","Britain_helicopters","Japan_helicopters",
                "Italy_helicopters","France_helicopters"}, "Helicopter", R.id.Helicopter);
        getrestree(new String[] {"USA_ground_vehicles","Germany_ground_vehicles","USSR_ground_vehicles","Britain_ground_vehicles","Japan_ground_vehicles","China_ground_vehicles",
                "Italy_ground_vehicles","France_ground_vehicles","Sweden_ground_vehicles"}, "Tank", R.id.Tank);
        getrestree(new String[] {"USA_ships","Germany_ships","USSR_ships","Britain_ships","Japan_ships"}, "Ship", R.id.Ship);

        Button b1 = (Button)findViewById(R.id.Aircraft);
        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this,air_activity.class);
                startActivity(in);
            }
        });

        Button b2 = (Button)findViewById(R.id.Ship);
        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this,fleet_activity.class);
                startActivity(in);
            }
        });

        Button b3 = (Button)findViewById(R.id.Tank);
        b3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this,land_activity.class);
                startActivity(in);
            }
        });



    }

    private void getrestree(final String[] atypes, final String vtype, final int id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "https://wiki.warthunder.com";

                VehicleDB cdb = new VehicleDB(MainActivity.this);
                if(!cdb.checkexistence())
                {
                    for (String s1: atypes) {
                        try {
                            doc = Jsoup.connect("https://wiki.warthunder.com/Category:"+s1).get();
                            Elements div = doc.getElementsByClass("tree");
                            Elements tr = div.first().select("a");
                            for (Element bx: tr) {
                                String name = bx.attr("title");
                                String link = url + bx.attr("href");
                                String type = "";
                                String img_url = url + bx.select("img").first().attr("url");
                                if( bx.select("img").first().attr("src").equals("/images/3/39/Item_own.png")) {
                                    type = "Normal";
                                }
                                else {
                                    type = "Premium";
                                }
                                String[] country = s1.split("_");
                                String ctry = country[0];
                                //System.out.println(ctry + " " + name + " " + link + " " + type + " " + img_url + " " + vtype);
                                cdb.insertvehicles(name,link,type,img_url,ctry,vtype);
                            }


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Button b1 = (Button)findViewById(id);
                        b1.setVisibility(View.VISIBLE);
                        TextView ld1 = (TextView)findViewById(R.id.ld);
                        ld1.setVisibility(View.GONE);
                    }

                });
            }
        }).start();
    }

}

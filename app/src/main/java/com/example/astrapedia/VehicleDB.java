package com.example.astrapedia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class VehicleDB extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "thunder";
    private static final String TABLE = "vehicles";
    private static final String Name = "Name";
    private static final String Link = "Link";
    private static final String Type = "Type";
    private static final String Image_url = "Image_url";
    private static final String Country = "Country";
    private static final String Vtype = "Vtype";
    public VehicleDB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE + "("
                + Name + " TEXT," + Link + " TEXT,"
                + Type + " TEXT," + Image_url + " TEXT,"
                + Country + " TEXT,"
                + Vtype + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    void insertvehicles(String name, String link, String type, String image_url, String country, String vtype) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(Name, name);
        cValues.put(Link, link);
        cValues.put(Type, type);
        cValues.put(Image_url, image_url);
        cValues.put(Country, country);
        cValues.put(Vtype, vtype);

        long newRowId = db.insert(TABLE, null, cValues);
        db.close();
    }

    public ArrayList<HashMap<String, String>> getvehicles(String param, String value){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> vehicles = new ArrayList<>();
        String query = new String();
        if(param.equals("-")) {
            query = "SELECT * FROM "+ TABLE;
        }
        else {
            query = "SELECT * FROM "+ TABLE + " WHERE " + param + " = " + "'"+ value + "'";
        }

        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> vehicle = new HashMap<>();
            vehicle.put("Name",cursor.getString(cursor.getColumnIndex(Name)));
            vehicle.put("Link",cursor.getString(cursor.getColumnIndex(Link)));
            vehicle.put("Type",cursor.getString(cursor.getColumnIndex(Type)));
            vehicle.put("Image_url",cursor.getString(cursor.getColumnIndex(Image_url)));
            vehicle.put("Country",cursor.getString(cursor.getColumnIndex(Country)));
            vehicle.put("Vtype",cursor.getString(cursor.getColumnIndex(Vtype)));
            vehicles.add(vehicle);
        }
        db.close();
        return vehicles;

    }

    public boolean checkexistence() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM "+ TABLE;
        Cursor mCursor = db.rawQuery(sql, null);
        if (mCursor.getCount() > 0) {
            return true;
        }
        mCursor.close();
        db.close();
        return false;
    }
}

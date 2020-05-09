package com.example.astrapedia;

public class Vehicle_data {
    String name;
    String img_url;
    String ctry;
    String type;

    public Vehicle_data(String n, String im, String ct, String ty) {
        this.name = n;
        this.img_url = im;
        this.ctry = ct;
        this.type = ty;
    }

    public String getName() {
        return name;
    }

    public String getImg_url() {
        return  img_url;
    }

    public String getCtry() {
        return ctry;
    }

    public String getType() {
        return type;
    }
}

package com.example.austcanteen;

import android.graphics.Bitmap;

/**
 * Created by user on 1/22/2018.
 */

public class ListItems {
    private String name;
    private String price;
    private float rating;
    private String availibility;



    private int orderNumber;
    private String url;
    Bitmap bmp;

    public ListItems(String name, String price, float rating, String availibility, Bitmap bmp) {
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.availibility = availibility;
        this.bmp = bmp;
       // this.url = url;
    }

    public ListItems(String name, String price, float rating, String availibility) {
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.availibility = availibility;
    }

    public ListItems(int orderNumber,String name, String price, int rating, String availibility, String url) {
        this.orderNumber = orderNumber;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.availibility = availibility;
        this.url = url;
    }


    public String getAvailibility(){
        return availibility;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public float getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public Bitmap getBmp() {
        return bmp;
    }
}

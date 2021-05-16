package com.example.paindiaryapp.Retrofit;

import com.google.gson.annotations.SerializedName;

public class WeatherData {

    @SerializedName("name")
    private String name;

    public String getName ( ) {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    @SerializedName("main")
    private MainTemp main;


    public MainTemp getMain() {
        return main;
    }

    public void setMain(MainTemp main) {
        this.main = main;
    }
}

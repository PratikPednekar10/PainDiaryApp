package com.example.paindiaryapp.Retrofit;

import com.google.gson.annotations.SerializedName;

public class WeatherData {

    @SerializedName("main")
    private MainTemp main;


    public MainTemp getMain() {
        return main;
    }

    public void setMain(MainTemp main) {
        this.main = main;
    }
}

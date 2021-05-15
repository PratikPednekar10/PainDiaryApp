package com.example.paindiaryapp.Retrofit;

import com.google.gson.annotations.SerializedName;

public class MainTemp {

    @SerializedName("name")
    String name;

    @SerializedName("temp")
    String temp;

    @SerializedName("humidity")
    String humidity;

    @SerializedName("pressure")
    String pressure;


    public String getName() {
        return name;
    }

    public void setName(String temp) {
        this.name = name;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }
}


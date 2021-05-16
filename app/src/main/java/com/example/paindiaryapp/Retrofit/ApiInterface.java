package com.example.paindiaryapp.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("weather?q=Melbourne,Vic,Australia&units=metric&appid=42c97d3258880237264b6e6a5cc57e1e")
    Call < WeatherData > getweatherData( );
}

package com.example.paindiaryapp.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("weather?q=melbourne&appid=42c97d3258880237264b6e6a5cc57e1e")
    Call < WeatherData > getweatherData( @Query  ( "q" ) String name);
}

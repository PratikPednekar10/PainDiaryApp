package com.example.paindiaryapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paindiaryapp.R;
import com.example.paindiaryapp.Retrofit.Api;
import com.example.paindiaryapp.Retrofit.ApiInterface;
import com.example.paindiaryapp.Retrofit.WeatherData;
import com.example.paindiaryapp.databinding.FragmentHomeBinding;
import com.example.paindiaryapp.databinding.FragmentLogoutBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;


    public HomeFragment ( ) {
        // Required empty public constructor
    }



    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
    }

    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState ) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        //getWeatherData ( binding.tvCity.getText().toString().trim() );

        getWeatherData (  );

       return view;
    }

    private void getWeatherData(){



        ApiInterface apiInterface = Api.getClient ( ).create ( ApiInterface.class );

        Call < WeatherData > call = apiInterface.getweatherData (  );

        call.enqueue ( new Callback < WeatherData > ( ) {
            @Override
            public void onResponse ( Call < WeatherData > call, Response < WeatherData > response ) {



                try {

                    binding.tvCity.setText ( response.body ().getName () );
                    binding.tvTemp.setText(response.body().getMain().getTemp() + " °C");
                    binding.tvHumidity.setText("Humidity " + " " + response.body().getMain().getHumidity ()+ "%");
                    binding.tvPressure.setText("Pressure " + " " + response.body().getMain().getPressure ()+"hPa");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure ( Call < WeatherData > call, Throwable t ) {
                Log.d("test", "error");
            }
        } );

    }
}
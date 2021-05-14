package com.example.paindiaryapp.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.paindiaryapp.Loginsignup.LoginActivity;
import com.example.paindiaryapp.R;
import com.example.paindiaryapp.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding binding;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );

        binding = ActivitySplashBinding.inflate ( getLayoutInflater ( ) );

        View view = binding.getRoot ( );


        setContentView ( view );

        //setContentView ( R.layout.activity_splash );

        binding.logoImgS.animate ().translationY ( -1600 ).setDuration ( 1000 ).setStartDelay ( 4000 );
        binding.img.animate ().translationY ( 1400 ).setDuration ( 1000 ).setStartDelay ( 4000 );
        binding.namelogo.animate ().translationY ( 1400 ).setDuration ( 1000 ).setStartDelay ( 4000 );
        binding.lottie.animate ().translationY ( 1500 ).setDuration ( 1000 ).setStartDelay ( 4000 );

        new Thread ( new Runnable ( ) {
            @Override
            public void run ( ) {
                try {
                    Thread.sleep ( 3800 );

                }catch (InterruptedException e){
                    e.printStackTrace ();
                }
                Intent intent = new Intent ( getApplicationContext (), LoginActivity.class );
                startActivity ( intent );
                finish();

            }

        } ).start ();


    }
}
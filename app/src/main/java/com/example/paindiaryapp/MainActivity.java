package com.example.paindiaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.paindiaryapp.Loginsignup.LoginActivity;
import com.example.paindiaryapp.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;


    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        binding = ActivityMainBinding.inflate ( getLayoutInflater ( ) );

        View view = binding.getRoot ( );

        setContentView ( view );
        //setContentView ( R.layout.activity_main );


        binding.btnLogout.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                FirebaseAuth.getInstance ().signOut ();
                startActivity ( new Intent ( getApplicationContext (), LoginActivity.class ) );
                finish ();
            }
        } );

    }
/*
    public void logout ( View view ) {
        FirebaseAuth.getInstance ( ).signOut ( ); //logout user
        startActivity ( new Intent ( getApplicationContext ( ), LoginActivity.class ) );
        finish ( );
 */



}

package com.example.paindiaryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import androidx.appcompat.widget.Toolbar;

import com.example.paindiaryapp.Fragments.DailyRecordFragment;
import com.example.paindiaryapp.Fragments.HomeFragment;
import com.example.paindiaryapp.Fragments.LogoutFragment;
import com.example.paindiaryapp.Fragments.MapsFragment;
import com.example.paindiaryapp.Fragments.PainDataEntryFragment;
import com.example.paindiaryapp.Fragments.ReportsFragment;
import com.example.paindiaryapp.Loginsignup.LoginActivity;
import com.example.paindiaryapp.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ActivityMainBinding binding;

    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        binding = ActivityMainBinding.inflate ( getLayoutInflater ( ) );

        View view = binding.getRoot ( );

        setContentView ( view );
        //setContentView ( R.layout.activity_main );

        drawerLayout = findViewById ( R.id.navigation_view );
        Toolbar toolbar = findViewById ( R.id.toolbar );
        NavigationView navigationView = findViewById ( R.id.navigation_view1 );
        navigationView.setNavigationItemSelectedListener ( this );
        getSupportFragmentManager ().beginTransaction ().replace ( R.id.Fragment_container, new HomeFragment () );


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle ( this, drawerLayout, toolbar, R.string.navigation_draw_open, R.string.navigation_draw_close );

        drawerLayout.addDrawerListener ( toggle );
        toggle.syncState ( );

        if ( savedInstanceState == null )
        {
            getSupportFragmentManager ().beginTransaction ().replace ( R.id.Fragment_container, new HomeFragment () ).commit ();
            navigationView.setCheckedItem ( R.id.home );
        }


    }

    @Override
    public boolean onNavigationItemSelected ( @NonNull MenuItem item ) {

        switch(item.getItemId ())
        {
            case R.id.home:
                getSupportFragmentManager ().beginTransaction ().replace ( R.id.Fragment_container, new HomeFragment () ).commit ();
                break;
            case R.id.paindataentry:
                getSupportFragmentManager ().beginTransaction ().replace ( R.id.Fragment_container, new PainDataEntryFragment () ).commit ();
                break;
            case R.id.dailyrecord:
                getSupportFragmentManager ().beginTransaction ().replace ( R.id.Fragment_container, new DailyRecordFragment () ).commit ();
                break;
            case R.id.reports:
                getSupportFragmentManager ().beginTransaction ().replace ( R.id.Fragment_container, new ReportsFragment () ).commit ();
                break;
            case R.id.maps:
                getSupportFragmentManager ().beginTransaction ().replace ( R.id.Fragment_container, new MapsFragment () ).commit ();
                break;
            case R.id.logout:
                getSupportFragmentManager ().beginTransaction ().replace ( R.id.Fragment_container, new LogoutFragment () ).commit ();
                break;
        }

        drawerLayout.closeDrawer ( GravityCompat.START );




        return true;
    }

    @Override
    public void onBackPressed ( ) {

        if ( drawerLayout.isDrawerOpen ( GravityCompat.START ) ) {
            drawerLayout.closeDrawer ( GravityCompat.START );
        }
        else
        {
            super.onBackPressed ();
        }
    }
}

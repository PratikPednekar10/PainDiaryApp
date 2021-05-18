package com.example.paindiaryapp.Fragments;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.paindiaryapp.R;
import com.example.paindiaryapp.databinding.FragmentMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


public class MapsFragment extends Fragment {
    private FragmentMapsBinding binding;


    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
    }

    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState ) {


        binding = FragmentMapsBinding.inflate ( inflater, container, false );
        View view = binding.getRoot ( );

        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager ( ).findFragmentById ( R.id.google_map );



        //Async Map
        supportMapFragment.getMapAsync ( new OnMapReadyCallback ( ) {
            @Override
            public void onMapReady ( @NonNull GoogleMap googleMap ) {

                binding.searchLoc.setOnQueryTextListener ( new SearchView.OnQueryTextListener ( ) {
                    @Override
                    public boolean onQueryTextSubmit ( String query ) {
                        String locat = binding.searchLoc.getQuery ().toString ();
                        List < Address > addressList = null;

                        if ( locat != null || !locat.equals ( "" ) ){
                            Geocoder geocoder = new Geocoder (getContext () );
                            try {
                                addressList = geocoder.getFromLocationName ( locat,1 );
                            } catch (IOException e) {
                                e.printStackTrace ( );
                            }
                            Address address;
                            address = addressList.get ( 0 );
                            LatLng latLng = new LatLng ( address.getLatitude (),address.getLongitude () );
                            googleMap.addMarker ( new MarkerOptions ().position ( latLng ).title ( locat ) );
                            googleMap.animateCamera ( CameraUpdateFactory.newLatLngZoom ( latLng,10 ) );
                        }

                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange ( String newText ) {
                        return false;
                    }
                } );
            }
        } );
        return view;
    }

}














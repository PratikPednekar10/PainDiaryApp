package com.example.paindiaryapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paindiaryapp.R;
import com.example.paindiaryapp.databinding.FragmentPainDataEntryBinding;
import com.example.paindiaryapp.databinding.FragmentReportsBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ReportsFragment extends Fragment {
    private FragmentReportsBinding binding;

    DatabaseReference painDbRef;


    public ReportsFragment ( ) {
        // Required empty public constructor
    }



    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );

    }

    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState ) {

        binding = FragmentReportsBinding.inflate ( inflater, container, false );
        View view = binding.getRoot ( );



        return view;
    }
}
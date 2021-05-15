package com.example.paindiaryapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.example.paindiaryapp.R;
import com.example.paindiaryapp.databinding.FragmentHomeBinding;
import com.example.paindiaryapp.databinding.FragmentPainDataEntryBinding;


public class PainDataEntryFragment extends Fragment {
    private FragmentPainDataEntryBinding binding;



    public PainDataEntryFragment ( ) {
        // Required empty public constructor
    }




    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );

    }

    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState ) {
        binding = FragmentPainDataEntryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        return view;
           }
}
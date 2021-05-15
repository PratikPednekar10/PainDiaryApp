package com.example.paindiaryapp.Fragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.paindiaryapp.R;
import com.example.paindiaryapp.databinding.FragmentHomeBinding;
import com.example.paindiaryapp.databinding.FragmentPainDataEntryBinding;

import java.util.Calendar;

import static androidx.core.content.ContextCompat.getSystemService;


public class PainDataEntryFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private FragmentPainDataEntryBinding binding;

    RadioButton rb;



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



        binding.seekBar.setOnSeekBarChangeListener ( new SeekBar.OnSeekBarChangeListener ( ) {
            @Override
            public void onProgressChanged ( SeekBar seekBar, int progress, boolean fromUser ) {
                binding.tvRating.setText ( "Rating " + String.valueOf ( progress ) );
            }

            @Override
            public void onStartTrackingTouch ( SeekBar seekBar ) {

            }

            @Override
            public void onStopTrackingTouch ( SeekBar seekBar ) {

            }
        } );

        binding.bSpinner.setOnItemSelectedListener ( this );

        binding.radioGroup.setOnCheckedChangeListener ( new RadioGroup.OnCheckedChangeListener ( ) {
            @Override
            public void onCheckedChanged ( RadioGroup group, int checkedId ) {
                switch (checkedId){
                    case R.id.radioButton1:
                        Toast.makeText ( getContext (),"You selected very low",Toast.LENGTH_SHORT ).show ();
                        break;
                    case R.id.radioButton2:
                        Toast.makeText ( getContext (),"You selected low",Toast.LENGTH_SHORT ).show ();
                        break;
                    case R.id.radioButton3:
                        Toast.makeText ( getContext (),"You selected average",Toast.LENGTH_SHORT ).show ();
                        break;
                    case R.id.radioButton4:
                        Toast.makeText ( getContext (),"You selected good",Toast.LENGTH_SHORT ).show ();
                        break;
                    case R.id.radioButton5:
                        Toast.makeText ( getContext (),"You selected very good",Toast.LENGTH_SHORT ).show ();
                        break;

                }
            }
        } );


        return view;
           }



    @Override
    public void onItemSelected ( AdapterView < ? > parent, View view, int position, long id ) {
        Toast.makeText ( getContext (), parent.getSelectedItem().toString(), Toast.LENGTH_SHORT ).show ();
    }

    @Override
    public void onNothingSelected ( AdapterView < ? > parent ) {

    }
}
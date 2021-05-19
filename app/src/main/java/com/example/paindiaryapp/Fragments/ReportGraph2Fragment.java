package com.example.paindiaryapp.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.paindiaryapp.databinding.FragmentReportGraph2Binding;
import com.example.paindiaryapp.entity.DailyPainRecord;
import com.example.paindiaryapp.viewmodel.DailyPainRecordViewModel;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


public class ReportGraph2Fragment extends Fragment {
    private FragmentReportGraph2Binding binding;

    private DailyPainRecordViewModel dailyPainRecordViewModel;


    public ReportGraph2Fragment ( ) {
        // Required empty public constructor
    }


    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
    }

    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState ) {
        binding = FragmentReportGraph2Binding.inflate ( inflater, container, false );
        View view = binding.getRoot ( );

        List < PieEntry > pieStepsEntries = new ArrayList <> ( );

        dailyPainRecordViewModel = new
                ViewModelProvider ( requireActivity ( ) ).get ( DailyPainRecordViewModel.class );



        dailyPainRecordViewModel.getCurrentDayRecord ( ).observe (
                getActivity ( ), new Observer < DailyPainRecord > ( ) {
                    @Override
                    public void onChanged ( DailyPainRecord dailyPainRecord ) {
                        if ( dailyPainRecord != null ) {

                            pieStepsEntries.add ( new PieEntry ( dailyPainRecord.dailySteps, "Steps completed today" ) );
                            pieStepsEntries.add ( new PieEntry ( dailyPainRecord.goalSteps - dailyPainRecord.dailySteps, "Steps remaining for today" ) );

                            ArrayList < Integer > colors = new ArrayList <> ( );
                            for (int color : ColorTemplate.MATERIAL_COLORS) {
                                colors.add ( color );
                            }

                            for (int color : ColorTemplate.COLORFUL_COLORS) {
                                colors.add ( color );
                            }

                            PieDataSet barGraphDataSet = new PieDataSet ( pieStepsEntries, "Measure Steps" );
                            barGraphDataSet.setColors ( colors );
                            PieData dataset = new PieData ( barGraphDataSet );
                            dataset.setDrawValues ( true );
                            dataset.setValueFormatter ( new PercentFormatter ( binding.pieChartsteps ) );
                            dataset.setValueTextSize ( 20f );
                            dataset.setValueTextColor ( Color.MAGENTA );

                            binding.pieChartsteps.setData ( dataset );
                            Description description = new Description ( );
                            description.setText ( "Steps taken Pie Chart" );
                            binding.pieChartsteps.setDescription ( description );
                            binding.pieChartsteps.invalidate ( );

                        }
                    }
                } );


        return view;
    }
}
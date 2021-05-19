package com.example.paindiaryapp.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.paindiaryapp.R;
import com.example.paindiaryapp.dao.DailyPainRecordDAO;
import com.example.paindiaryapp.databinding.FragmentReportGraph1Binding;
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


public class ReportGraph1Fragment extends Fragment {
    private FragmentReportGraph1Binding binding;

    private DailyPainRecordViewModel dailyPainRecordViewModel;



    public ReportGraph1Fragment ( ) {
        // Required empty public constructor
    }


    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );

    }

    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState ) {

        binding = FragmentReportGraph1Binding.inflate ( inflater, container, false );
        View view = binding.getRoot ( );


        List < PieEntry > painLocationpieEntries = new ArrayList <> ();
        //List<String> locationXAxis = new ArrayList<>();

        dailyPainRecordViewModel = new
                ViewModelProvider (requireActivity()).get(DailyPainRecordViewModel.class);

        dailyPainRecordViewModel.getPainLocationCount().observe(getViewLifecycleOwner(), new
                Observer <List< DailyPainRecordDAO.PaintLocCount>> () {
                    @Override
                    public void onChanged( List<DailyPainRecordDAO.PaintLocCount> painRecordList) {
                        if(painRecordList.size() > 0){
                            //int i = 0;
                            for (DailyPainRecordDAO.PaintLocCount temp : painRecordList) {
                                painLocationpieEntries.add(new PieEntry(temp.getCountItem(), temp.getNameItm()));

                            }

                            ArrayList<Integer> colors = new ArrayList<>();
                            for (int color: ColorTemplate.MATERIAL_COLORS) {
                                colors.add(color);
                            }

                            for (int color: ColorTemplate.COLORFUL_COLORS) {
                                colors.add(color);
                            }

                            PieDataSet barGraphDataSet = new PieDataSet(painLocationpieEntries, "Locations");
                            barGraphDataSet.setColors(colors);
                            PieData dataset = new PieData(barGraphDataSet);
                            dataset.setDrawValues(true);
                            dataset.setValueFormatter(new PercentFormatter (binding.pieChartpain));
                            dataset.setValueTextSize(20f);
                            dataset.setValueTextColor( Color.MAGENTA);

                            binding.pieChartpain.setData(dataset);

                            Description description = new Description();
                            description.setText("Pain Location Pie Chart");
                            binding.pieChartpain.setDescription(description);

                            binding.pieChartpain.invalidate();


                        }

                    }
                });



        return view;
    }
}
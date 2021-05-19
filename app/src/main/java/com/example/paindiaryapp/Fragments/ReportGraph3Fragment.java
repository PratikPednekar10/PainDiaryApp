package com.example.paindiaryapp.Fragments;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.paindiaryapp.DatePickerFromGraph;
import com.example.paindiaryapp.databinding.FragmentReportGraph3Binding;
import com.example.paindiaryapp.entity.DailyPainRecord;
import com.example.paindiaryapp.viewmodel.DailyPainRecordViewModel;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ReportGraph3Fragment extends Fragment {
    private FragmentReportGraph3Binding binding;


    DailyPainRecordViewModel dailyPainRecordViewModel;
    List < DailyPainRecord > dailyPainRecordListcList;
    String selectedOption = "Temperature";
    String wDate = "end";
    Date stDate = new Date ( );
    Date eDate = new Date ( );


    public ReportGraph3Fragment ( ) {
        // Required empty public constructor
    }


    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );

    }

    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState ) {
        binding = FragmentReportGraph3Binding.inflate ( inflater, container, false );
        View view = binding.getRoot ( );


        //data list
        ArrayList < Entry > values = new ArrayList <> ( );
        ArrayList < Entry > weather_values = new ArrayList <> ( );
        ArrayList < String > xAxisValues = new ArrayList <> ( );

        //spinner
        List < String > list_option = new ArrayList < String > ( );
        list_option.add ( "Temperature" );
        list_option.add ( "Humidity" );
        list_option.add ( "Pressure" );
        final ArrayAdapter < String > spAdapter = new
                ArrayAdapter < String > ( getContext ( ), android.R.layout.simple_spinner_item, list_option );
        binding.weatherSpinner.setAdapter ( spAdapter );


        dailyPainRecordViewModel = new
                ViewModelProvider ( requireActivity ( ) ).get ( DailyPainRecordViewModel.class );


        binding.weatherSpinner.setOnItemSelectedListener ( new AdapterView.OnItemSelectedListener ( ) {
            @Override
            public void onItemSelected ( AdapterView < ? > parent, View view, int position, long id ) {
                selectedOption = parent.getItemAtPosition ( position ).toString ( );

            }

            @Override
            public void onNothingSelected ( AdapterView < ? > parent ) {
            }
        } );

        binding.btnShowGraph.setOnClickListener ( new View.OnClickListener ( ) {
            public void onClick ( View v ) {
                binding.lineGraph.clear ( );
                values.clear ( );
                weather_values.clear ( );
                xAxisValues.clear ( );
                dailyPainRecordViewModel.getDailyPainRecordRange ( stDate, eDate ).observe ( getViewLifecycleOwner ( ), new
                        Observer < List < DailyPainRecord > > ( ) {
                            @Override
                            public void onChanged ( List < DailyPainRecord > painRecordList ) {
                                if ( painRecordList.size ( ) > 0 ) {
                                    int i = 1;
                                    for (DailyPainRecord temp : painRecordList) {
                                        values.add ( new Entry ( i, temp.getPainIntensity ( ) ) );
                                        if ( selectedOption == "Temperature" )
                                            weather_values.add ( new Entry ( i, (float) temp.getWeatherTemperature ( ) ) );
                                        else if ( selectedOption == "Humidity" )
                                            weather_values.add ( new Entry ( i, (float) temp.getWeatherHumidity ( ) ) );
                                        else if ( selectedOption == "Pressure" )
                                            weather_values.add ( new Entry ( i, (float) temp.getWeatherPressure ( ) ) );
                                        xAxisValues.add ( new SimpleDateFormat ( "dd/MM/yyyy" ).format ( temp.getEntryDate ( ) ).toString ( ) );
                                        i++;
                                    }

                                    //line 1
                                    LineDataSet draw1;


                                    draw1 = new LineDataSet ( values, "Level of Pain" );
                                    draw1.setDrawIcons ( false );
                                    draw1.setColor ( Color.MAGENTA );
                                    draw1.setCircleHoleColor ( Color.MAGENTA );
                                    draw1.setCircleColor ( Color.GRAY );
                                    draw1.setValueTextColor ( Color.MAGENTA );
                                    draw1.setLineWidth ( 1f );
                                    draw1.setCircleRadius ( 3f );
                                    draw1.setDrawCircleHole ( true );
                                    draw1.setValueTextSize ( 10f );
                                    draw1.setFormLineWidth ( 1f );
                                    draw1.setFormSize ( 10.f );

                                    //line 2
                                    LineDataSet draw2;
                                    binding.lineGraph.getXAxis ( ).setValueFormatter ( new
                                            com.github.mikephil.charting.formatter.IndexAxisValueFormatter ( xAxisValues ) );
                                    binding.lineGraph.getXAxis ( ).setLabelRotationAngle ( 90 );
                                    draw2 = new LineDataSet ( weather_values, "Weather Factors" );
                                    draw2.setDrawIcons ( false );
                                    draw2.setColor ( Color.BLUE );
                                    draw2.setCircleColor ( Color.GRAY );
                                    draw2.setCircleHoleColor ( Color.BLUE );
                                    draw2.setLineWidth ( 1f );
                                    draw2.setCircleRadius ( 3f );
                                    draw2.setDrawCircleHole ( true );
                                    draw2.setValueTextSize ( 10f );
                                    draw2.setValueTextColor ( Color.BLUE );
                                    draw2.setFormLineWidth ( 1f );
                                    draw2.setFormSize ( 10.f );

                                    ArrayList < ILineDataSet > dataSets = new ArrayList <> ( );
                                    dataSets.add ( draw1 );
                                    dataSets.add ( draw2 );
                                    LineData data = new LineData ( dataSets );
                                    binding.lineGraph.setData ( data );

                                    Description description = new Description ( );
                                    description.setText ( "Pain and Weather line Graph" );
                                    binding.lineGraph.setDescription ( description );

                                    binding.lineGraph.invalidate ( );
                                    // }

                                }

                            }
                        } );

            }
        } );

        //date picker

        binding.btnStartDate.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                wDate = "start";
                DatePickerFromGraph dpfg = new DatePickerFromGraph ( ).newInstance ( );
                dpfg.setCallBack ( onCurrentDate );
                dpfg.show ( getFragmentManager ( ).beginTransaction ( ), "DatePickerFragment" );
            }
        } );

        binding.btnEndDate.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                wDate = "end";
                DatePickerFromGraph dpfg = new DatePickerFromGraph ( ).newInstance ( );
                dpfg.setCallBack ( onCurrentDate );
                dpfg.show ( getFragmentManager ( ).beginTransaction ( ), "DatePickerFragment" );
            }
        } );

        //correlate
        binding.btnCorr.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                if ( values.size ( ) > 0 )
                    binding.tvCorrResult.setText ( testCorrelation ( values, weather_values ) );
            }

        } );

        return view;
    }

    private String testCorrelation ( ArrayList < Entry > values, ArrayList < Entry > weather_values ) {

        double data[][] = new double[ values.size ( ) ][ 2 ];
        for (int i = 0; i < values.size ( ); i++) {
            data[ i ][ 0 ] = values.get ( i ).getY ( );
            data[ i ][ 1 ] = weather_values.get ( i ).getY ( );
        }

        RealMatrix m = MatrixUtils.createRealMatrix ( data );

        for (int i = 0; i < m.getColumnDimension ( ); i++)
            for (int j = 0; j < m.getColumnDimension ( ); j++) {
                PearsonsCorrelation pcorr = new PearsonsCorrelation ( );
                double cor = pcorr.correlation ( m.getColumn ( i ), m.getColumn ( j ) );
                System.out.println ( i + "," + j + "=[" + String.format ( ".%2f", cor ) + "," + "]" );
            }
        PearsonsCorrelation pcorr = new PearsonsCorrelation ( m );
        RealMatrix corM = pcorr.getCorrelationMatrix ( );

        RealMatrix pM = pcorr.getCorrelationPValues ( );
        return ( "p value:" + pM.getEntry ( 0, 1 ) + "\n" + "Correlation: " + corM.getEntry ( 0, 1 ) );

    }

    private DatePickerDialog.OnDateSetListener onCurrentDate = new DatePickerDialog.OnDateSetListener ( ) {
        @Override
        public void onDateSet ( final DatePicker view, final int year, final int month, final int dayOfMonth ) {
            Calendar c = Calendar.getInstance ( );
            c.set ( Calendar.YEAR, year );
            c.set ( Calendar.MONTH, month );
            c.set ( Calendar.DAY_OF_MONTH, dayOfMonth );
            String currentDate = DateFormat.getDateInstance ( DateFormat.FULL ).format ( c.getTime ( ) );

            if ( wDate == "start" ) {
                binding.tvStartDate.setText ( currentDate );
                stDate = c.getTime ( );
            } else if ( wDate == "end" ) {
                binding.tvEndDate.setText ( currentDate );
                eDate = c.getTime ( );
            }
        }
    };

}
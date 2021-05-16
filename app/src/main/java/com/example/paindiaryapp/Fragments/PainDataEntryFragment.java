package com.example.paindiaryapp.Fragments;

import android.app.AlarmManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.paindiaryapp.R;
import com.example.paindiaryapp.databinding.FragmentPainDataEntryBinding;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static androidx.core.content.ContextCompat.getSystemService;


public class PainDataEntryFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private FragmentPainDataEntryBinding binding;

    int hour,minute;

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
        binding = FragmentPainDataEntryBinding.inflate ( inflater, container, false );
        View view = binding.getRoot ( );

        binding.btnScheduleN.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                //Initialize time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog (
                        getContext (),
                        new TimePickerDialog.OnTimeSetListener ( ) {
                            @Override
                            public void onTimeSet ( TimePicker view, int hourOfDay, int minute ) {
                              //Initialize hour and minute
                              hour = hourOfDay;
                              minute = minute;
                              //Initialize Calendar
                                Calendar calendar = Calendar.getInstance ();

                                Calendar c = Calendar.getInstance ( );
                                c.set ( Calendar.HOUR_OF_DAY, hourOfDay );
                                c.set ( Calendar.MINUTE, minute );
                                c.set ( Calendar.SECOND, 0 );


                                //set hour and minute
                               // calendar.set ( 0,0,0,hour,minute );
                                //set selected time on text view
                               // binding.tvAlarmAlert.setText ( DateFormat.getTimeInstance (DateFormat.SHORT).format (c ) );
                                try {
                                    binding.tvAlarmAlert.setText ( new SimpleDateFormat("hh:mm aa").format (
                                            new SimpleDateFormat ( "HH:mm" ).parse ( hour+ ":"+minute ) ) );
                                } catch (ParseException e) {
                                    e.printStackTrace ( );
                                }
                            }
                        },12,0,false
                );
                //Displayed previous selected time
                timePickerDialog.updateTime ( hour,minute );

                //show dialog
                timePickerDialog.show ();
            }
        } );



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




/*
    public void onTimeSet ( TimePicker view, int hourOfDay, int minute ) {
        Calendar c = Calendar.getInstance ( );
        c.set ( Calendar.HOUR_OF_DAY, hourOfDay );
        c.set ( Calendar.MINUTE, minute );
        c.set ( Calendar.SECOND, 0 );

        updateTimeText ( c );
        startAlarm ( c );
    }



    private void updateTimeText ( Calendar c ) {
        String timeText = "Alarm set for: ";
        timeText += DateFormat.getTimeInstance (DateFormat.SHORT).format ( c );

        binding.tvAlarmAlert.setText ( timeText );
    }

    private void startAlarm(Calendar c){
        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService( Context.ALARM_SERVICE);
    }
*/

    @Override
    public void onItemSelected ( AdapterView < ? > parent, View view, int position, long id ) {
        Toast.makeText ( getContext ( ), parent.getSelectedItem ( ).toString ( ), Toast.LENGTH_SHORT ).show ( );
    }

    @Override
    public void onNothingSelected ( AdapterView < ? > parent ) {

    }
}
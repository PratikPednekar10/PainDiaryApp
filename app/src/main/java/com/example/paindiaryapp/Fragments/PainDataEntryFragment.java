package com.example.paindiaryapp.Fragments;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.paindiaryapp.R;
import com.example.paindiaryapp.databinding.FragmentPainDataEntryBinding;
import com.example.paindiaryapp.entity.DailyPainRecord;
import com.example.paindiaryapp.viewmodel.DailyPainRecordViewModel;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.firebase.auth.FirebaseAuth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class PainDataEntryFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private FragmentPainDataEntryBinding binding;

    private DailyPainRecordViewModel dailyPainRecordViewModel;

    int hour, minute, p_intensity;
    int set_goal;
    String mood, user_email, pain_location;


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



        dailyPainRecordViewModel =
                ViewModelProvider.AndroidViewModelFactory.getInstance ( getActivity ( ).getApplication ( ) ).create
                        ( DailyPainRecordViewModel.class );

        dailyPainRecordViewModel.getAllDailyPainRecord ().observe(getActivity(), new
                Observer < List < DailyPainRecord > > () {
                    @Override
                    public void onChanged(@Nullable final List<DailyPainRecord> painRecords) {
                        String allPainRecords = "";
                        for (DailyPainRecord temp : painRecords) {

                            String painDetails = (temp.uid + " " + temp.userEmail + " " + temp.painIntensity + " " + temp.painLocation);
                            allPainRecords = allPainRecords +
                                    System.getProperty("line.separator") + painDetails;


                        }

                    }
                });

        SharedPreferences sharedPref = requireActivity ( ).
                getSharedPreferences ( "WeatherReporting", Context.MODE_PRIVATE );
        String temperature = sharedPref.getString ( "temperature", null );
        String humidity = sharedPref.getString ( "humidity", null );
        String pressure = sharedPref.getString ( "pressure", null );

        binding.btnScheduleN.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                //Initialize time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog (
                        getContext ( ),
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

        binding.bSpinner.setOnItemSelectedListener ( this );


        binding.btnSave.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {

                user_email = FirebaseAuth.getInstance ( ).getCurrentUser ( ).getEmail ( ).toString ( );
                //get values from form data entry

                //get mood level from radio button group
                int radioId = binding.radioGroup.getCheckedRadioButtonId ( );
                MaterialRadioButton radioButton = binding.radioGroup.findViewById ( radioId );
                mood = radioButton.getText ( ).toString ( );

                //get value for pain intensity from slider
                p_intensity = (int) binding.painIntensitySilder.getValue ( );


                //get the goals steps
                set_goal = Integer.parseInt ( binding.etSetGoals.getEditText ( ).getText ( ).toString ( ) );

                //get pain_location
                pain_location = binding.bSpinner.getSelectedItem ( ).toString ( );

                DailyPainRecord dailyPainRecord = new DailyPainRecord(user_email,p_intensity,pain_location,mood,set_goal,new Date ( ),Double.parseDouble ( temperature ),Double.parseDouble ( humidity ),Double.parseDouble ( pressure ) );
                dailyPainRecordViewModel.insert(dailyPainRecord);

            }

        } );


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
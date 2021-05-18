package com.example.paindiaryapp.Fragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.ListenableWorker;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.paindiaryapp.AlertReceiver;
import com.example.paindiaryapp.PainRecordFirebase;
import com.example.paindiaryapp.R;
import com.example.paindiaryapp.databinding.FragmentPainDataEntryBinding;
import com.example.paindiaryapp.entity.DailyPainRecord;
import com.example.paindiaryapp.viewmodel.DailyPainRecordViewModel;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


public class PainDataEntryFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private FragmentPainDataEntryBinding binding;

    private DailyPainRecordViewModel dailyPainRecordViewModel;

    DatabaseReference painRecordDbRef;

    private static final String TAG = "PainDataEntryFragment";


    int hour, minute, p_intensity;
    int set_goal, daily_step;
    String mood, user_email, pain_location;
    int userId;
    Calendar c = Calendar.getInstance ( );

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


        painRecordDbRef = FirebaseDatabase.getInstance ( ).getReference ( ).child ( "PainRecord" );

        dailyPainRecordViewModel =
                ViewModelProvider.AndroidViewModelFactory.getInstance ( getActivity ( ).getApplication ( ) ).create
                        ( DailyPainRecordViewModel.class );

        dailyPainRecordViewModel.getCurrentDayRecord ( ).observe (
                getActivity ( ), new Observer < DailyPainRecord > ( ) {
                    @Override
                    public void onChanged ( DailyPainRecord painRecord ) {

                        if ( painRecord != null ) {
                            userId = painRecord.uid;
                            binding.painIntensitySilder.setValue ( (float) painRecord.painIntensity );

                            for (int i = 0; i < binding.radioGroup.getChildCount ( ); i++) {
                                RadioButton rbtn = (RadioButton) binding.radioGroup.getChildAt ( i );
                                if ( rbtn.getText ( ).equals ( painRecord.moodLevel ) ) {
                                    binding.radioGroup.check ( rbtn.getId ( ) );
                                }
                            }
                            binding.etSetGoals.getEditText ( ).setText ( String.valueOf ( painRecord.goalSteps ) );
                            binding.etSetGoalsToday.getEditText ( ).setText ( String.valueOf ( painRecord.dailySteps ) );

                            String compareValue = painRecord.painLocation;
                            ArrayAdapter < CharSequence > adapter = ArrayAdapter.createFromResource ( getContext ( ), R.array.Spinner_items, android.R.layout.simple_spinner_item );
                            adapter.setDropDownViewResource ( android.R.layout.simple_spinner_dropdown_item );
                            binding.bSpinner.setAdapter ( adapter );
                            if ( compareValue != null ) {
                                int spinnerPosition = adapter.getPosition ( compareValue );
                                binding.bSpinner.setSelection ( spinnerPosition );
                            }

                            binding.btnSave.setEnabled ( false );
                            binding.btnEdit.setEnabled ( true );
                        } else {
                            binding.btnSave.setEnabled ( true );
                            binding.btnEdit.setEnabled ( false );
                        }
                    }
                }
        );

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
                                Calendar calendar = Calendar.getInstance ( );


                                c.set ( Calendar.HOUR_OF_DAY, hourOfDay );
                                c.set ( Calendar.MINUTE, minute );
                                c.set ( Calendar.SECOND, 0 );


                                //set hour and minute
                                // calendar.set ( 0,0,0,hour,minute );
                                //set selected time on text view
                                // binding.tvAlarmAlert.setText ( DateFormat.getTimeInstance (DateFormat.SHORT).format (c ) );
                                try {
                                    binding.tvAlarmAlert.setText ( new SimpleDateFormat ( "hh:mm aa" ).format (
                                            new SimpleDateFormat ( "HH:mm" ).parse ( hour + ":" + minute ) ) );
                                } catch (ParseException e) {
                                    e.printStackTrace ( );
                                }
                                startAlarm ( c );

                            }

                        }, 12, 0, false

                );
                //Displayed previous selected time
                timePickerDialog.updateTime ( hour, minute );

                //show dialog
                timePickerDialog.show ( );

                startAlarm ( c );

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

                //get the daily goals steps
                daily_step = Integer.parseInt ( binding.etSetGoalsToday.getEditText ( ).getText ( ).toString ( ) );

                //get pain_location
                pain_location = binding.bSpinner.getSelectedItem ( ).toString ( );


                DailyPainRecord dailyPainRecord = new DailyPainRecord ( user_email, p_intensity, pain_location, mood, set_goal, new Date ( ), Double.parseDouble ( temperature ), Double.parseDouble ( humidity ), Double.parseDouble ( pressure ), daily_step );
                dailyPainRecordViewModel.insert ( dailyPainRecord );



                PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder (
                        MyPeriodicWork.class, 1, TimeUnit.DAYS )
                        .build ( );

                WorkManager.getInstance ( ).enqueue ( periodicWorkRequest );

            }


        } );


        binding.btnEdit.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {

                if ( userId != 0 ) {


                    if ( binding.etSetGoals.getEditText ( ).getText ( ).toString ( ).isEmpty ( ) ) {
                    } else if ( binding.etSetGoalsToday.getEditText ( ).getText ( ).toString ( ).isEmpty ( ) ) {
                    } else if ( binding.bSpinner.getSelectedItem ( ) == null ) {

                    } else {

                        user_email = FirebaseAuth.getInstance ( ).getCurrentUser ( ).getEmail ( ).toString ( );

                        //get value for pain intensity
                        int pain_intensity = (int) binding.painIntensitySilder.getValue ( );

                        //get mood level
                        int Id = binding.radioGroup.getCheckedRadioButtonId ( );
                        MaterialRadioButton rbtn = binding.radioGroup.findViewById ( Id );
                        String mood_level = rbtn.getText ( ).toString ( );

                        //get goal steps
                        int goal_steps = Integer.parseInt ( binding.etSetGoals.getEditText ( ).getText ( ).toString ( ) );
                        int daily_steps = Integer.parseInt ( binding.etSetGoalsToday.getEditText ( ).getText ( ).toString ( ) );

                        //get pain_location
                        pain_location = binding.bSpinner.getSelectedItem ( ).toString ( );


                        //update
                        if ( android.os.Build.VERSION.SDK_INT >=
                                android.os.Build.VERSION_CODES.N ) {
                            CompletableFuture < DailyPainRecord > painRecordCompletableFuture =
                                    dailyPainRecordViewModel.findByIDFuture ( userId );
                            painRecordCompletableFuture.thenApply ( painRecord -> {
                                if ( painRecord != null ) {

                                    painRecord.weatherTemperature = Double.parseDouble ( temperature );
                                    painRecord.weatherHumidity = Double.parseDouble ( humidity );
                                    painRecord.weatherPressure = Double.parseDouble ( pressure );
                                    painRecord.moodLevel = mood_level;
                                    painRecord.goalSteps = goal_steps;
                                    painRecord.dailySteps = daily_steps;
                                    painRecord.painIntensity = pain_intensity;
                                    painRecord.painLocation = pain_location;

                                    dailyPainRecordViewModel.update ( painRecord );

                                } else {
                                    //do nothing

                                }

                                return painRecord;
                            } );
                        }
                    }
                }
            }
        } );

        binding.radioGroup.setOnCheckedChangeListener ( new RadioGroup.OnCheckedChangeListener ( ) {
            @Override
            public void onCheckedChanged ( RadioGroup group, int checkedId ) {
                switch (checkedId) {
                    case R.id.radioButton1:
                        Toast.makeText ( getContext ( ), "You selected very low", Toast.LENGTH_SHORT ).show ( );
                        break;
                    case R.id.radioButton2:
                        Toast.makeText ( getContext ( ), "You selected low", Toast.LENGTH_SHORT ).show ( );
                        break;
                    case R.id.radioButton3:
                        Toast.makeText ( getContext ( ), "You selected average", Toast.LENGTH_SHORT ).show ( );
                        break;
                    case R.id.radioButton4:
                        Toast.makeText ( getContext ( ), "You selected good", Toast.LENGTH_SHORT ).show ( );
                        break;
                    case R.id.radioButton5:
                        Toast.makeText ( getContext ( ), "You selected very good", Toast.LENGTH_SHORT ).show ( );
                        break;

                }
            }
        } );





        return view;


    }

    public void insertPainRecordData ( ) {
        String email = FirebaseAuth.getInstance ( ).getCurrentUser ( ).getEmail ( ).toString ( );
        String moodF = mood;
        int pintensityF = p_intensity;
        int setgoalF = set_goal;
        int dailystepF = daily_step;
        String painLocationF = pain_location;


        PainRecordFirebase painRecordFirebase = new PainRecordFirebase ( email, moodF, pintensityF, setgoalF, dailystepF, painLocationF );

        painRecordDbRef.push ( ).setValue ( painRecordFirebase );
        Toast.makeText ( getContext ( ), "Data inserted!", Toast.LENGTH_SHORT ).show ( );
    }

    private void startAlarm ( Calendar c ) {
        AlarmManager alarmManager = (AlarmManager) getActivity ( ).getSystemService ( Context.ALARM_SERVICE );
        Intent intent = new Intent ( getActivity ( ), AlertReceiver.class );
        PendingIntent pendingIntent = PendingIntent.getBroadcast ( getContext ( ), 1, intent, 0 );

        if ( c.before ( Calendar.getInstance ( ) ) ) {
            c.add ( Calendar.DATE, 1 );
        }

        alarmManager.setExact ( AlarmManager.RTC_WAKEUP, c.getTimeInMillis ( ) - 2000, pendingIntent );


    }


    @Override
    public void onItemSelected ( AdapterView < ? > parent, View view, int position, long id ) {
        Toast.makeText ( getContext ( ), parent.getSelectedItem ( ).toString ( ), Toast.LENGTH_SHORT ).show ( );
    }

    @Override
    public void onNothingSelected ( AdapterView < ? > parent ) {

    }


    public class MyPeriodicWork extends Worker {

        private static final String TAG = "MyPeriodicWork";

        public MyPeriodicWork ( @NonNull Context context, @NonNull WorkerParameters workerParams ) {
            super ( context, workerParams );
        }

        @NonNull
        @Override
        public Result doWork ( ) {
            insertPainRecordData ();
            Log.e ( TAG, "Entry is updated." );
            return Result.success ( );
        }


    }
}


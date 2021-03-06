package com.example.paindiaryapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFromGraph extends DialogFragment {

    DatePickerDialog.OnDateSetListener onDateSet;

    public static DatePickerFromGraph newInstance ( ) {
        DatePickerFromGraph dpfgmain = new DatePickerFromGraph ( );
        return dpfgmain;
    }

    public DatePickerFromGraph ( ) {
    }

    @Nullable
    @Override
    public View onCreateView (
            @NonNull
            final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState ) {
           return super.onCreateView ( inflater, container, savedInstanceState );
    }

    @NonNull
    @Override
    public Dialog onCreateDialog ( @Nullable final Bundle savedInstanceState ) {
        Calendar c = Calendar.getInstance ( );
        int year = c.get ( Calendar.YEAR );
        int month = c.get ( Calendar.MONTH );
        int day = c.get ( Calendar.DAY_OF_MONTH );
        //return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getTargetFragment(), year, month, day);
        return new DatePickerDialog ( getActivity ( ), onDateSet, year, month, day );
    }

    public void setCallBack ( DatePickerDialog.OnDateSetListener onDate ) {
        onDateSet = onDate;
    }


}



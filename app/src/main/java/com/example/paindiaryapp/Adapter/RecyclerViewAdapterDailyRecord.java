package com.example.paindiaryapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paindiaryapp.R;
import com.example.paindiaryapp.entity.DailyPainRecord;

import java.text.SimpleDateFormat;
import java.util.List;


public class RecyclerViewAdapterDailyRecord extends RecyclerView.Adapter < RecyclerViewAdapterDailyRecord.DailyPainViewHolder > {

    List < DailyPainRecord > dailyPainRecordsList;

    public RecyclerViewAdapterDailyRecord ( List < DailyPainRecord > dpainRecList ) {
        this.dailyPainRecordsList = dpainRecList;
    }

    public void setPainRecordList ( List < DailyPainRecord > dpainRecords ) {
        this.dailyPainRecordsList = dpainRecords;
        notifyDataSetChanged ( );
    }

    @NonNull
    @Override
    public DailyPainViewHolder onCreateViewHolder ( @NonNull ViewGroup parent, int viewType ) {

        View view = LayoutInflater.from ( parent.getContext ( ) ).inflate ( R.layout.rows_records, parent, false );
        return new DailyPainViewHolder ( view );

    }

    @Override
    public int getItemCount ( ) {
        return dailyPainRecordsList.size ( );
    }

    public class DailyPainViewHolder extends RecyclerView.ViewHolder {
        TextView recyclev_date,recyclev_mood, recyclev_painLoc,recyclev_painIntensity,recyclev_stepsGoals;
        TextView recyclev_temp, recyclev_humidity, recyclev_pressure;

        public DailyPainViewHolder ( View itemView ) {
            super ( itemView );
            recyclev_date = itemView.findViewById ( R.id.cr_date );
            recyclev_mood = itemView.findViewById ( R.id.row_mood );
            recyclev_painLoc = itemView.findViewById ( R.id.row_painLoc );
            recyclev_painIntensity= itemView.findViewById ( R.id.row_painIntensity );
            recyclev_stepsGoals = itemView.findViewById ( R.id.row_stepGoals );

            recyclev_temp = itemView.findViewById ( R.id.row_temp );
            recyclev_humidity = itemView.findViewById ( R.id.row_humd );
            recyclev_pressure = itemView.findViewById ( R.id.row_pressure );
        }
    }

    @Override
    public void onBindViewHolder ( @NonNull DailyPainViewHolder holder, int position ) {
        DailyPainRecord dailyPainRecord = dailyPainRecordsList.get ( position );
        holder.recyclev_date.setText ( new SimpleDateFormat ( "dd/MM/yyyy" ).format ( dailyPainRecord.entryDate ).toString ( ) );
        holder.recyclev_mood.setText ( "Mood: " + dailyPainRecord.moodLevel );
        holder.recyclev_painLoc.setText ( "Location: " + dailyPainRecord.painLocation);
        holder.recyclev_painIntensity.setText ( "Intensity: " + String.valueOf ( dailyPainRecord.painIntensity) );
        holder.recyclev_stepsGoals.setText ( "Steps taken: " + String.valueOf ( dailyPainRecord.dailySteps ) );

        holder.recyclev_temp.setText ( "Temp: " + String.valueOf ( dailyPainRecord.weatherTemperature ) + " C" );
        holder.recyclev_humidity.setText ( "Humidity: " + String.valueOf ( dailyPainRecord.weatherHumidity) + " %" );
        holder.recyclev_pressure.setText ( "Pressure: " + String.valueOf ( dailyPainRecord.weatherPressure) + " hPa" );

    }


}

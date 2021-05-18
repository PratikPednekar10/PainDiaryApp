package com.example.paindiaryapp.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class DailyPainRecord {
    @PrimaryKey (autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "user_email")
    @NonNull
    public String userEmail;

    @ColumnInfo(name = "pain_intensity" )
    @NonNull
    public int painIntensity;


    @ColumnInfo(name = "pain_location" )
    @NonNull
    public String painLocation;

    @ColumnInfo(name = "mood_level" )
    @NonNull
    public String moodLevel;

    @ColumnInfo(name = "goals_steps" )
    @NonNull
    public int goalSteps;

    @ColumnInfo(name = "daily_steps" )
    @NonNull
    public int dailySteps;

    @ColumnInfo(name = "entry_date" )
    @NonNull
    public Date entryDate;

    @ColumnInfo(name = "weather_temperature" )
    @NonNull
    public double weatherTemperature;

    @ColumnInfo(name = "weather_humidity" )
    @NonNull
    public double weatherHumidity;

    @ColumnInfo(name = "weather_pressure" )
    @NonNull
    public double weatherPressure;


    public DailyPainRecord ( @NonNull String userEmail, int painIntensity, @NonNull String painLocation, @NonNull String moodLevel, int goalSteps, @NonNull Date entryDate, double weatherTemperature, double weatherHumidity, double weatherPressure ,int dailySteps) {
        this.userEmail = userEmail;
        this.painIntensity = painIntensity;
        this.painLocation = painLocation;
        this.moodLevel = moodLevel;
        this.goalSteps = goalSteps;
        this.entryDate = entryDate;
        this.weatherTemperature = weatherTemperature;
        this.weatherHumidity = weatherHumidity;
        this.weatherPressure = weatherPressure;
        this.dailySteps = dailySteps;
    }
}

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
    @NonNull
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(@NonNull String userEmail) {
        this.userEmail = userEmail;
    }

    public int getPainIntensity() {
        return painIntensity;
    }

    public void setPainIntensity(int painIntensity) {
        this.painIntensity = painIntensity;
    }

    @NonNull
    public String getPainLocation() {
        return painLocation;
    }

    public void setPainLocation(@NonNull String painLocation) {
        this.painLocation = painLocation;
    }

    @NonNull
    public String getMoodLevel() {
        return moodLevel;
    }

    public void setMoodLevel(@NonNull String moodLevel) {
        this.moodLevel = moodLevel;
    }

    public int getGoalSteps() {
        return goalSteps;
    }

    public void setGoalSteps(int stepsDay) {
        this.goalSteps = stepsDay;
    }

    public int getDailySteps() {
        return dailySteps;
    }

    public void setDailySteps(int dailyStepsCompleted) {
        this.dailySteps = dailyStepsCompleted;
    }

    @NonNull
    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(@NonNull Date entryDate) {
        this.entryDate = entryDate;
    }

    public double getWeatherTemperature() {
        return weatherTemperature;
    }

    public void setWeatherTemperature(double temperature) {
        this.weatherTemperature = temperature;
    }

    public double getWeatherHumidity() {
        return weatherHumidity;
    }

    public void setWeatherHumidity(double humidity) {
        this.weatherHumidity = humidity;
    }

    public double getWeatherPressure() {
        return weatherPressure;
    }

    public void setWeatherPressure(double pressure) {
        this.weatherPressure = pressure;
    }
}



package com.example.paindiaryapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.paindiaryapp.entity.DailyPainRecord;

import java.util.List;


public interface DailyPainRecordDAO {

    @Query("SELECT * FROM DailyPainRecord ORDER BY uid ASC ")
    LiveData< List < DailyPainRecord >> getAll();

    @Query("SELECT * FROM DailyPainRecord WHERE  uid = :dailyPainRecordID LIMIT 1")
    DailyPainRecord findByID(int dailyPainRecordID);

    @Insert
    void insert(DailyPainRecord DailyPainRecord);

    @Update
    void updateDailyPainReocrd(DailyPainRecord DailyPainRecord);

}

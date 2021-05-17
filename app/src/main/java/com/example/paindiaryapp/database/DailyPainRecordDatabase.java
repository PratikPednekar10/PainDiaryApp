package com.example.paindiaryapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.paindiaryapp.ConvertersData;
import com.example.paindiaryapp.dao.DailyPainRecordDAO;
import com.example.paindiaryapp.entity.DailyPainRecord;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {DailyPainRecord.class}, version = 1, exportSchema = false)
@TypeConverters({ConvertersData.class})
public abstract class DailyPainRecordDatabase extends RoomDatabase {



    public abstract DailyPainRecordDAO dailyPainRecordDAO();
    private static DailyPainRecordDatabase INSTANCE;
    //we create an ExecutorService with a fixed thread pool so we can later run database operations asynchronously on a background thread.
    private static final int NUMBER_OF_THREADS = 4;


    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    //A synchronized method in a multi threaded environment means that two threads are not allowed to access data at the same time
    public static synchronized DailyPainRecordDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    DailyPainRecordDatabase.class, "DailyPainRecordDatabase") .fallbackToDestructiveMigration() .build();
        }
        return INSTANCE; }
}

package com.example.paindiaryapp.repository;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.paindiaryapp.dao.DailyPainRecordDAO;
import com.example.paindiaryapp.database.DailyPainRecordDatabase;
import com.example.paindiaryapp.entity.DailyPainRecord;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class DailyPainRecordRepository {
    private DailyPainRecordDAO dailyPainRecordDAO;
    private LiveData < List < DailyPainRecord > > allDailyPainRecord;

    public DailyPainRecordRepository ( Application application ) {
        DailyPainRecordDatabase db = DailyPainRecordDatabase.getInstance ( application );
        dailyPainRecordDAO = db.dailyPainRecordDAO ( );
        allDailyPainRecord = dailyPainRecordDAO.getAll ( );
    }

    //Room executes this query on a separate thread
    public LiveData < List < DailyPainRecord > > getAllDailyPainRecord ( ) {
        return allDailyPainRecord;
    }

    public void insert ( final DailyPainRecord dailyPainRecord ) {
        DailyPainRecordDatabase.databaseWriteExecutor.execute ( new Runnable ( ) {
            @Override
            public void run ( ) {
                dailyPainRecordDAO.insert ( dailyPainRecord );
            }
        } );
    }

    public void updateDailyPainRecord ( final DailyPainRecord dailyPainRecord ) {
        DailyPainRecordDatabase.databaseWriteExecutor.execute ( new Runnable ( ) {
            @Override
            public void run ( ) {
                dailyPainRecordDAO.updateDailyPainReocrd ( dailyPainRecord );
            }
        } );
    }

    public LiveData<DailyPainRecord> getCurrentDayRecord() {
        return dailyPainRecordDAO.getCurrentDayRecord ();
    }

    public  LiveData<List<DailyPainRecordDAO.PaintLocCount>> getPainLocCount() {
        return dailyPainRecordDAO.getPainLocationC ( );
    }

    public LiveData<List<DailyPainRecord>> getDailyPainRecordRange( Date startDate, Date endDate){
        return dailyPainRecordDAO.getDailyPainRecordRange(startDate,endDate);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture < DailyPainRecord > findByIDFuture ( final int customerId ) {
        return CompletableFuture.supplyAsync ( new Supplier < DailyPainRecord > ( ) {
            @Override
            public DailyPainRecord get ( ) {
                return dailyPainRecordDAO.findByID ( customerId );
            }
        }, DailyPainRecordDatabase.databaseWriteExecutor );
    }
}


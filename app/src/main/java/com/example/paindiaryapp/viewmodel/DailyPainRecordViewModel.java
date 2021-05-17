package com.example.paindiaryapp.viewmodel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.paindiaryapp.entity.DailyPainRecord;
import com.example.paindiaryapp.repository.DailyPainRecordRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DailyPainRecordViewModel  extends AndroidViewModel {
    private DailyPainRecordRepository dRepository;
    private LiveData< List <DailyPainRecord> > allDailyPainRecord;

    public DailyPainRecordViewModel (Application application ) {
        super ( application );
        dRepository = new DailyPainRecordRepository ( application );
        allDailyPainRecord = dRepository.getAllDailyPainRecord ();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture <DailyPainRecord> findByIDFuture( final int dailyPainRecordId){ return dRepository.findByIDFuture(dailyPainRecordId);
    }
    public LiveData<List<DailyPainRecord>> getAllDailyPainRecord() {
        return allDailyPainRecord; }
    public  void insert ( DailyPainRecord dailyPainRecord ) { dRepository.insert(dailyPainRecord);
    }

    public void update(DailyPainRecord dailyPainRecord) { dRepository.updateDailyPainRecord (dailyPainRecord);
    }
}

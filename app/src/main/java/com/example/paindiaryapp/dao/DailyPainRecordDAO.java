package com.example.paindiaryapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.paindiaryapp.entity.DailyPainRecord;

import java.util.Date;
import java.util.List;

@Dao
public interface DailyPainRecordDAO {

    @Query("SELECT * FROM dailypainrecord ORDER BY uid ASC ")
    LiveData< List < DailyPainRecord >> getAll();

    @Query("SELECT * FROM dailypainrecord WHERE  uid = :dailyPainRecordID LIMIT 1")
    DailyPainRecord findByID(int dailyPainRecordID);

    @Query("SELECT * from dailypainrecord  where date(entry_date / 1000,'unixepoch') = date('now')")
    LiveData<DailyPainRecord> getCurrentDayRecord();

    @Query("SELECT pain_location AS nameItm, count(pain_location) AS countItm FROM dailypainrecord group by pain_location")
    LiveData<List<PaintLocCount>> getPainLocationC();

    @Query ( "SELECT * FROM dailypainrecord where entry_date BETWEEN :from AND :to" )
    LiveData<List<DailyPainRecord>> getDailyPainRecordRange( Date from, Date to);



    @Insert
    void insert(DailyPainRecord dailyPainRecord);

    @Update
    void updateDailyPainReocrd(DailyPainRecord dailyPainRecord);

    class PaintLocCount {
        String nameItm;
        Long countItm;

        public String getNameItm() {
            return nameItm;
        }

        public void setName(String name) {
            this.nameItm = name;
        }

        public Long getCountItem() {
            return countItm;
        }

        public void setCountItem(Long countItem) {
            this.countItm = countItem;
        }
    }

}

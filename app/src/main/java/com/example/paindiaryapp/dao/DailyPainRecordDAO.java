package com.example.paindiaryapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.paindiaryapp.entity.DailyPainRecord;

import java.util.List;

@Dao
public interface DailyPainRecordDAO {

    @Query("SELECT * FROM dailypainrecord ORDER BY uid ASC ")
    LiveData< List < DailyPainRecord >> getAll();

    @Query("SELECT * FROM dailypainrecord WHERE  uid = :dailyPainRecordID LIMIT 1")
    DailyPainRecord findByID(int dailyPainRecordID);

    @Query("SELECT * from dailypainrecord  where date(entry_date / 1000,'unixepoch') = date('now')")
    LiveData<DailyPainRecord> getCurrentDayRecord();

    @Query("SELECT pain_location AS nameItem, count(pain_location) AS countItem FROM dailypainrecord group by pain_location")
    LiveData<List<PaintLocCount>> getPainLocationC();



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


/*
@Query("SELECT pain_location AS nameItem, count(pain_location) AS countItem FROM painrecord group by pain_location")
    LiveData<List<CountedPainLocationItem>> getPainLocationCount();

	public class CountedPainLocationItem {
        String nameItem;
        Long countItem;

        public String getNameItem() {
            return nameItem;
        }

        public void setNameItem(String nameItem) {
            this.nameItem = nameItem;
        }

        public Long getCountItem() {
            return countItem;
        }

        public void setCountItem(Long countItem) {
            this.countItem = countItem;
        }
    }



//repository

    public  LiveData<List<PainRecordDAO.CountedPainLocationItem>> getPainLocationCount() {
        return painRecordDao.getPainLocationCount();
    }


//view model

    public  LiveData<List<PainRecordDAO.CountedPainLocationItem>> getPainLocationCount(){return pRepository.getPainLocationCount();}


 */

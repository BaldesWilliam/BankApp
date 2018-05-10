package com.helpmeproductions.willus08.bankapp.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface DataStorageDao {

    @Query("select * from datastorage where password = :encryptedPassword")
    DataStorage getWithPassword(String encryptedPassword);

    @Insert(onConflict = IGNORE)
    void addData(DataStorage dataStorage);
}

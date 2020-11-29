package com.example.tv_app_mvvm.Local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.tv_app_mvvm.Model.Response.TvList;

import java.util.List;

@Dao
public interface TvDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TvList TvList);

    @Query("Select * From TvList")
    LiveData<List<TvList>> getAll();
}

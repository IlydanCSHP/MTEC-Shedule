package com.example.mtecschedule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mtecschedule.model.Pair;

import java.util.List;

@Dao
public interface PairDao {
    @Insert
    void insert(Pair pair);

    @Insert
    void insertAll(Pair pair);

    @Delete
    void delete(Pair pair);

    @Update
    void update(Pair pair);

    @Query("SELECT * FROM pairs WHERE id == :id")
    Pair findById(Long id);

    @Query("SELECT * FROM pairs WHERE schedule_id == :id")
    LiveData<List<Pair>> findBySchedule(Long id);

    @Query("SELECT * FROM pairs")
    List<Pair> getAll();

    @Query("SELECT * FROM pairs")
    LiveData<List<Pair>> getAllLive();
}

package com.example.mtecschedule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mtecschedule.model.Group;
import com.example.mtecschedule.model.Schedule;

import java.util.List;

@Dao
public interface ScheduleDao {

    @Insert
    void insert(Schedule schedule);

    @Insert
    void insertAll(Schedule... schedules);

    @Delete
    void delete(Schedule schedule);

    @Update
    void update(Schedule schedule);

    @Query("SELECT * FROM schedules WHERE id == :id")
    Schedule findById(Long id);

    @Query("SELECT * FROM schedules")
    List<Schedule> getAll();

    @Query("SELECT * FROM schedules")
    LiveData<List<Schedule>> getAllLive();
}

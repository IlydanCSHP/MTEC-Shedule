package com.example.mtecschedule.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mtecschedule.model.Group;

import java.util.List;

@Dao
public interface GroupDao {
    @Insert
    void insert(Group group);

    @Insert
    void insertAll(Group... groups);

    @Delete
    void delete(Group group);

    @Update
    void update(Group group);

    @Query("SELECT * FROM groups WHERE id == :id")
    Group findById(Long id);
    @Query("SELECT * FROM groups")
    List<Group> getAll();

    @Query("SELECT * FROM groups")
    LiveData<List<Group>> getAllLive();
}

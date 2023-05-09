package com.example.mtecschedule.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mtecschedule.dao.GroupDao;
import com.example.mtecschedule.dao.PairDao;
import com.example.mtecschedule.dao.ScheduleDao;
import com.example.mtecschedule.model.Group;
import com.example.mtecschedule.model.Pair;
import com.example.mtecschedule.model.Schedule;

@Database(entities = {Group.class, Pair.class, Schedule.class}, version = 4)
public abstract class MtecRoomDatabase extends RoomDatabase {
    public abstract GroupDao groupDao();
    public abstract PairDao pairDao();
    public abstract ScheduleDao scheduleDao();

    public static volatile MtecRoomDatabase INSTANCE;

    public static MtecRoomDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (MtecRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    MtecRoomDatabase.class,
                                    "MtecDB")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

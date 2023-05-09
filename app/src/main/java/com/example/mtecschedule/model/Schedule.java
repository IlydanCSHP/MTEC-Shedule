package com.example.mtecschedule.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "schedules"
)
public class Schedule {
    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "schedule_date")
    private String date;

    public Schedule(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", date='" + date + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

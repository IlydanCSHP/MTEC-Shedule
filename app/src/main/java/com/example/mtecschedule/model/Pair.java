package com.example.mtecschedule.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "pairs",
        foreignKeys = {
                @ForeignKey(
                        entity = Schedule.class,
                        parentColumns = "id",
                        childColumns = "schedule_id",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class Pair {
    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "pair_title")
    private String title;

    @ColumnInfo(name = "pair_teacher")
    private String teacher;

    @ColumnInfo(name = "pair_room")
    private Integer room;

    @ColumnInfo(name = "schedule_id")
    private Long scheduleId;

    @ColumnInfo(name = "pair_number")
    private Integer number;

    public Pair() {

    }

    public Pair(String title, String teacher, int room, int number) {
        this.title = title;
        this.teacher = teacher;
        this.room = room;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }
}

package com.example.mtecschedule.model;

public class Couple {
    private long id;
    private String title, teacher, time;
    private int room, number;

    public Couple(long id, String title, String teacher, String time, int room, int number) {
        this.id = id;
        this.title = title;
        this.teacher = teacher;
        this.time = time;
        this.room = room;
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
}

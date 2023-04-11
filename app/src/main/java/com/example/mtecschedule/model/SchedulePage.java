package com.example.mtecschedule.model;

import java.util.List;

public class SchedulePage {
    private long id;
    private String title, date;
    private List<Couple> coupleList;

    public SchedulePage(long id, String title, String date, List<Couple> coupleList) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.coupleList = coupleList;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Couple> getCouplesList() {
        return coupleList;
    }

    public void setCouplesList(List<Couple> coupleList) {
        this.coupleList = coupleList;
    }
}

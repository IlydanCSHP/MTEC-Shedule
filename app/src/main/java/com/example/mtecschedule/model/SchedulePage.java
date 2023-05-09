package com.example.mtecschedule.model;

import java.util.List;

public class SchedulePage {
    private long id;
    private String title, date;
    private List<Pair> pairList;

    public SchedulePage(long id, String title, String date, List<Pair> pairList) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.pairList = pairList;
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

    public List<Pair> getCouplesList() {
        return pairList;
    }

    public void setCouplesList(List<Pair> pairList) {
        this.pairList = pairList;
    }
}

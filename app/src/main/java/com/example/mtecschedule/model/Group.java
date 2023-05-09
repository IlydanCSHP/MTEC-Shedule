package com.example.mtecschedule.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "groups"
)
public class Group {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "group_title")
    private String title;

    @ColumnInfo(name = "group_full_title")
    private String fullTitle;

    @ColumnInfo(name = "group_speciality")
    private String speciality;

    @ColumnInfo(name = "group_course")
    private Integer course;

    public Group(){

    }

    public Group(String title, String fullTitle, String speciality, Integer course) {
        this.title = title;
        this.fullTitle = fullTitle;
        this.speciality = speciality;
        this.course = course;
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

    public String getFullTitle() {
        return fullTitle;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }
}

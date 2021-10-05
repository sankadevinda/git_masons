package com.example.blooddonationlsystems;

import com.google.firebase.database.Exclude;

import java.util.Date;

public class Camp {
    private String name;
    private String date;
    private String location;

    @Exclude
    private String key;

    public Camp() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}


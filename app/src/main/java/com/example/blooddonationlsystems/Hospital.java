package com.example.blooddonationlsystems;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private String name;
    private String blood;
    private String city;
    private String phone;
    private List<String> Types = new ArrayList<>();

    @Exclude
    private String key;

    public Hospital() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getTypes() {
        return Types;
    }

    public void setTypes(List<String> types) {
        Types = types;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

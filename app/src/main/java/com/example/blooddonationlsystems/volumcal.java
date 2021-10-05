package com.example.blooddonationlsystems;


public class volumcal {
    protected float menBloodVolum(Float value) {
        Float ans = (value*value*value * 3669/10000) + (3219/10000*value+6041/10000);
        return ans;
    }
    protected float womenBloodVolum(Float value) {
        Float ans = (value*value*value * 3561/10000) + (3308/100000*value + 1833/10000);
        return ans;
    }

}


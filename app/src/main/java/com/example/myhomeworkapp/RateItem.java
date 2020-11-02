package com.example.myhomeworkapp;


public class RateItem {
    private String curName;
    private float curRate;
    private int id;

    public RateItem(String curName, float curRate, int id) {
        this.curName = curName;
        this.curRate = curRate;
        this.id = id;
    }

    public RateItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurName() {
        return curName;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    public float getCurRate() {
        return curRate;
    }

    public void setCurRate(float curRate) {
        this.curRate = curRate;
    }
}

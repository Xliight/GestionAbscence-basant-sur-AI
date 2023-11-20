package com.example.model;

public class abs {
    private String name;
    private String immatr;
    private String date_arriv;


    public abs(String name, String immatr, String date_arriv) {
        this.name = name;
        this.immatr = immatr;
        this.date_arriv = date_arriv;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImmatr() {
        return immatr;
    }

    public void setImmatr(String immatr) {
        this.immatr = immatr;
    }

    public String getDate_arriv() {
        return date_arriv;
    }

    public void setDate_arriv(String date_arriv) {
        this.date_arriv = date_arriv;
    }


}

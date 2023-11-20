package com.example.model;

import javafx.scene.control.Button;

public class employee {
    private String name;
    private String immatr;
    private String cin;
    private String email;
    private String phone;
    private String depart;

    private Button button;

    public employee(String name, String immatr, String cin, String email, String phone, String depart) {
        this.name = name;
        this.immatr = immatr;
        this.cin = cin;
        this.email = email;
        this.phone = phone;
        this.depart = depart;
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

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    @Override
    public String toString() {
        return "employee{" +
                "name='" + name + '\'' +
                ", immatr='" + immatr + '\'' +
                ", cin='" + cin + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", depart='" + depart + '\'' +
                '}';
    }
}

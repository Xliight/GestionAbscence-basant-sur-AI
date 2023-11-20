package com.example.model;

import javafx.scene.control.Button;

public class Card {
    private String img;
    private String name;
    private String immatr;
    private String email;
    private Button upload;



//    public Card(String img, String name, String immatr, String email, String phone, String depart) {
//        this.img = img;
//        this.name = name;
//        this.immatr = immatr;
//        this.email = email;
//        this.phone = phone;
//        this.depart = depart;
//    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Button getUpload() {
        return upload;
    }

    public void setUpload(Button upload) {
        this.upload = upload;
    }
}

package com.example.demostage;

import com.example.model.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class controllercard  {

    @FXML
    private Text email;

    @FXML
    private ImageView img;

    @FXML
    private Text immatr;

    @FXML
    private Text name;

    @FXML
    private Button upload;
    public void setdata(Card card){
        Image image=new Image(getClass().getResourceAsStream(card.getImg()));
        img.setImage(image);
        name.setText(card.getName());
        immatr.setText(card.getImmatr());
        email.setText(card.getEmail());
        upload.setText("gg");


    }



}

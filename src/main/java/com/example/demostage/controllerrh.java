package com.example.demostage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;




import java.io.IOException;
import java.util.List;

public class controllerrh  {
//    HelloApplication s=new HelloApplication();
    scene scene= new scene();
    private Process pythonProcess;
    @FXML
    void goemployee(ActionEvent event) throws IOException {
        scene.changeScene(event,"addempl.fxml" ,  "ajouter eployee" , 960 , 640 );
    }
    @FXML
    void gotolistempl(ActionEvent event) throws IOException {
        scene.changeScene(event,"listempl.fxml" ,  "ajouter eployee" , 960 , 640 );

    }

    @FXML
    void gopresence(ActionEvent event) throws IOException {
       scene.changeScene(event,"absence.fxml" ,  "ajouter eployee" , 900 , 600 );

    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        scene.changeScene(event,"log.fxml" ,  "ajouter eployee" , 900 , 600 );
    }
    @FXML
    void Start_script(ActionEvent event){


    }
}
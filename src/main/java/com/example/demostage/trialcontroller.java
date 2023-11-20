package com.example.demostage;

import com.example.model.Card;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class trialcontroller implements Initializable {


    @FXML
    private Button addcondibtn;

    @FXML
    private VBox vboxlayout;

    @FXML
    void backtorh(ActionEvent event) {

    }

    @FXML
    void deleteempl(ActionEvent event) {

    }

    @FXML
    void showlist(ActionEvent event) {

    }
    private List<Card> recentlyadded;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        recentlyadded =new ArrayList<>(recentlyadded());
        try {
         for (int i=0;i<recentlyadded.size();i++){
             FXMLLoader fxmlLoader=new FXMLLoader();
             fxmlLoader.setLocation(getClass().getResource("card.fxml"));

             VBox cardvbox=fxmlLoader.load();
             controllercard controllercard=fxmlLoader.getController();
             controllercard.setdata(recentlyadded.get(i));
             vboxlayout.getChildren().add(cardvbox);


             }
             }catch (IOException e) {
            throw new RuntimeException(e);
         }
    }
    private List<Card> recentlyadded(){
        List<Card> l=new ArrayList<>();
        Card card=new Card();
        card.setName("abdo");
        card.setImg("com/example/demostage/Recrutement.png");
        card.setImmatr("1235485");
        card.setEmail("azdfaz@gzjz.com");
        l.add(card);

        card=new Card();
        card.setName("abdo2");
        card.setImg("com/example/demostage/Recrutement.png");
        card.setImmatr("123548552");
        card.setEmail("azdfaz@gzjz2.com");
        l.add(card);

        card=new Card();
        card.setName("abdo3");
        card.setImg("com/example/demostage/Recrutement.png");
        card.setImmatr("12354853");
        card.setEmail("azdfaz@gzjz3.com");
        l.add(card);
        return l;
    }
}

package com.example.demostage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.example.dao.*;
import com.example.model.*;

import java.io.IOException;

public class controllerempl {
    @FXML
    private TextField Cin;

    @FXML
    private TextField Immatr;

    @FXML
    private Button addempbtn;

    @FXML
    private TextField depart;

    @FXML
    private TextField email;

    @FXML
    private Label labelmsg;

    @FXML
    private TextField name;

    @FXML
    private TextField phone;

    @FXML
    void addemp(ActionEvent event) {
        String immatr=Immatr.getText();
        String fullname = name.getText();
        String cine = Cin.getText();
        String emaile = email.getText();
        String phonee = phone.getText();
        String departe = depart.getText();
        String s=null;
        functions.checkImmatr(String.valueOf(immatr));
        if (fullname.isEmpty() || cine.isEmpty() || emaile.isEmpty() || phonee.isEmpty() || immatr.isEmpty() ){
            s="Tous les champs doit être remplies";
            labelmsg.setText(s) ;
        } else {

            if (functions.isValid(emaile) && functions.isValidNumb(phonee) && functions.isvalidimmatr(Immatr.getText())) {
                if (functions.checkImmatr(emaile)){
                    employeedao employeedao = new emlpoyeedaoImp();
                    employee re = new employee(fullname, immatr, cine, emaile, phonee , departe);
                    employeedao.save(re);
                    name.clear();
                    Cin.clear();
                    email.clear();
                    phone.clear();
                    Immatr.clear();
                    depart.clear();

                    s= "L'employéé' a été ajouté avec succès";
                    labelmsg.setText(s) ;
                }else  {


                    s="\n" +
                            "Adresse e-Mail déjà utilisée";
                    labelmsg.setText(s) ;
                }

            }else {

                s="Le format de l'e-mail ou du numéro de téléphone est incorrect\n ";
                labelmsg.setText(s) ;


            }

        }

    }

    @FXML
    void backtorh(ActionEvent event) throws IOException {

        scene.changeScene(event,"menu.fxml" ,  "ajouter eployee" , 900 , 600 );
    }
    }

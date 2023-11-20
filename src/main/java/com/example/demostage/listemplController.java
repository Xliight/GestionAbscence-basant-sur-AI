package com.example.demostage;

import com.example.dao.emlpoyeedaoImp;
import com.example.dao.employeedao;
import com.example.model.employee;
import com.example.dbconnection.dbconnection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class listemplController  {
    @FXML
    private Button addcondibtn;

    @FXML
    private TableColumn<employee, String> depart;

    @FXML
    private TableColumn<employee, String> cin;

    @FXML
    private TableColumn<employee, String> email;

    @FXML
    private TableColumn<employee, String> phone;

    @FXML
    private TableColumn<employee, String> name;

    @FXML
    private TableColumn<employee, Long> immatr;

    @FXML
    private TableView<employee> table;
    ObservableList<employee> l;

    employeedao employeedao=new emlpoyeedaoImp();
    public void refreshtablecondidat(){
        name.setCellValueFactory(new PropertyValueFactory<employee,String>("name"));
        immatr.setCellValueFactory(new PropertyValueFactory<employee,Long>("immatr"));
        cin.setCellValueFactory(new PropertyValueFactory<employee,String>("cin"));
        email.setCellValueFactory(new PropertyValueFactory<employee,String>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<employee,String>("phone"));
        depart.setCellValueFactory(new PropertyValueFactory<employee,String>("depart"));
        l=employeedao.showlist();
        System.out.println(l);
        table.setItems(l);
    }
    @FXML
    void showlist(ActionEvent event) {
        refreshtablecondidat();
    }

    @FXML
    void backtorh(ActionEvent event) throws IOException {
        scene.changeScene(event,"menu.fxml" ,  "ajouter eployee" , 900  , 600 );
    }

    @FXML
    void deleteempl(ActionEvent event) {
        if (table.getSelectionModel().getSelectedItem()==null){
            return;
        }
        Connection con=dbconnection.getConnection();
        if (con == null) {
            return;
        }else { System.out.println("connection success");}
        String query = "DELETE FROM employee WHERE immatr=?;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setString(1, table.getSelectionModel().getSelectedItem().getImmatr());


            preparedStatement.executeUpdate();
            System.out.println("deleted");
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        refreshtablecondidat();
    }

    @FXML
    public void gotoaddempl(ActionEvent event) throws IOException {
        scene.changeScene(event,"Addempl.fxml" ,  "ajouter eployee" , 960 , 640 );
    }




}

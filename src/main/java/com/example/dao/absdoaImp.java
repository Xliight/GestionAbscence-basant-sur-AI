package com.example.dao;

import com.example.dbconnection.dbconnection;
import com.example.model.abs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class absdoaImp implements absdao{



    @Override
    public ObservableList<abs> showlistabs(String immatri) {
        Connection con= dbconnection.getConnection();
        if (con==null){
            System.out.println("connection failled");

        }else{
            System.out.println("connection success");
        }
        ObservableList<abs> abs = FXCollections.observableArrayList();

        String query="Select * from abs where immatr=?";

        try(PreparedStatement preparedStatement= con.prepareStatement(query)){
            preparedStatement.setString(1, immatri);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                abs.add(new abs(resultSet.getString("name"),resultSet.getString("immatr"),resultSet.getString("date_arriv")));
            }

        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            try{
                con.close();

            }catch (SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("impl is done");

        return abs;
    }
}

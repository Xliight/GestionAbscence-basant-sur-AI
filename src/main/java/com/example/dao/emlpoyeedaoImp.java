package com.example.dao;

import com.example.model.employee;
import com.example.dbconnection.dbconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class emlpoyeedaoImp implements employeedao {

    @Override
    public void save(employee e) {
        Connection con =dbconnection.getConnection();
        if (con == null) {
            return;
        }else { System.out.println("connection success");}

        // Update
        String query = "insert into employee (name, immatr, cin, email,phone,depart)  VALUES (?, ?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setString(1, e.getName());
            preparedStatement.setString(2, e.getImmatr());
            preparedStatement.setString(3, e.getCin());
            preparedStatement.setString(4, e.getEmail());
            preparedStatement.setString(5, e.getPhone());
            preparedStatement.setString(6, e.getDepart());
            preparedStatement.executeUpdate();
            System.out.println("Saved");
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    @Override
    public ObservableList<employee> showlist() {

        Connection con=dbconnection.getConnection();
        if (con==null){
            System.out.println("connection failled");

        }else{
            System.out.println("connection success");
        }
        ObservableList<employee> employees = FXCollections.observableArrayList();

        String query="Select * from employee";

        try(PreparedStatement preparedStatement= con.prepareStatement(query)){
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                employees.add(new employee(resultSet.getString("name"),resultSet.getString("immatr"),resultSet.getString("cin"),resultSet.getString("email"),resultSet.getString("phone"),resultSet.getString("depart")));
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

        return employees;
    }

    @Override
    public ObservableList<employee> showlistbyid(String immatri) {
        Connection con=dbconnection.getConnection();
        if (con==null){
            System.out.println("connection failled");

        }else{
            System.out.println("connection success");
        }
        ObservableList<employee> employees = FXCollections.observableArrayList();

        String query="Select * from abs where immatr=?";

        try(PreparedStatement preparedStatement= con.prepareStatement(query)){
            preparedStatement.setString(1, immatri);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                employees.add(new employee(resultSet.getString("name"),resultSet.getString("immatr"),resultSet.getString("date-arriv"),resultSet.getString("date-sortie"),resultSet.getString("abs"),resultSet.getString("depart")));
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

        return employees;
    }}


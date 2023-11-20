package com.example.demostage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.*;

import com.example.dbconnection.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class login {
    String password1 = "swypomderffmnuix";
    String to = "abdomostmee@gmail.com";
    String from = "abdoachrafyahya2001@gmail.com";
    String subject = "Second: Reset password";

    @FXML
    private Button back;
    @FXML
    private Label wrongLogIn;
    @FXML
    private Label msgconf;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField otp;
    private Integer globalotp;
    private static Stage stg;

    public void adminLogIn(ActionEvent event) throws IOException {
        checkLogin();

    }

    private void checkLogin() throws IOException {
        HelloApplication s = new HelloApplication();

        String immatr  = username.getText();
        String motdepasse = password.getText();
        ResultSet resultSet=null;
        if (immatr.isBlank()==false  && motdepasse.isBlank()==false){
            Connection con=dbconnection.getConnection();
            if (con == null) {
                return;
            }else {
                System.out.println("connection succes");
            }

            String query = "select password from rh where immatr=?;";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                 preparedStatement.setString(1,immatr);
                 resultSet=preparedStatement.executeQuery();
                if(!resultSet.isBeforeFirst()){
                    wrongLogIn.setText("Wrong username or password!");
                }else{
                    while(resultSet.next()){
                        String retrievedPassword = resultSet.getString("password");
                        if(retrievedPassword.equals(motdepasse)){
                           s.changeScene("menu.fxml");
                        }
                    }


            }


        } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }else {
            wrongLogIn.setText("Wrong username or password!");
        }
    }
    public void forgetpass(ActionEvent event) throws IOException {
        HelloApplication s = new HelloApplication();
        String otp=Mailer.generateOTP(6);
        Connection con=dbconnection.getConnection();
        if (con == null) {
            return;
        }else {
            System.out.println("connection succes ");
        }
        String query = "insert into otpbase (otp)  VALUES (?);";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, otp);
            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        s.changeScene("pass.fxml");
        String subject = "Second: Reset password";
        String text = "your otp is "+ otp;
        Mailer.send(from,password1,to,subject,text);
        System.out.println(otp);
        globalotp=Integer.parseInt(otp);


    }
    public void confirmotp(ActionEvent event) throws SQLException {
        String otps=otp.getText();
        Integer otpconf=Integer.parseInt(otps);
        System.out.println("otpconf is "+otpconf);
        Integer otpb=0;
        Connection con=dbconnection.getConnection();
        if (con == null) {
            return;
        }else {
            System.out.println("connection succes ");
        }
        ResultSet resultSet1=null;
        String insert = "select otp from otpbase ORDER BY id DESC LIMIT 1;";
        try (PreparedStatement preparedStatement = con.prepareStatement(insert)) {
            resultSet1=preparedStatement.executeQuery();
            while (resultSet1.next()) {
                otpb = resultSet1.getInt("otp");
                System.out.println(otpb);



            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(otpconf.equals(otpb)){
            ResultSet resultSet=null;
            String pass="";
            String query = "select password from rh ;";

            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                resultSet=preparedStatement.executeQuery();
                while (resultSet.next()) {
                    pass = resultSet.getString("password");
                    String text = "your password is "+ pass;
                    Mailer.send(from,password1,to,subject,text);;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            msgconf.setText("something wrong");}

    }
    public void backtologin(ActionEvent event) throws IOException {
        HelloApplication s=new HelloApplication();
        s.changeScene("log.fxml");
    }

}


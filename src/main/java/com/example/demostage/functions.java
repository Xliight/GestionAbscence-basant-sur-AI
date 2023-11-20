package com.example.demostage;

import com.example.dbconnection.dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class functions {
    public static boolean checkImmatr(String immatr){
        boolean check=true;
        String x="";
        ResultSet resultSet;
        Connection con = dbconnection.getConnection();
        if (con!= null) { System.out.println("connection success");}
        else { System.out.println("connection failed");
        }
        ;
        String query="Select * from employee where immatr =? ";
        try(PreparedStatement preparedStatement= con.prepareStatement(query)){
            preparedStatement.setString(1, immatr);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                if (resultSet.getString("immatr").equals(immatr)){
                    check=false;
                }
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
        return check;
    }
    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static boolean isValidNumb(String s)
    {
        Pattern p = Pattern.compile("^\\d{10}$");
        Matcher m = p.matcher(s);
        return (m.matches());
    }
    public static boolean isvalidimmatr(String input){
        Boolean flag=true;
        for(int a=0;a<input.length();a++)
        {
            if(a==0 && input.charAt(a) == '-')
                continue;
            if( !Character.isDigit(input.charAt(a)))
                flag=false;
        }
        if(flag)
        {
            return true;
        }else {
            return false;
        }
    }
}

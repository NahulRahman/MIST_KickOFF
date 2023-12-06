package com.example.nahulthejoker;
import java.sql.Connection;
import java.sql.DriverManager;


public class DbConnectionPlayer {
    public static Connection databaseLink;

    public static Connection getConnection(){
        String databaseName = "db_main";
        String databaseUser = "root";
        String databasePassword = "root";
        String url = "jdbc:mysql://127.0.0.1:3306/" + databaseName;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return databaseLink;
    }

}
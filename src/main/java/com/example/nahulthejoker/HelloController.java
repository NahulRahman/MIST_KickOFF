package com.example.nahulthejoker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.EventObject;

public class HelloController {

    @FXML
    private Button btnforgot;

    @FXML
    private Button btnsignin;

    @FXML
    private Button btnsignup;

    @FXML
    private PasswordField txtpass;

    @FXML
    private TextField txtuser;

    @FXML
    void forgetpass(ActionEvent event) throws IOException {
        swtch sw = new swtch();
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("forgotpassword.fxml"));
        sw.switch_scene(root,event);
    }

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

public static String uname;
    public static String playerPos;

    public static String playerVal;

    @FXML
    void logintohomepage(ActionEvent event) throws IOException {
String pass;

        uname = txtuser.getText();
        pass= txtpass.getText();


        if(uname.equals("") || pass.equals(""))
        {
            //warninglabel.setText("Fill up the blank field!!!");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Login Failed!");
            alert.showAndWait();
            txtuser.setText("");
            txtpass.setText("");
        }

        else {
            try {
                //  sharedClass.getInstance().setUsername(uname);
                con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_main", "root", "root");

                pst = con.prepareStatement("SELECT * FROM teams WHERE Username=? and Password=?");

                pst.setString(1, uname);
                pst.setString(2, pass);

                rs = pst.executeQuery();


                if (rs.next()) {


                    swtch sw = new swtch();

                    Parent root = FXMLLoader.load(HelloApplication.class.getResource("home.fxml"));
                    sw.switch_scene(root,event);
//
                    throw new customException();
                } else {


                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Login Failed!");
                    alert.showAndWait();
                    txtuser.setText("");
                    txtpass.setText("");


                }
            }
                catch(customException f){
                    f.show();
                }
             catch (SQLException e) {
                e.printStackTrace();

            }
        }


    }

    @FXML
    void registrationpage(ActionEvent event) throws IOException {

        swtch sw = new swtch();
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("register.fxml"));
        sw.switch_scene(root,event);
    }




}

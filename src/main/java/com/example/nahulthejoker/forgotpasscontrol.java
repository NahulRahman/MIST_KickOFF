package com.example.nahulthejoker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.EventObject;

public class forgotpasscontrol {

    @FXML

    private Label warninglabel;
    public TextField txtuser;
    public TextField txtcontact;
    public PasswordField txtpass;
    public PasswordField txtconfirmpass;


    @FXML
    void logineja(MouseEvent event) throws IOException {
        swtch sw = new swtch();

        Parent root = FXMLLoader.load(HelloApplication.class.getResource("login.fxml"));
        sw.switch_scene(root,event);

    }



    @FXML
    void forgottologin(ActionEvent event) throws IOException{

        String user = txtuser.getText();
        String contact = txtcontact.getText();
        String pass = txtpass.getText();
        String confirmpass = txtconfirmpass.getText();
        if(user.equals("") || contact.equals("")  || pass.equals("") || confirmpass.equals(""))
        {
            //warninglabel.setText("Please Fill in All Fields!!!");
            abstractImplement s=new abstractImplement();
            s.warning();
            txtuser.setText("");
            txtcontact.setText("");
            txtpass.setText("");
            txtconfirmpass.setText("");

        }

        else if (!pass.equals(confirmpass)) {
            //warninglabel.setText("Password Does Not Match!!!");
            abstractImplement s=new abstractImplement();
            s.warning(false);

            txtuser.setText("");
            txtcontact.setText("");
            txtpass.setText("");
            txtconfirmpass.setText("");

        }

        else
        {
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_main", "root", "root")) {

                // Check if user exists and contact number matches
                //String sql = "SELECT * FROM teams WHERE Username = ? AND Contact_Number = ?";
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM teams WHERE Username = ? AND Contact_Number = ?");
                statement.setString(1, user);

                statement.setString(2, contact);
                ResultSet resultSet = statement.executeQuery();


                // If user and contact number match, proceed with password reset
                if (resultSet.next()) {

                    statement = connection.prepareStatement("UPDATE teams SET Password = ?, Confirm_Password = ? WHERE Username = ?");
                    statement.setString(1, pass);
                    statement.setString(2, confirmpass);
                    statement.setString(3, user);
                    statement.executeUpdate();


                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Password reset successful.");
                    alert.showAndWait();
                    txtuser.setText("");
                    txtcontact.setText("");
                    txtpass.setText("");
                    txtconfirmpass.setText("");


                    connection.close();

                    swtch sw = new swtch();

                    Parent root = FXMLLoader.load(HelloApplication.class.getResource("login.fxml"));
                    sw.switch_scene(root,event);
                } else {
                    // Display error message if user or contact number doesn't match
                    //warninglabel.setText("Username or contact number does not match.");
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Username or contact number does not match.");
                    alert.showAndWait();
                    txtuser.setText("");
                    txtcontact.setText("");
                    txtpass.setText("");
                    txtconfirmpass.setText("");

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);

            }
        }
    }





}





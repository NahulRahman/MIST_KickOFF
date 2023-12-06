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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.EventObject;


public class registercontrol
{

    @FXML
    private Label warninglabel;

    public TextField txtuser;

    public TextField txtdept;
    public TextField txtid;

    public TextField txtcontact;
    public PasswordField txtpass;
    public PasswordField txtconfirmpass;


    @FXML
    void registerthekelogin(MouseEvent event) throws IOException {

        swtch sw = new swtch();

        Parent root = FXMLLoader.load(HelloApplication.class.getResource("login.fxml"));
        sw.switch_scene(root,event);

    }

    @FXML
    void registertologin(ActionEvent event) throws IOException {
        String username = txtuser.getText().trim();
        String dept = txtdept.getText().trim();
        String id = txtid.getText().trim();
        String contact = txtcontact.getText().trim();
        String pass = txtpass.getText().trim();
        String confirmpass = txtconfirmpass.getText().trim();

        if (username.equals("") || dept.equals("") || id.equals("") || contact.equals("") || pass.equals("") || confirmpass.equals("")) {
            //warninglabel.setText("Please Fill in all Fields!!!");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please Fill in all Fields!!!");

            alert.showAndWait();
            txtuser.setText("");
            txtdept.setText("");
            txtid.setText("");
            txtcontact.setText("");
            txtpass.setText("");
            txtconfirmpass.setText("");

        }
        // Check if email already exists in the database
        else if (!pass.equals(confirmpass)) {
            //warninglabel.setText("Password Does Not Match!!!");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Password Does Not Match!!!");

            alert.showAndWait();
            txtuser.setText("");
            txtdept.setText("");
            txtid.setText("");
            txtcontact.setText("");
            txtpass.setText("");
            txtconfirmpass.setText("");

        } else if (!(pass.length() > 6 && containsSpecialCharacter(pass))
        ) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please make the password length more than 6 and include special character!!!");

            alert.showAndWait();
            txtuser.setText("");
            txtdept.setText("");
            txtid.setText("");
            txtcontact.setText("");
            txtpass.setText("");
            txtconfirmpass.setText("");
            
        }


        // Insert new user into the database
        else {
            //String sql = "INSERT INTO teams (Username, Department_Name, Student_ID, Contact_Number, Password, Confirm_Password ) VALUES (?, ?, ?, ?, ?, ?)";
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_main", "root", "root");
                PreparedStatement statement = connection.prepareStatement("INSERT INTO teams (Username, Department_Name, Student_ID, Contact_Number, Password, Confirm_Password ) VALUES (?, ?, ?, ?, ?, ?)");
                statement.setString(1, username);
                statement.setString(2, dept);
                statement.setString(3, id);
                statement.setString(4, contact);
                statement.setString(5, pass);
                statement.setString(6, confirmpass);
                statement.executeUpdate();

                // Show success message and clear form fields
                //warninglabel.setText("Registration Successful!!!");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Registration Successful!!!");

                alert.showAndWait();
                txtuser.setText("");
                txtdept.setText("");
                txtid.setText("");
                txtcontact.setText("");
                txtpass.setText("");
                txtconfirmpass.setText("");
//                ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
//
//                // Load the new FXML scene
//                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
//                Scene scene = new Scene(fxmlLoader.load());
//
//                // Create a new stage
//                Stage newStage = new Stage();
//
//                // Set the new scene on the stage and show the stage
//                newStage.setScene(scene);
//                newStage.show();

                swtch sw = new swtch();

                Parent root = FXMLLoader.load(HelloApplication.class.getResource("login.fxml"));
                sw.switch_scene(root,event);



            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }
    private boolean containsSpecialCharacter(String str) {
        return !str.matches("[a-zA-Z0-9]+");
    }



}

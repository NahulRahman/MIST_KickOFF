package com.example.nahulthejoker;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;

import static com.example.nahulthejoker.HelloController.uname;

public class customException extends Exception {
    public void show() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Congratulations!!!");
        alert.setHeaderText(null);
        alert.setContentText("Welcome 'Team " + uname + "' to MIST KickOff!!");
        alert.showAndWait();


    }
}







package com.example.nahulthejoker;

import javafx.scene.control.Alert;

public class abstractImplement implements abstractMethod{
    public void warning(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Please Fill in All Fields!!!");
        alert.showAndWait();

    }
    public void warning(boolean x){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Password Does Not Match!!!");
        alert.showAndWait();


    }


}

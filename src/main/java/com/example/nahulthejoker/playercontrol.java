package com.example.nahulthejoker;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.nahulthejoker.HelloController.uname;

public class playercontrol {
    Connection connection = DbConnectionPlayer.getConnection();
    PreparedStatement pst;

    public playercontrol() {
    }

    @FXML
    void playertohome(MouseEvent event) throws IOException {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

        // Load the new FXML scene
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Create a new stage
        Stage newStage = new Stage();

        // Set the new scene on the stage and show the stage
        newStage.setScene(scene);
        newStage.show();
    }


}

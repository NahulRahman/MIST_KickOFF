package com.example.nahulthejoker;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;

public class swtch {

    public void switch_scene(Parent root, EventObject event) throws IOException
    {
//        root = FXMLLoader.load(HelloApplication.class.getResource("fxml/faculty/faculty_login.fxml"));
//        switch_scene(root,event);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        //stage.setTitle("Responsible CR");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}

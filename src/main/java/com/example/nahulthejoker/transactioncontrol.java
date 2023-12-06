package com.example.nahulthejoker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.nahulthejoker.HelloController.uname;

public class transactioncontrol implements Initializable {
    @FXML
    private TableColumn<transactionModel, String> cur_amnColumn;

    @FXML
    private TableColumn<transactionModel, String> deptColumn;

    @FXML
    private TableColumn<transactionModel, String> idColumn;

    @FXML
    private TableColumn<transactionModel, String> nameColumn;

    @FXML
    private TableColumn<transactionModel, String> posColumn;

    @FXML
    private TableView<transactionModel> showTable;

    @FXML
    private TableColumn<transactionModel, String> statColumn;

    @FXML
    private TableColumn<transactionModel, String> valColumn;
    @FXML
    private Label totalMoney;

    Connection connection = DbConnectionPlayer.getConnection();
    PreparedStatement pst; int myIndex; int id;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        table();
        try {

            pst = connection.prepareStatement("select Money from teams where Username= ? ");
            pst.setString(1, uname);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int retrievedValue = rs.getInt("Money");
                totalMoney.setText(String.valueOf(retrievedValue));


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void table()
    {
        ObservableList<transactionModel> trans= FXCollections.observableArrayList();
        try
        {

            pst= connection.prepareStatement("select Serial,name,id,dept,position,value,status,current_amount from transaction where club= ? ");
            pst.setString(1, uname);

            ResultSet rs= pst.executeQuery();
            while (rs.next()){
                transactionModel tn=new transactionModel();
                tn.setName(rs.getString("name"));
                tn.setId(rs.getString("id"));
                tn.setDept(rs.getString("dept"));
                tn.setPos(rs.getString("position"));
                tn.setVal(rs.getString("value"));
                tn.setStatus(rs.getString("status"));
                tn.setCurrent_amount(rs.getString("current_amount"));
                trans.add(tn);





            }

            showTable.setItems(trans);
            deptColumn.setCellValueFactory(f->f.getValue().deptProperty());
            nameColumn.setCellValueFactory(f->f.getValue().nameProperty());
            idColumn.setCellValueFactory(f->f.getValue().idProperty());
            valColumn.setCellValueFactory(f->f.getValue().valProperty());
            posColumn.setCellValueFactory((f->f.getValue().posProperty()));
            statColumn.setCellValueFactory(f->f.getValue().statusProperty());
            cur_amnColumn.setCellValueFactory(f->f.getValue().current_amountProperty());










        } catch (SQLException ex) {
            Logger.getLogger(homecontrol.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw new RuntimeException(ex);

        }






    }



    @FXML
    void transactiontohome(MouseEvent event) throws IOException {
//        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
//
//        // Load the new FXML scene
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//
//        // Create a new stage
//        Stage newStage = new Stage();
//
//        // Set the new scene on the stage and show the stage
//        newStage.setScene(scene);
//        newStage.show();

        swtch sw = new swtch();

        Parent root = FXMLLoader.load(HelloApplication.class.getResource("home.fxml"));
        sw.switch_scene(root,event);
    }

}

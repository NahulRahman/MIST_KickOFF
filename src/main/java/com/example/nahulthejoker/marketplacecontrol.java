package com.example.nahulthejoker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

public class marketplacecontrol implements Initializable {
    @FXML
    private Button btnBuy;

    @FXML
    private TableColumn<playerModel, String> deptColumn;

    @FXML
    private TextField depttxt;

    @FXML
    private TableColumn<playerModel, String> idColumn;

    @FXML
    private TextField idtxt;

    @FXML
    private TableColumn<playerModel, String> nameColumn;

    @FXML
    private TextField nametxt;

    @FXML
    private TableColumn<playerModel, String> posColumn;

    @FXML
    private TextField postxt;

    @FXML
    private TableView<playerModel> showTable;

    @FXML
    private TableColumn<playerModel, String> valColumn;

    @FXML
    private TextField valtxt;
    @FXML
    private Label totalMoney;

    Connection connection = DbConnectionPlayer.getConnection();
    PreparedStatement pst; int myIndex; int id;


    @FXML
    void Buy(ActionEvent event) throws SQLException {
            pst = connection.prepareStatement("UPDATE player SET club = ? WHERE name = ?");
            pst.setString(1, uname );
            pst.setString(2, nametxt.getText() );
            pst.executeUpdate();
        pst = connection.prepareStatement("select value from player where id= ? ");
        pst.setString(1, idtxt.getText());
        ResultSet rs = pst.executeQuery(); int retrievedValue1=0;
        while (rs.next()) {
            retrievedValue1 = rs.getInt("value");

        }
        rs.close();
        pst = connection.prepareStatement("select Money from teams where Username= ?");
        pst.setString(1, uname);
        ResultSet rs1 = pst.executeQuery();int retrievedValue2=0;
        while (rs1.next())
        {
            retrievedValue2 = rs1.getInt("Money");

        }

        //ResultSet rs1 = pst.executeQuery();
        System.out.println(retrievedValue1 + " " + retrievedValue2 + "\n");
        pst = connection.prepareStatement("UPDATE teams SET Money = ? WHERE Username = ?");
        pst.setInt(1, retrievedValue2 - retrievedValue1 );
        pst.setString(2, uname);
        pst.executeUpdate();
        totalMoney.setText(String.valueOf(retrievedValue2 - retrievedValue1));


        String name,id,dept,pos,val,status,current_amn;
        name=nametxt.getText();
        id=idtxt.getText();
        dept=depttxt.getText();
        pos=postxt.getText();
        val=valtxt.getText();
        status="Buy";
        current_amn=String.valueOf(retrievedValue2 - retrievedValue1);
        pst= connection.prepareStatement("insert into transaction(name,id,dept,position,value,club,status,current_amount)values (?,?,?,?,?,?,?,?)");
        pst.setString(1, name);
        pst.setString(2, id);
        pst.setString(3, dept);
        pst.setString(4, pos);
        pst.setString(5, val);
        pst.setString(6, uname);
        pst.setString(7, status);
        pst.setString(8, current_amn);

        pst.executeUpdate();


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Player Bought Successfully!!!");
        alert.showAndWait();
        nametxt.setText("");
        idtxt.setText("");
        depttxt.setText("");
        postxt.setText("");
        valtxt.setText("");
        table();





        }




    public int count=0;

    @FXML
    void markettohome(MouseEvent event) throws IOException {
        swtch sw = new swtch();

        Parent root = FXMLLoader.load(HelloApplication.class.getResource("home.fxml"));
        sw.switch_scene(root,event);
    }
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
        ObservableList<playerModel> students= FXCollections.observableArrayList();
        try
        {

            pst= connection.prepareStatement("select Serial,name,id,dept,position,value from player where club= ? ");
            pst.setString(1, "N");

            ResultSet rs= pst.executeQuery();
            while (rs.next()){
                playerModel st=new playerModel();
                st.setName(rs.getString("name"));
                st.setId(rs.getString("id"));
                st.setDept(rs.getString("dept"));
                st.setPos(rs.getString("position"));
                st.setVal(rs.getString("value"));
                students.add(st);





            }

            showTable.setItems(students);
            deptColumn.setCellValueFactory(f->f.getValue().deptProperty());
            nameColumn.setCellValueFactory(f->f.getValue().nameProperty());
            idColumn.setCellValueFactory(f->f.getValue().idProperty());
            valColumn.setCellValueFactory(f->f.getValue().valProperty());
            posColumn.setCellValueFactory((f->f.getValue().posProperty()));








        } catch (SQLException ex) {
            Logger.getLogger(homecontrol.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw new RuntimeException(ex);

        }
        showTable.setRowFactory(tv-> {
            TableRow<playerModel> myRow= new TableRow<>();
            myRow.setOnMouseClicked(mouseEvent -> {
                if(mouseEvent.getClickCount()==1 && (!myRow.isEmpty())){
                    myIndex = showTable.getSelectionModel(). getSelectedIndex();


                    // id = Integer.parseInt(String.valueOf(showTable.getItems().get(myIndex).getId()));
                    nametxt.setText(showTable.getItems().get(myIndex).getName());
                    idtxt.setText(showTable.getItems().get(myIndex).getId());
                    depttxt.setText(showTable.getItems().get(myIndex).getDept());
                    valtxt.setText(showTable.getItems().get(myIndex).getVal());
                    postxt.setText(showTable.getItems().get(myIndex).getPos());

                }
            });
            return myRow;
        });





}
}


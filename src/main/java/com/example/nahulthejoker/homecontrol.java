package com.example.nahulthejoker;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.FileOutputStream;
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
import static com.example.nahulthejoker.HelloController.playerPos;
import static com.example.nahulthejoker.HelloController.playerVal;



public class homecontrol extends playerModel implements Initializable {
    @FXML
    private Button btnSell;

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
    private Label totalplayer;


    @FXML
    private TableView<playerModel> showTable;

    @FXML
    private TableColumn<playerModel, String> valColumn;

    @FXML
    private TextField valtxt;
    @FXML
    private TextField searchField;
    @FXML
    private Label totalMoney;
    @FXML
    private TextField updatePOS;

    @FXML
    private TextField updateVal;


    Connection connection = DbConnectionPlayer.getConnection();
    PreparedStatement pst;
    int myIndex;
    int id;

    public String q,idd;

     int count = 0;




    @FXML
    void Sell(ActionEvent event) throws SQLException {
        pst = connection.prepareStatement("UPDATE player SET club = ? WHERE name = ?");
        pst.setString(1, "N");
        pst.setString(2, nametxt.getText());
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
       // System.out.println(retrievedValue1 + " " + retrievedValue2 + "\n");

        pst = connection.prepareStatement("UPDATE teams SET Money = ? WHERE Username = ?");
        pst.setInt(1, retrievedValue1 + retrievedValue2);
        pst.setString(2, uname);
        pst.executeUpdate();
        totalMoney.setText(String.valueOf(retrievedValue1 + retrievedValue2));

        String name,id,dept,pos,val,status,current_amn;
        name=nametxt.getText();
        id=idtxt.getText();
        dept=depttxt.getText();
        pos=postxt.getText();
        val=valtxt.getText();
        status="Sell";
        current_amn=String.valueOf(retrievedValue1 + retrievedValue2);
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
        alert.setContentText("Player Sold Successfully!!!");
        alert.showAndWait();
        nametxt.setText("");
        idtxt.setText("");
        depttxt.setText("");
        postxt.setText("");
        valtxt.setText("");
        table();








    }


    @FXML
    void hometoabout(MouseEvent event) throws IOException {
        swtch sw = new swtch();

        Parent root = FXMLLoader.load(HelloApplication.class.getResource("aboutus.fxml"));
        sw.switch_scene(root,event);

    }

    @FXML
    void hometologin(MouseEvent event) throws IOException {
        swtch sw = new swtch();

        Parent root = FXMLLoader.load(HelloApplication.class.getResource("login.fxml"));
        sw.switch_scene(root,event);

    }

    @FXML
    void hometomarketplace(MouseEvent event) throws IOException {
        swtch sw = new swtch();

        Parent root = FXMLLoader.load(HelloApplication.class.getResource("market.fxml"));
        sw.switch_scene(root,event);
    }

    @FXML
    void hometoplayer(MouseEvent event) throws IOException {


            try {
                String path = "E://NahulTheJoker//" + uname + ".pdf";

                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(path));
                document.open();
                document.add(new Paragraph("Welcome to MIST KickOFF!!\n\n\n\n"));
                PreparedStatement statement1 = connection.prepareStatement("SELECT Username, Department_Name, Student_ID, Contact_Number, Money FROM teams WHERE username=?");
                statement1.setString(1, uname);
                ResultSet resultSet1 = statement1.executeQuery();
                String name1, dept = new String(), contact= new String();
                int id1= 0,money=0;

                while (resultSet1.next())
                {
                    name1=resultSet1.getString("Username");
                    dept=resultSet1.getString("Department_Name");
                    id1=resultSet1.getInt("Student_ID");
                    contact=resultSet1.getString("Contact_Number");
                    money=resultSet1.getInt("Money");
                }
                document.add(new Paragraph("Club Name: "+uname+"\nDepartment: "+dept+"\nClub ID: "+id1+"\nContact No: "+contact+"\nAccount Balance: "+money+"\n"));
                document.add(new Paragraph("Player LineUp:\n\n\n\n"));





                PdfPTable table = new PdfPTable(3); // 3 columns
                table.setWidthPercentage(100); // Table width to 100% of page width
                table.getDefaultCell().setBackgroundColor(BaseColor.WHITE);

                table.addCell("ID");
                table.addCell("Name");
                table.addCell("Position");



                PreparedStatement statement = connection.prepareStatement("SELECT name, id, position FROM player WHERE club=? ORDER BY position ASC");
                statement.setString(1, uname);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String position = resultSet.getString("position");

                    table.addCell(String.valueOf(id));
                    table.addCell(name);
                    table.addCell(position);
                }
                document.add(table);
                document.close();

                System.out.println("done!");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("PDF saved to E://NahulTheJoker location!!!");
                alert.showAndWait();

            } catch (DocumentException | IOException | SQLException e) {
                e.printStackTrace();
            }
        }




    @FXML
    void hometosearch(MouseEvent event) throws IOException {
        swtch sw = new swtch();

        Parent root = FXMLLoader.load(HelloApplication.class.getResource("search.fxml"));
        sw.switch_scene(root,event);
    }

    @FXML
    void hometotransaction(MouseEvent event) throws IOException {

        swtch sw = new swtch();

        Parent root = FXMLLoader.load(HelloApplication.class.getResource("transaction.fxml"));
        sw.switch_scene(root,event);
    }

    public void table() {
        ObservableList<playerModel> students = FXCollections.observableArrayList();
        try {

            pst = connection.prepareStatement("select Serial,name,id,dept,position,value from player where club= ? ");
            pst.setString(1, uname);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                playerModel st = new playerModel();
                st.setName(rs.getString("name"));
                st.setId(rs.getString("id"));
                st.setDept(rs.getString("dept"));
                st.setPos(rs.getString("position"));
                st.setVal(rs.getString("value"));
                count++;
                students.add(st);


            }
            totalplayer.setText(String.valueOf(count));
            count=0;


            showTable.setItems(students);
            deptColumn.setCellValueFactory(f -> f.getValue().deptProperty());
            nameColumn.setCellValueFactory(f -> f.getValue().nameProperty());
            idColumn.setCellValueFactory(f -> f.getValue().idProperty());
            valColumn.setCellValueFactory(f -> f.getValue().valProperty());
            posColumn.setCellValueFactory((f -> f.getValue().posProperty()));


        } catch (SQLException ex) {
            Logger.getLogger(homecontrol.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw new RuntimeException(ex);

        }
        showTable.setRowFactory(tv -> {
            TableRow<playerModel> myRow = new TableRow<>();
            myRow.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = showTable.getSelectionModel().getSelectedIndex();


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
        FilteredList<playerModel> filteredList = new FilteredList<>(students, b -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(playerModel -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;

                }
                String searchKeyword = newValue.toLowerCase();
                if (playerModel.getName().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (playerModel.getId().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (playerModel.getDept().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (playerModel.getPos().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (playerModel.getVal().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<playerModel> sortedList = new SortedList<>(filteredList);
        //Bind sorted result with Table View
        sortedList.comparatorProperty().bind(showTable.comparatorProperty());

        //Apply filtered and sorted data to the table view
        showTable.setItems(sortedList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection connection = DbConnectionPlayer.getConnection();
        table();

        try {

            pst = connection.prepareStatement("select Money from teams where Username= ? ");
            pst.setString(1, uname);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int retrievedValue = rs.getInt("Money");
                totalMoney.setText(String.valueOf(retrievedValue));
                 // totalplayer.setText(String.valueOf(count));


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public TextField getPostxt() {
        return postxt;
    }

    public TextField getValtxt() {
        return valtxt;
    }
}

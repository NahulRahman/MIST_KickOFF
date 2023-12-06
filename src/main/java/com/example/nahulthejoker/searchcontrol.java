package com.example.nahulthejoker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.example.nahulthejoker.HelloController.uname;

public  class searchcontrol implements Initializable {

    public int gk=0;
    public int def=0;
    public int mid=0;
    public int att=0;

    @FXML
    void searchtohome(MouseEvent event) throws IOException {
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

    @FXML
    private PieChart pieChart;

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    /*@Override
    public void initialize(URL url, ResourceBundle rb) {
        // Connect to your MySQL database

    }*/

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Connect to your MySQL database
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_main", "root", "root");
            //String query = "SELECT position FROM player WHERE club=?"; // Modify this query according to your database schema
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT name,value FROM player WHERE club=?");
            preparedStatement.setString(1, uname);
            ResultSet resultSet = preparedStatement.executeQuery();

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

            // Retrieve data from the database and add it to the pie chart
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                double value = resultSet.getDouble("value");
                pieChartData.add(new PieChart.Data(name, value));
            }

            pieChart.setData(pieChartData);
            pieChart.setTitle("Value % of Players");

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_main", "root", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT name, value FROM player WHERE club=?");
            preparedStatement.setString(1, uname);
            ResultSet resultSet = preparedStatement.executeQuery();

            XYChart.Series<String, Number> series = new XYChart.Series<>();

            // Retrieve data from the database and add it to the line chart
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                double value = resultSet.getDouble("value");
                series.getData().add(new XYChart.Data<>(name, value));
            }

            lineChart.getData().add(series);
            lineChart.setTitle("Exact Value of Players");

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

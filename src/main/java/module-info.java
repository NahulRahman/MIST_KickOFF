module com.example.nahulthejoker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires itextpdf;


    opens com.example.nahulthejoker to javafx.fxml;
    exports com.example.nahulthejoker;
}
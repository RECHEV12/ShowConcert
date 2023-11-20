module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires json.simple;
    requires org.json;
    requires com.oracle.database.jdbc;


    opens com.example.main to javafx.fxml;
    exports com.example.main;
}
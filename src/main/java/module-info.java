module com.example.demostage {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires activation;
    requires javax.mail;
//    requires jython;
//    requires jython.standalone;


    opens com.example.demostage to javafx.fxml;
    opens com.example.model to javafx.fxml;
    exports com.example.demostage;
    exports com.example.model;

}
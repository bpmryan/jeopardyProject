module com.example.jeopardyapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.jeopardyapp to javafx.fxml;
    exports com.example.jeopardyapp;
}
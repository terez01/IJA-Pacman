module com.example.ijapacman {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ijapacman to javafx.fxml;
    exports com.example.ijapacman;
}
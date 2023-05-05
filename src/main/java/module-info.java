module ija.proj.pacman {
    requires javafx.controls;
    requires javafx.fxml;


    opens ija.proj.pacman to javafx.fxml;
    exports ija.proj.pacman;
}
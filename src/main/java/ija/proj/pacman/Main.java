package ija.proj.pacman;

import ija.proj.pacman.common.Maze;

import ija.proj.pacman.game.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane layout = new BorderPane();

        MenuBar menu = new MenuBar();
        Menu play = new Menu("Play");
        Menu map = new Menu("Maps");
        Menu log = new Menu("Playback");

        menu.getMenus().addAll(play, map, log);

        RadioMenuItem item1 = new RadioMenuItem("Map 1");
        RadioMenuItem item2 = new RadioMenuItem("Map 2");
        RadioMenuItem item3 = new RadioMenuItem("Map 3");

        ToggleGroup rGroup = new ToggleGroup();
        rGroup.getToggles().addAll(item1, item2, item3);

        map.getItems().addAll(item1, item2, item3);

        layout.setTop(menu);
        Scene scene = new Scene(layout, 320, 240);
        stage.setTitle("Pac-Man");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws FileNotFoundException {
        launch();
//        LoadMapTest test = new LoadMapTest();
//        test.LoadMapTest01();

        Logger log = new Logger();
        //load the map
        Maze maze = log.LoadMap();
        //first log
        log.LogMap(maze);
        //second log
        log.LogMap(maze);
    }
}
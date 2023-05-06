package ija.proj.pacman;

import ija.proj.pacman.common.Maze;
import ija.proj.pacman.game.CommonMaze;
import ija.proj.pacman.game.Logger;
import ija.proj.pacman.game.MazeConfigure;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application {
//    Maze maze;
//Logger log = new Logger();
    @Override
    public void start(Stage stage) throws IOException {
        GameView gameView = GameView.getInstance();



//        MazeConfigure cfg = new MazeConfigure();
//        CommonMaze maze = cfg.loadMazeFromFile("data/map03.txt");
//
//        /*first log*/
//        log.LogMap(maze);
//
//        maze.redraw();
//
        GameController controller = GameController.getInstance();
//
//        controller.setMaze(maze);

        controller.loadMaze("data/map01.txt");
        gameView.createGrid(controller.maze.numRows(), controller.maze.numCols());
        controller.maze.redraw();
        BorderPane layout = new BorderPane();

//        layout.setOnKeyPressed(controller);

        MenuBar menu = new MenuBar();
        Menu game = new Menu("Game");
        Menu map = new Menu("Maps");
        Menu log = new Menu("Playback");

        menu.getMenus().addAll(game, map, log);

        MenuItem playButton = new MenuItem("Play");

        game.getItems().add(playButton);

        RadioMenuItem item1 = new RadioMenuItem("Map 1");
        RadioMenuItem item2 = new RadioMenuItem("Map 2");
        RadioMenuItem item3 = new RadioMenuItem("Map 3");

        ToggleGroup rGroup = new ToggleGroup();
        rGroup.getToggles().addAll(item1, item2, item3);

        map.getItems().addAll(item1, item2, item3);

        item1.setOnAction(actionEvent -> {
            try{

                controller.loadMaze("data/map01.txt");

                //layout.setOnKeyPressed(controller);
                gameView.createGrid(controller.maze.numRows(), controller.maze.numCols());

                controller.maze.redraw();

            } catch (FileNotFoundException e){
                e.printStackTrace();
            }

        });

        item2.setOnAction(actionEvent -> {
            try{

                controller.loadMaze("data/map02.txt");

                //layout.setOnKeyPressed(controller);
                gameView.createGrid(controller.maze.numRows(), controller.maze.numCols());

                controller.maze.redraw();

            } catch (FileNotFoundException e){
                e.printStackTrace();
            }

        });

        item3.setOnAction(actionEvent -> {
            try{

                controller.loadMaze("data/map03.txt");

                //layout.setOnKeyPressed(controller);
                gameView.createGrid(controller.maze.numRows(), controller.maze.numCols());

                controller.maze.redraw();

            } catch (FileNotFoundException e){
                e.printStackTrace();
            }

        });



        playButton.setOnAction(actionEvent -> {
            layout.setOnKeyPressed(controller);
            controller.startTimer();
            System.out.print("DING\n");
        });

        layout.setTop(menu);
        layout.setCenter(gameView);
        Scene scene = new Scene(layout, controller.maze.numRows()*gameView.cellSize , controller.maze.numCols()*gameView.cellSize + 25);
        stage.setTitle("Pac-Man");
        stage.setScene(scene);
        stage.show();
        layout.requestFocus();

    }

    public static void main(String[] args) throws FileNotFoundException {
        launch();
    }

}
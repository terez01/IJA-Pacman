package ija.proj.pacman;

import ija.proj.pacman.common.Maze;
import ija.proj.pacman.game.CommonMaze;
import ija.proj.pacman.game.Logger;
import ija.proj.pacman.game.MazeConfigure;
import ija.proj.pacman.game.Playback;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GameView gameView = GameView.getInstance();
        GameController controller = GameController.getInstance();
        Playback playBack = new Playback(controller.log);

        controller.loadMaze("data/map01.txt");
        controller.pathToMazeFile = "data/map01.txt";
        gameView.createGrid(controller.maze.numRows(), controller.maze.numCols());
        controller.maze.redraw();
        BorderPane layout = new BorderPane();

        MenuBar menu = new MenuBar();
        Menu game = new Menu("Game");
        Menu map = new Menu("Maps");

        menu.getMenus().addAll(game, map);

        MenuItem playButton = new MenuItem("Play");
        MenuItem playbackButton = new MenuItem("Playback");

        game.getItems().addAll(playButton, playbackButton);

        RadioMenuItem item1 = new RadioMenuItem("Map 1");
        RadioMenuItem item2 = new RadioMenuItem("Map 2");
        RadioMenuItem item3 = new RadioMenuItem("Map 3");

        ToggleGroup rGroup = new ToggleGroup();
        rGroup.getToggles().addAll(item1, item2, item3);

        map.getItems().addAll(item1, item2, item3);

        Button startLogButton = new Button("start");
        Button endLogButton = new Button("end");
        Button nextLogButton = new Button("next");
        Button previousLogButton = new Button("previous");

        log.getItems().addAll(startLogButton, endLogButton, nextLogButton, previousLogButton);
        //observer to change labels
        Label labelPlay = new Label();
        Label labelLives = new Label("Lives: 3");
        labelLives.setFont(Font.font ("Verdana", 20));
        Label labelStatus = new Label();
        labelStatus.setFont(Font.font ("Verdana", 20));
        gameView.setLabelLives(labelLives);
        gameView.setLabelStatus(labelStatus);

        HBox bottom = new HBox();
        bottom.getChildren().addAll(labelLives, labelStatus);
        gameView.setLabelPlay(labelPlay);

        bottom.setAlignment(Pos.BOTTOM_CENTER);
        bottom.setSpacing(10);
        bottom.getChildren().addAll(startLogButton, endLogButton, nextLogButton, previousLogButton);

        //give the buttons to the game view
        gameView.setPlaybackButtons(startLogButton, endLogButton, nextLogButton, previousLogButton);
        //hide playback buttons
        gameView.hidePlaybackButtons();


        item1.setOnAction(actionEvent -> {
            try{
                changeMap("data/map01.txt");


            } catch (FileNotFoundException e){
                e.printStackTrace();
            }

        });

        item2.setOnAction(actionEvent -> {
            try{
                changeMap("data/map02.txt");
                controller.pathToMazeFile = "data/map02.txt";
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }

        });

        item3.setOnAction(actionEvent -> {
            try{
                changeMap("data/map03.txt");
                controller.pathToMazeFile = "data/map03.txt";

            } catch (FileNotFoundException e){
                e.printStackTrace();
            }

        });

        startLogButton.setOnAction(actionEvent -> {

            playBack.PlayFirst();

        });

        endLogButton.setOnAction(actionEvent -> {

            playBack.PlayLast();

        });

        nextLogButton.setOnAction(actionEvent -> {

            try {
                playBack.PlayNext();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        });

        previousLogButton.setOnAction(actionEvent -> {

            try {
                playBack.PlayPrevious();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        });

        playButton.setOnAction(actionEvent -> {
            if (controller.getMode() != GameController.Mode.Play) {
                controller.setMode(GameController.Mode.Play);
                layout.setOnKeyPressed(controller);
                controller.startTimer();
                System.out.print("[DEBUG] Started play\n");

                //changing the visibility of the buttons
                gameView.hidePlaybackButtons();

            }
        });

        playbackButton.setOnAction(actionEvent -> {
            if (controller.getMode() != GameController.Mode.Playback) {
                controller.stopTimer();
                controller.setMode(GameController.Mode.Playback);
                try {
                    playBack.init();

                    //changing the visibility of the buttons
                    gameView.showPlaybackButtons();

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                System.out.print("[DEBUG] Started Playback\n");
            }
        });

        layout.setTop(menu);
        layout.setCenter(gameView);
        layout.setBottom(bottom);
        Scene scene = new Scene(layout);
        stage.setTitle("Pac-Man");
        stage.setScene(scene);
        stage.show();
        layout.requestFocus();

    }

    void changeMap(String mapfile) throws FileNotFoundException {
        GameController.getInstance().setMode(GameController.Mode.Stopped);

        GameController.getInstance().log.reset();
        GameController.getInstance().loadMaze(mapfile);
        GameController.getInstance().init();

        //layout.setOnKeyPressed(controller);
        GameView.getInstance().createGrid(GameController.getInstance().maze.numRows(), GameController.getInstance().maze.numCols());

        GameController.getInstance().maze.redraw();
    }

    public static void main(String[] args) throws FileNotFoundException {
        launch();
    }

}
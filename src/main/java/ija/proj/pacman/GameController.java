package ija.proj.pacman;

import ija.proj.pacman.common.Field;
import ija.proj.pacman.game.CommonMaze;
import ija.proj.pacman.game.Logger;
import ija.proj.pacman.game.MazeConfigure;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameController implements EventHandler<KeyEvent> {
    enum Mode{
        Play, Playback, Stopped
    }

    //if the player wins or loses, game controller stops the timer
    boolean victory = false;
    boolean defeat = false;
    private static GameController instance;
    Logger log = new Logger();
    private int frames = 200;
    Timer timer;
    CommonMaze maze;
    MazeConfigure cfg = new MazeConfigure();
    Field.Direction direction = Field.Direction.R;

    Mode mode = Mode.Stopped;
    private GameController(){
    }
    //singleton
    public static GameController getInstance(){
        if (instance == null){
            instance = new GameController();
        }
        return instance;
    }
    public void startTimer() {
        this.timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        //checking if the game is still running on each tick
                        gameOverCheck();

                        maze.pacman.move(direction);
                        for(int i = 0; i < maze.ghostList.size(); i++){
                            maze.ghostList.get(i).move(maze.ghostList.get(i).lastDirection);
                            System.out.println("ghost moved");
                        }
                        maze.redraw();
                        log.LogMap(maze);
                    }
                });
            }
        };

        this.timer.schedule(timerTask, 10, frames);
    }
    public void stopTimer(){
        this.timer.cancel();
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public void setMaze(CommonMaze maze) {
        this.maze = maze;
    }

    public void loadMaze(String path) throws FileNotFoundException {
//        unloadMaze();

        maze = cfg.loadMazeFromFile(path);

        /*first log*/
        log.LogMap(maze);

//        maze.redraw();
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        switch (keyEvent.getCode()){
            case UP:
                if (maze.pacman.canMove(Field.Direction.U)){
                    direction = Field.Direction.U;
                    System.out.println("Pohol sa hore\n");
                }
                break;
            case DOWN:
                if (maze.pacman.canMove(Field.Direction.D)){
                    direction = Field.Direction.D;
                    System.out.println("Pohol sa dolu\n");
                }
                break;
            case LEFT:
                if (maze.pacman.canMove(Field.Direction.L)){
                    direction = Field.Direction.L;
                    System.out.println("Pohol sa dolava\n");
                }
                break;
            case RIGHT:
                if (maze.pacman.canMove(Field.Direction.R)){
                    direction = Field.Direction.R;
                    System.out.println("Pohol sa doprava\n");
                }
                break;
        }
    }
    public void gameOverCheck(){
        if (this.victory){
            stopTimer();
        }
        if (this. defeat){
            stopTimer();
        }
    }

    public void gameWon(){
        this.victory = true;
    }

    public void gameLost(){
        this.defeat = true;
    }

}
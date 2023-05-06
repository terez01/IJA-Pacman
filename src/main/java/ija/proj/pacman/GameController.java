package ija.proj.pacman;

import ija.proj.pacman.common.Field;
import ija.proj.pacman.game.CommonMaze;
import ija.proj.pacman.game.MazeConfigure;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Timer;
import java.util.TimerTask;

public class GameController implements EventHandler<KeyEvent> {
    private int frames = 200;
    Timer timer;

    /*TODO GET THE FUCKING MAZE IN HERE FROM MAIN SOMEHOW pls*/
    CommonMaze maze;
    MazeConfigure cfg = new MazeConfigure();
    public GameController(){
        this.startTimer();
    }
    private void startTimer() {
        this.timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        //call change direction
                    }
                });
            }
        };

        this.timer.schedule(timerTask, 0, frames);
    }

    public void setMaze(CommonMaze maze) {
        this.maze = maze;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        switch (keyEvent.getCode()){
            case UP:
                if (maze.pacman.move(Field.Direction.U)){
                    System.out.println("Pohol sa hore\n");
                }
                break;
            case DOWN:
                if (maze.pacman.move(Field.Direction.D)){
                    System.out.println("Pohol sa dolu\n");
                }
                break;
            case LEFT:
                if (maze.pacman.move(Field.Direction.L)){
                    System.out.println("Pohol sa dolava\n");
                }
                break;
            case RIGHT:
                if (maze.pacman.move(Field.Direction.R)){
                    System.out.println("Pohol sa doprava\n");
                }
                break;
        }
        maze.redraw();
        //log
        //notify log
    }
}
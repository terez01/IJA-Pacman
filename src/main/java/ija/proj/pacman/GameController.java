package ija.proj.pacman;

import ija.proj.pacman.game.CommonMaze;
import ija.proj.pacman.game.MazeConfigure;
import javafx.application.Platform;
import java.util.Timer;
import java.util.TimerTask;

public class GameController{
    private int frames = 200;
    Timer timer;
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



}
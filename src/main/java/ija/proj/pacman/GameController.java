/**
 * IJA - PACMAN
 * author(s): xmager00, xhusar11
 * GAME CONTROLLER CLASS
 */
package ija.proj.pacman;

import ija.proj.pacman.common.Field;
import ija.proj.pacman.game.*;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.io.FileNotFoundException;
import java.util.*;

public class GameController extends Observable implements EventHandler<KeyEvent>{
    enum Mode{
        Play, Playback, Stopped
    }

    //if the player wins or loses, game controller stops the timer
    public boolean victory = false;
    public boolean defeat = false;
    private static GameController instance;
    Logger log = new Logger();
    private int frames = 250;
    Timer timer;
    public CommonMaze maze;
    String pathToMazeFile;
    MazeConfigure cfg = new MazeConfigure();
    Field.Direction direction = Field.Direction.R;
    Mode mode = Mode.Stopped;

    private GameController(){
        this.addObserver(GameView.getInstance());
    }

    public void init(){
        victory = false;
        defeat = false;
        mode = Mode.Stopped;
        setChanged();
        notifyObservers();
    }
    //singleton
    public static GameController getInstance(){
        if (instance == null){
            instance = new GameController();
        }
        return instance;
    }
    public void startTimer() {
        maze.pacman.stepCnt = 0;
        this.timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        //checking if the game is still running on each tick
                        gameOverCheck();

                        if(maze.pacman.move(direction)){
                            maze.pacman.stepCnt++;
                            setChanged();
                            notifyObservers();
                        }
                        for(int i = 0; i < maze.ghostList.size(); i++){
                            maze.ghostList.get(i).move(maze.ghostList.get(i).lastDirection);
                        }
                        maze.redraw();
                        log.LogMap(maze);
                        hurtCheck();
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
                }
                break;
            case DOWN:
                if (maze.pacman.canMove(Field.Direction.D)){
                    direction = Field.Direction.D;
                }
                break;
            case LEFT:
                if (maze.pacman.canMove(Field.Direction.L)){
                    direction = Field.Direction.L;
                }
                break;
            case RIGHT:
                if (maze.pacman.canMove(Field.Direction.R)){
                    direction = Field.Direction.R;
                }
                break;
            case SPACE:
            {
                if(mode != Mode.Play && mode != Mode.Playback){
                    log.reset();
                    try {
                        loadMaze(pathToMazeFile);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    init();
                    GameView.getInstance().createGrid(maze.numRows(), maze.numCols());
                    maze.redraw();
                    setMode(Mode.Play);
                    startTimer();
                }
                break;
            }
        }
    }

    public void hurtCheck(){
        int pacRow = maze.pacman.getRow();
        int pacCol = maze.pacman.getCol();

        for (int i = 0; i < maze.ghostList.size(); i++){
            int ghostRow = maze.ghostList.get(i).getRow();
            int ghostCol = maze.ghostList.get(i).getCol();
            //if both pacman and ghost are on the same Field, hurt the pacman
            if (ghostRow == pacRow && ghostCol == pacCol){
                maze.pacman.hurt();
            }
            Field pacField = maze.getField(pacRow,pacCol);
            Field ghostField = maze.getField(ghostRow,ghostCol);
            //if pacman is about to step on a field with ghost
            if (pacField.nextField(maze.pacman.lastDirection) == ghostField){

                GhostObject tmpGhost = maze.ghostList.get(i);

                //and at the same time ghost is about to step on a field with pacman
                if (ghostField.nextField(tmpGhost.lastDirection) == pacField){
                    maze.pacman.hurt();
                }
            }

        }
    }
 public void gameOverCheck(){
        if (this.victory){
            stopTimer();
            notifyObservers();
        }
        if (this. defeat){
            stopTimer();
            notifyObservers();
        }

    }

    public void gameWon(){
        this.victory = true;
        setMode(Mode.Stopped);

    }

    public void gameLost(){
        this.defeat = true;
        setMode(Mode.Stopped);

    }

}
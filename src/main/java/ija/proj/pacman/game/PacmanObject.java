/**
 * IJA - PACMAN
 * author(s): xmager00, xhusar11
 * PACMAN OBJECT CLASS
 */
package ija.proj.pacman.game;

import ija.proj.pacman.GameController;
import ija.proj.pacman.GameView;
import ija.proj.pacman.common.Field;
import ija.proj.pacman.common.Maze;
import ija.proj.pacman.common.MazeObject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Observable;

public class PacmanObject extends Observable implements MazeObject {
    public int lifeCnt;
    public int stepCnt = 0;
    int row;
    int col;
    Maze maze = null;
    Image pacmanImage;
    public Field.Direction lastDirection = Field.Direction.D;

    boolean hasKey = false;
    public PacmanObject(Maze maze, int row, int col){
        this.lifeCnt = 3;
        this.maze = maze;
        this.col = col;
        this.row = row;
        this.pacmanImage = new Image("file:lib/img/right.png");
        this.addObserver(GameView.getInstance());
    }
    @Override
    public boolean canMove(Field.Direction dir) {
        Field tmpfield = maze.getField(row, col).nextField(dir);
        if (tmpfield != null){
            return tmpfield.canMove(maze.getField(row, col).get());
        } else {
            return false;
        }
    }
    @Override
    public boolean move(Field.Direction dir) {
        if (canMove(dir)) {
            Field nextField = maze.getField(row,col).nextField(dir);
            //Key grabbing logic
            if (nextField.get() instanceof KeyObject){
                hasKey = true;
                //remove the grabbed key
                nextField.remove(nextField.get());
            }
            nextField.put(this);
            maze.getField(row,col).remove(this);
            switch (dir) {
                case D -> {
                    row = row + 1;
                    this.pacmanImage = new Image("file:lib/img/down.png");
                }
                case L -> {
                    col = col - 1;
                    this.pacmanImage = new Image("file:lib/img/left.png");
                }
                case U -> {
                    row = row - 1;
                    this.pacmanImage = new Image("file:lib/img/up.png");
                }
                case R -> {
                    col = col + 1;
                    this.pacmanImage = new Image("file:lib/img/right.png");
                }
            }
            this.lastDirection = dir;
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean isPacman() {
        return true;
    }

    public char getAscii(){
        return 'S';
    }
    public void draw(){
        ImageView imageView = GameView.getInstance().getImageView(row, col);
        imageView.setImage(this.pacmanImage);
    }

    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    public void hurt(){
        lifeCnt--;
        if (lifeCnt <= 0){
            //Game over - stop the timer

            GameController controller = GameController.getInstance();
            controller.gameLost();
        }
        setChanged();
        notifyObservers();
    }
}

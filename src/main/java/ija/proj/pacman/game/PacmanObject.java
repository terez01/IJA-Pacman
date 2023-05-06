/**
 * IJA - PACMAN
 * author(s): xmager00, xhusar11
 */
package ija.proj.pacman.game;

import ija.proj.pacman.GameView;
import ija.proj.pacman.common.Field;
import ija.proj.pacman.common.Maze;
import ija.proj.pacman.common.MazeObject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PacmanObject implements MazeObject {
    int row;
    int col;
    Maze maze = null;
    Image pacmanImage;
    boolean hasKey = false;
    public PacmanObject(Maze maze, int row, int col){
        this.maze = maze;
        this.col = col;
        this.row = row;
        this.pacmanImage = new Image("file:lib/img/right.png");
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
                System.out.println("Pacman took the key");
                nextField.remove(nextField.get());
            }
            nextField.put(this);
            maze.getField(row,col).remove(this);
            switch (dir){
                case D:
                    row = row+1;
                    this.pacmanImage = new Image("file:lib/img/down.png");
                    break;
                case L:
                    col = col-1;
                    this.pacmanImage = new Image("file:lib/img/left.png");
                    break;
                case U:
                    row = row-1;
                    this.pacmanImage = new Image("file:lib/img/up.png");
                    break;
                case R:
                    col = col+1;
                    this.pacmanImage = new Image("file:lib/img/right.png");
                    break;
            }
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
}

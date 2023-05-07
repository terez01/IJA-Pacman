/**
 * IJA - PACMAN
 * author(s): xmager00, xhusar11
 * GHOST OBJECT CLASS
 */
package ija.proj.pacman.game;

import ija.proj.pacman.GameView;
import ija.proj.pacman.common.Field;
import ija.proj.pacman.common.Maze;
import ija.proj.pacman.common.MazeObject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class GhostObject implements MazeObject{
    int row;
    int col;
    Maze maze = null;
    Image ghostImage;
    public Field.Direction lastDirection = Field.Direction.D;
    public GhostObject(Maze maze, int row, int col){
        this.maze = maze;
        this.col = col;
        this.row = row;
    }

    @Override
    public boolean canMove(Field.Direction dir) {
        Field tmpfield = maze.getField(row, col).nextField(dir);
        if (tmpfield != null){
            if(tmpfield.get() instanceof GhostObject){
                return true;
            }else{
                return tmpfield.canMove(maze.getField(row, col).get());
            }

        } else {
            return false;
        }
    }
    private Field.Direction ghostNewDirection(Field.Direction dir){
        Random generator = new Random();
        Field.Direction newDirection = dir;

        boolean right = canMove(Field.Direction.R);
        boolean left = canMove(Field.Direction.L);
        boolean up = canMove(Field.Direction.U);
        boolean down = canMove(Field.Direction.D);
        boolean decided = false;

        if(!left && !right && !up){
            newDirection = Field.Direction.D;
        }else if(!left && !right && !down){
            newDirection = Field.Direction.U;
        }else if(!left && !up && !down){
            newDirection = Field.Direction.R;
        }else if(!right && !up && !down){
            newDirection = Field.Direction.L;
        }
        else if((!left && !right) || (!up && !down)) {
            newDirection = dir;
        }else{
            switch(this.lastDirection){
                case L -> right = false;
                case D -> up = false;
                case U -> down = false;
                case R -> left = false;
            }
            while (!decided) {
                float random = generator.nextFloat();
                if (left && random < 0.25){
                    newDirection = Field.Direction.L;
                    decided = true;
                }
                else if (right && random < 0.50){
                    newDirection = Field.Direction.R;
                    decided = true;
                }
                else if (up && random < 0.75){
                    newDirection = Field.Direction.U;
                    decided = true;
                }
                else if (down && random < 1.0){
                    newDirection = Field.Direction.D;
                    decided = true;
                }
            }
        }
        this.lastDirection = newDirection;
        return newDirection;
    }
    @Override
    public boolean move(Field.Direction dir) {
        Field.Direction newDirection = ghostNewDirection(dir);
        if (canMove(newDirection)) {
            Field nextField = maze.getField(row,col).nextField(newDirection);
            nextField.put(this);
            maze.getField(row,col).remove(this);
            switch (newDirection){
                case D:
                    row = row+1;
                    break;
                case L:
                    col = col-1;
                    break;
                case U:
                    row = row-1;
                    break;
                case R:
                    col = col+1;
                    break;
            }
            this.lastDirection = newDirection;
            return true;
        }else {
            return false;
        }

    }

    @Override
    public boolean isPacman() {
        return false;
    }

    public char getAscii(){
        return 'G';
    }
    public void draw(){
        ImageView imageView = GameView.getInstance().getImageView(row, col);
        imageView.setImage(this.ghostImage);
    }

    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
}

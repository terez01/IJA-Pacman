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

public class PathField implements Field {
    Image pathImage;
    MazeObject mazeObject = null;
    Maze maze = null;
    int row;
    int col;

    public PathField(int row, int col){
        this.row = row;
        this.col = col;
        this.pathImage = new Image("file:lib/img/path.png");

    }
    @Override
    public boolean canMove(MazeObject movingObject) {
        return true;
    }

    /*TODO add instances key and ghost*/
    @Override
    public MazeObject get() {
//        if (mazeObject instanceof PacmanObject) {
//            return (PacmanObject) mazeObject;
//        }else{
//            return null;
//        }
        return mazeObject;
    }

    @Override
    public boolean isEmpty() {
        return (mazeObject == null);
    }

    @Override
    public Field nextField(Direction dir) {
        int nextrow = row;
        int nextcol = col;

        switch (dir){
            case D:
                nextrow = row+1;
                break;
            case L:
                nextcol = col-1;
                break;
            case U:
                nextrow = row-1;
                break;
            case R:
                nextcol = col+1;
                break;
        }

        return maze.getField(nextrow, nextcol);
    }

    @Override
    public boolean put(MazeObject object) {
        if(isEmpty()) {
            mazeObject = object;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean remove(MazeObject object) {
        if(!isEmpty() && mazeObject.equals(object)) {
            mazeObject = null;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PathField){
            if (this.row == ((PathField) obj).row && this.col == ((PathField) obj).col){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public char getAscii(){
        if (isEmpty()){
            return ('.');
        } else {
            return get().getAscii();
        }

    }
    public void draw(){
        if (isEmpty()){
            ImageView imageView = GameView.getInstance().getImageView(row, col);
            imageView.setImage(this.pathImage);
        }else{
            get().draw();
        }

    }
}

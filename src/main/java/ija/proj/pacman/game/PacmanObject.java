/**
 * IJA - PACMAN
 * author(s): xmager00, xhusar11
 */
package ija.proj.pacman.game;

import ija.proj.pacman.common.Field;
import ija.proj.pacman.common.Maze;
import ija.proj.pacman.common.MazeObject;

public class PacmanObject implements MazeObject {
    int row;
    int col;
    Maze maze = null;
    public PacmanObject(Maze maze, int row, int col){
        this.maze = maze;
        this.col = col;
        this.row = row;
    }
    @Override
    public boolean canMove(Field.Direction dir) {
        Field tmpfield = maze.getField(row, col).nextField(dir);
        if (tmpfield != null){
            return tmpfield.canMove();
        } else {
            return false;
        }
    }
    @Override
    public boolean move(Field.Direction dir) {
        if (canMove(dir)) {
            maze.getField(row,col).nextField(dir).put(this);
            maze.getField(row,col).remove(this);
            switch (dir){
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
            return true;
        }else {
            return false;
        }
    }
    public char getAscii(){
        return 'S';
    }
}

/**
 * IJA - PACMAN
 * author(s): xmager00, xhusar11
 */
package ija.proj.pacman.game;

import ija.proj.pacman.common.Field;
import ija.proj.pacman.common.Maze;
import ija.proj.pacman.common.MazeObject;

public class TargetField implements Field {
    /*TODO create logic for the TARGET FIELD*/
    MazeObject mazeObject = null;
    Maze maze=null;
    int row;
    int col;
    public TargetField(int row, int col){
        this.row = row;
        this.col = col;
    }

    @Override
    public Field nextField(Direction dir) {
        return null;
    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public MazeObject get() {
        return null;
    }

    @Override
    public boolean put(MazeObject object) {
        return false;
    }

    @Override
    public boolean remove(MazeObject object) {
        return false;
    }

    @Override
    public void setMaze(Maze maze) {
            this.maze = maze;
    }
    public char getAscii(){
        return ('T');
    }
    public void draw(){

    }
}

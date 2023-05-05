/**
 * IJA - PACMAN
 * author(s): xmager00, xhusar11
 */
package ija.proj.pacman.game;

import ija.proj.pacman.common.Field;
import ija.proj.pacman.common.Maze;
import ija.proj.pacman.common.MazeObject;

public class WallField implements Field{
    Maze maze=null;
    int row;
    int col;
    public WallField(int row, int col){
        this.row = row;
        this.col = col;
    }
    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public MazeObject get() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Field nextField(Direction dirs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean put(MazeObject object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(MazeObject object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WallField){
            if (this.row == ((WallField) obj).row && this.col == ((WallField) obj).col){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    public char getAscii(){
        return ('X');
    }
}

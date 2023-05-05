/**
 * IJA - PACMAN
 * author(s): xmager00, xhusar11
 */
package ija.proj.pacman.game;

import ija.proj.pacman.common.Field;
import ija.proj.pacman.common.Maze;
import ija.proj.pacman.common.MazeObject;

public class GhostObject implements MazeObject {
    int row;
    int col;
    Maze maze = null;
    public GhostObject(Maze maze, int row, int col){
        this.maze = maze;
        this.col = col;
        this.row = row;
    }

    @Override
    public boolean canMove(Field.Direction dir) {
        return false;
    }

    @Override
    public boolean move(Field.Direction dir) {
        return false;
    }
    public char getAscii(){
        return 'G';
    }
}

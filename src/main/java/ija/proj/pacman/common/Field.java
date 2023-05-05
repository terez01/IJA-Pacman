/**
 * IJA - PACMAN
 * author(s): xmager00, xhusar11
 */
package ija.proj.pacman.common;

public interface Field {
    enum Direction{
        U, L, D, R
    }
    Field nextField(Field.Direction dir);
    boolean canMove();
    boolean isEmpty();
    MazeObject get();
    boolean put(MazeObject object);
    boolean remove(MazeObject object);
    void setMaze(Maze maze);
    char getAscii();
}

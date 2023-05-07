/**
 * IJA - PACMAN
 * author(s): xmager00, xhusar11
 * MAZEOBJECT - INTERFACE
 */
package ija.proj.pacman.common;

public interface MazeObject{
    boolean canMove(Field.Direction dir);
    boolean move(Field.Direction dir);
    boolean isPacman();
    char getAscii();
    void draw();
}

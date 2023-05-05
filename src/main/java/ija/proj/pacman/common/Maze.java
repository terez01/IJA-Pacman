/**
 * IJA - PACMAN
 * author(s): xmager00, xhusar11
 */
package ija.proj.pacman.common;

public interface Maze {
    Field getField(int row, int col);
    int numCols();
    int numRows();
}

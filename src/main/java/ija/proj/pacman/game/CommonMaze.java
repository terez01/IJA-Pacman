/**
 * IJA - PACMAN
 * author(s): xmager00, xhusar11
 * COMMON MAZE CLASS
 */
package ija.proj.pacman.game;

import ija.proj.pacman.common.Field;
import ija.proj.pacman.common.Maze;
import ija.proj.pacman.common.MazeObject;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

public class CommonMaze implements Maze {
    LinkedList<Field> fieldList;
    List<MazeObject> mazeObjectList;
    public List<GhostObject> ghostList = new ArrayList<>();

    public PacmanObject pacman;
    KeyObject key;
    private int rows = 0;
    private int cols = 0;

    public CommonMaze(int rows, int cols, List<Character> charList){
        this.fieldList = new LinkedList<Field>();

        for (int i = 0; i < charList.size(); i++){
            if (charList.get(i) == 'X'){
                Field newField = new WallField(i/cols, i%cols);
                this.fieldList.add(newField);
                newField.setMaze(this);
            } else if (charList.get(i) == '.') {
                Field newField = new PathField(i/cols, i%cols);
                this.fieldList.add(newField);
                newField.setMaze(this);
            } else if (charList.get(i) == 'S'){
                Field newField = new PathField(i/cols, i%cols);
                this.fieldList.add(newField);
                pacman = new PacmanObject(this, i/cols, i%cols);
                newField.put(pacman);
                newField.setMaze(this);
            } else if (charList.get(i) == 'T'){
                Field newField = new TargetField(i/cols, i%cols);
                this.fieldList.add(newField);
                newField.setMaze(this);
            } else if (charList.get(i) == 'K'){
                Field newField = new PathField(i/cols, i%cols);
                this.fieldList.add(newField);
                key = new KeyObject(this, i/cols, i%cols);
                newField.put(key);
                newField.setMaze(this);
            } else if (charList.get(i) == 'G'){
                Field newField = new PathField(i/cols, i%cols);
                this.fieldList.add(newField);
                GhostObject newGhost = new GhostObject(this, i/cols, i%cols);
                newField.put(newGhost);
                ghostList.add(newGhost);
                newField.setMaze(this);
            }
        }
        for(int i = 0; i < ghostList.size(); i++){
            String path = "file:lib/img/ghost" + String.valueOf(i) + ".png";
            ghostList.get(i).ghostImage = new Image(path);
        }
        this.rows = rows;
        this.cols = cols;
    }
    public Field getField(int row, int col){
        if (row>=0 && row<rows && col>=0 && col<cols){
            return fieldList.get(row*cols + col);
        }else {
            return null;
        }
    }
    @Override
    public int numCols() {
        return cols;
    }
    @Override
    public int numRows() {
        return rows;
    }
    public void printMaze(){
        for (int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                Field tmpField = getField(i,j);
                System.out.print(tmpField.getAscii());
            }
            System.out.print('\n');
        }
    }
    public void redraw(){
        for (int i=0; i<rows; i++){
            for(int j=0; j<cols; j++) {
                Field tmpField = getField(i, j);
                tmpField.draw();
            }
        }
    }
}

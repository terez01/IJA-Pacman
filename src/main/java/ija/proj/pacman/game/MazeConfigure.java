/**
 * IJA - PACMAN
 * author(s): xmager00, xhusar11
 */
package ija.proj.pacman.game;

import ija.proj.pacman.common.Maze;
import ija.proj.pacman.game.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class MazeConfigure {
    List<Character> charList;
    int rows = 0;
    int cols = 0;   //!probably not gonna be used
    int expectedRows;
    int expectedCols;
    boolean mazeOk=true;
    public MazeConfigure() {
        charList = new LinkedList<Character>();
    }
    Logger log = new Logger();

    /**
     * Function creates and returns the maze, if the maze was correctly given, otherwise return null
      * @return
     */
    public Maze createMaze(){
        if (mazeOk){
            return new CommonMaze(rows+2, expectedCols+2, charList);
        } else {
            return null;
        }
    }

    /**
     * Function reads one line, controls the validity (The Maze must be Rectangular), and adds the left & right padding to the Maze. If the input was valid, returns true, false otherwise.
     * @param line
     * @return
     */
    public boolean processLine(String line){
        rows++;

        int currentCols = 0;

        charList.add('X');  //left padding

        for (int i=0; i < line.length(); i++){
            currentCols++;
            //controls the validity of the input characters (X,.,S,T,K,G)
            if(line.charAt(i) != 'X' && line.charAt(i) != '.' && line.charAt(i) != 'S' && line.charAt(i) != 'T' && line.charAt(i) != 'K' && line.charAt(i) != 'G' ){
                mazeOk = false;
                return false;
            }
            charList.add(line.charAt(i));
        }

        charList.add('X');  //right padding

        //controls if the number of columns was as expected
        if (currentCols != expectedCols){
            mazeOk = false;
            return false;
        } else {
            return true;
        }
    }

    /**
     * Function prepares to read the lines by getting the number of rows and columns first. Then adds the top padding of the Maze.
     * @param rows
     * @param cols
     */
    public void startReading(int rows, int cols){
        expectedRows = rows;
        expectedCols = cols;

        //top padding (walls), expCols+2 because of the right and left padding
        for (int i=0; i<expectedCols+2; i++){
            charList.add('X');
        }
    }

    /**
     * Function adds the bottom padding of the Maze. Then controls if the number of rows was as expected. If yes, returns true, false otherwise.
     * @return
     */
    public boolean stopReading(){
        //bottom padding (walls), expCols+2 because of the right and left padding
        for (int i=0; i<expectedCols+2; i++){
            charList.add('X');
        }

        if (expectedRows != rows){
            mazeOk = false;
            return false;
        } else {
            return true;
        }
    }
    /**
     * Function loads the maze, deletes previous log file and prints the dimensions of the maze to the log file
     */
    public CommonMaze loadMazeFromFile(String filePath) throws FileNotFoundException {

        log.LogDelete();    //deletes the previous log

        File file = new File(filePath);   //path to the map

        Scanner scan = new Scanner(file);

        String fileContent = "";    //string storing the file

        MazeConfigure cfg = new MazeConfigure();
        String line = scan.nextLine();
        String[] parts = line.split(" ");
        int num1 = Integer.parseInt(parts[0]);
        int num2 = Integer.parseInt(parts[1]);

        log.LogDimensions(num1,num2);

        cfg.startReading(num1, num2);
        while(scan.hasNextLine()){
            cfg.processLine(scan.nextLine());
        }

        cfg.stopReading();

        Maze tmpmaze = cfg.createMaze();
        CommonMaze maze = (CommonMaze) tmpmaze;
        return maze;
    }
}

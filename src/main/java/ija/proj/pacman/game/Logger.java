/**
* IJA - PACMAN
* author(s): xmager00, xhusar11
*/
package ija.proj.pacman.game;

import ija.proj.pacman.common.Maze;
import ija.proj.pacman.game.CommonMaze;
import ija.proj.pacman.game.MazeConfigure;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Logger {

    public void reset(){
        iterationNum = 0;
    }
//    Maze logMaze = null;
    int iterationNum = 0;

    /**
     * Function logs the maze and its iteration number to the log.txt file
     * @param maze
     */
    public void LogMap(Maze maze){
        iterationNum++;

        String iterNumStr = Integer.toString(iterationNum); //integer to string for logging

        String filename = "data/log.txt";

        //print iteration number and new line
        try{
            FileWriter fw = new FileWriter(filename, true); //true flag will append the new data
            fw.write(iterNumStr + "\n"); //string to be appended
            fw.close();
        }
        catch (IOException ioe){
            System.err.println("IOException:" + ioe.getMessage());
        }

        //print the maze line by line to the log, +-1 because I do not want to log the padding
        for (int i = 1; i < maze.numRows() - 1; i++){
            for (int j = 1; j < maze.numCols() - 1; j++){
                Character tmpField = maze.getField(i,j).getAscii();
                //print the Ascii symbol of the field
                try{
                    FileWriter fw = new FileWriter(filename, true); //true flag will append the new data
                    fw.write(tmpField); //string to be appended
                    fw.close();
                }
                catch (IOException ioe){
                    System.err.println("IOException:" + ioe.getMessage());
                }
            }
            //print the newline when you printed all the columns
            try{
                FileWriter fw = new FileWriter(filename, true); //true flag will append the new data
                fw.write("\n");
                fw.close();
            }
            catch (IOException ioe){
                System.err.println("IOException:" + ioe.getMessage());
            }
        }

    }

    /**
     * Function deletes the log
     */
    public void LogDelete() {
        String filename = "data/log.txt";

        //rewrite the log to an empty line
        try{
            FileWriter fw = new FileWriter(filename); //true flag will append the new data
            fw.write(""); //writes an empty string
            fw.close();
        }
        catch (IOException ioe){
            System.err.println("IOException:" + ioe.getMessage());
        }
    }

    /**
     * Function prints the dimensions of the map to the top of the log
     */
    public void LogDimensions(int numRows, int numCols){
        String filename = "data/log.txt";

        String numRowsString = Integer.toString(numRows);
        String numColsString = Integer.toString(numCols);

        //write the dimensions to the log
        try{
            FileWriter fw = new FileWriter(filename, true); //true flag will append the new data
            fw.write(numRowsString + " " + numColsString + "\n"); //writes the dimensions along with new line
            fw.close();
        }
        catch (IOException ioe){
            System.err.println("IOException:" + ioe.getMessage());
        }
    }

    /**
     * Function finds the desired Maze by the log number and returns it, if the log number is not in the file, return null
     */
    public CommonMaze LoadLogMap(int iterNum) throws FileNotFoundException{

        File file = new File("data/log.txt");   //path to the logged iterations of the map

        Scanner scan = new Scanner(file);
        String readLine = scan.nextLine();  //reads the first line (the dimensions of the Mazes)

        String[] parts = readLine.split(" ");
        int rowDimension = Integer.parseInt(parts[0]);
        int colDimension = Integer.parseInt(parts[1]);

        readLine = scan.nextLine();
        while(readLine != null){

            String iterNumStr = Integer.toString(iterNum);
            String iterNumStr2 = Integer.toString(iterNum+1);

            if (Objects.equals(readLine, iterNumStr)){
                //SCAN THE MAZE
                MazeConfigure logCfg = new MazeConfigure();

                logCfg.startReading(rowDimension, colDimension);
                while(scan.hasNextLine()){
                    String strLine = scan.nextLine();

                    if (!iterNumStr2.equals(strLine)){
                        logCfg.processLine(strLine);
                    } else {
                        break;
                    }
                }

                logCfg.stopReading();

                Maze tmpmaze = logCfg.createMaze();
                CommonMaze maze = (CommonMaze) tmpmaze;

                return maze;
            }
            if (scan.hasNextLine()) {
                readLine = scan.nextLine();
            }
        }
        return null;
    }

    /**
     * Function gets the last iteration number, function returns zero if there is none
     * @return
     * @throws FileNotFoundException
     */
    public int LogGetLastIterNum() throws FileNotFoundException {
        int lastIterNum = 0;

        File file = new File("data/log.txt");   //path to the logged iterations of the map

        Scanner scan = new Scanner(file);
        if (!scan.hasNextLine()){
            return 0;
        } else {
            String readLine = scan.nextLine();  //reads the first line (the dimensions of the Mazes)

            String[] parts = readLine.split(" ");
            int rowDimension = Integer.parseInt(parts[0]);

            while(scan.hasNextLine()){
                lastIterNum = Integer.parseInt(scan.nextLine());
                for (int i = 0; i < rowDimension; i++){
                    scan.nextLine();
                }
            }
            return lastIterNum;
        }

    }

}

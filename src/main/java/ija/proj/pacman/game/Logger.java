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
import java.util.Scanner;

public class Logger {
    Maze logMaze = null;
    int iterationNum = 0;
    public void LoadMap() throws FileNotFoundException {
        File file = new File("data/map01.txt");   //path to the map

        Scanner scan = new Scanner(file);

        String fileContent = "";    //string storing the file

        MazeConfigure cfg = new MazeConfigure();
        String line = scan.nextLine();
        String[] parts = line.split(" ");
        int num1 = Integer.parseInt(parts[0]);
        int num2 = Integer.parseInt(parts[1]);

        cfg.startReading(num1, num2);
        while(scan.hasNextLine()){
            cfg.processLine(scan.nextLine());
        }

        cfg.stopReading();

        Maze tmpmaze = cfg.createMaze();
        CommonMaze maze = (CommonMaze) tmpmaze;
        maze.printMaze();

        // save the maze to logMaze
        logMaze = tmpmaze;

        //TODO rewrite the log to an empty file
    }

    public void LogMap(){
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

        //print maze line by line




    }

}

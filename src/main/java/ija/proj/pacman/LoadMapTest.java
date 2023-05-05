/**
 * IJA - PACMAN
 * author(s): xmager00, xhusar11
 * TESTING CLASS
 */
package ija.proj.pacman;

import ija.proj.pacman.common.Maze;
import ija.proj.pacman.game.CommonMaze;
import ija.proj.pacman.game.MazeConfigure;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadMapTest {
    public void LoadMapTest01() throws FileNotFoundException {
        File file = new File("C:/School/git/3bit/summer2023/ija/pacman/data/map01.txt");   //path to the map

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
    }
}

package ija.proj.pacman.game;

import java.io.FileNotFoundException;

public class Playback {
    int currentIter;
    int lastIter;
    Logger logger;
    public Playback(Logger logger){
        this.logger = logger;
    }

    public void init() throws FileNotFoundException {
        this.currentIter = 1;
        this.lastIter = logger.LogGetLastIterNum();

    }

    public void PlayFirst(){
        try{
            CommonMaze maze = logger.LoadLogMap(1);
            this.currentIter = 1;

            maze.redraw();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void PlayLast(){
        try{
            this.currentIter = lastIter;

            CommonMaze maze = logger.LoadLogMap(lastIter);

            maze.redraw();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void PlayNext() throws FileNotFoundException {
        if (currentIter != lastIter){
            currentIter++;

            CommonMaze maze = logger.LoadLogMap(currentIter);

            maze.redraw();
        }

    }

    public void PlayPrevious() throws FileNotFoundException {
        if (currentIter != 1){
            currentIter--;

            CommonMaze maze = logger.LoadLogMap(currentIter);

            maze.redraw();
        }
    }

}

/**
 * IJA - PACMAN
 * author(s): xmager00, xhusar11
 */
package ija.proj.pacman.game;

import ija.proj.pacman.GameController;
import ija.proj.pacman.GameView;
import ija.proj.pacman.common.Field;
import ija.proj.pacman.common.Maze;
import ija.proj.pacman.common.MazeObject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TargetField implements Field {
    /*TODO create logic for the TARGET FIELD*/
    MazeObject mazeObject = null;
    Maze maze=null;
    Image targetImage;
    int row;
    int col;
    public TargetField(int row, int col){
        this.row = row;
        this.col = col;
        this.targetImage = new Image("file:lib/img/chest.png");
    }

    @Override
    public Field nextField(Direction dir) {
        return null;
    }

    @Override
    public boolean canMove(MazeObject movingObject) {
        if (movingObject instanceof PacmanObject){
            if (((PacmanObject) movingObject).hasKey){
                System.out.println("pohol sa na target");
                //STOPS THE TIMER - THE PLAYER WON THIS WAY
                GameController controller = GameController.getInstance();
                controller.stopTimer();
                return true;
            }
            System.out.println("instancia pacmana");
        }
        System.out.println("nevie sa pohnut");
        return false;

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public MazeObject get() {
        return null;
    }

    @Override
    public boolean put(MazeObject object) {
        return false;
    }

    @Override
    public boolean remove(MazeObject object) {
        return false;
    }

    @Override
    public void setMaze(Maze maze) {
            this.maze = maze;
    }
    public char getAscii(){
        return ('T');
    }
    public void draw(){
        ImageView imageView = GameView.getInstance().getImageView(row, col);
        imageView.setImage(this.targetImage);
    }
}

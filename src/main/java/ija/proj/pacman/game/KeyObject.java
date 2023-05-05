/**
 * IJA - PACMAN
 * author(s): xmager00, xhusar11
 */
package ija.proj.pacman.game;

import ija.proj.pacman.GameView;
import ija.proj.pacman.common.Field;
import ija.proj.pacman.common.Maze;
import ija.proj.pacman.common.MazeObject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class KeyObject implements MazeObject  {
    int row;
    int col;
    Maze maze = null;
    Image keyImage;
    public KeyObject(Maze maze, int row, int col){
        this.maze = maze;
        this.col = col;
        this.row = row;
        this.keyImage = new Image("file:lib/img/key.png");
    }

    @Override
    public boolean canMove(Field.Direction dir) {
        return false;
    }

    @Override
    public boolean move(Field.Direction dir) {
        return false;
    }

    @Override
    public boolean isPacman() {
        return false;
    }

    public char getAscii(){
        return 'K';
    }
    public void draw(){
        ImageView imageView = GameView.getInstance().getImageView(row, col);
        imageView.setImage(this.keyImage);
    }
}

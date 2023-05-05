package ija.proj.pacman;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class GameView extends Group {
    private static GameView instance;
    private ImageView[][] gridCells;
    private int cellSize = 30;
    private int mazeSize = 10;

    private GameView(){

    }
    //singleton
    public static GameView getInstance(){
        if (instance == null){
            instance = new GameView();
        }
        return instance;
    }
    public void createGrid(){
        this.gridCells = new ImageView[12][12];
        for (int i = 0; i < mazeSize+2; i++) {
            for (int j = 0; j < mazeSize+2; j++) {
                ImageView cell = new ImageView();
                cell.setX(j * cellSize);
                cell.setY(i * cellSize);
                cell.setFitWidth(cellSize);
                cell.setFitHeight(cellSize);
                this.gridCells[i][j] = cell;
                this.getChildren().add(cell);
            }
        }
    }
    public ImageView getImageView(int row, int col){
        return this.gridCells[row][col];
    }


}

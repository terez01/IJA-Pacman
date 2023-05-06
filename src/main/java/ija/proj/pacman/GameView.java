package ija.proj.pacman;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class GameView extends Group {
    private static GameView instance;
    private List<ImageView> gridCells = new ArrayList<>();
    public int cellSize = 30;
    private int mapWidth;

    private GameView(){

    }
    //singleton
    public static GameView getInstance(){
        if (instance == null){
            instance = new GameView();
        }
        return instance;
    }
    public void createGrid(int mapRows, int mapCols){
        this.gridCells.clear();
        this.getChildren().clear();
        this.mapWidth = mapCols + 3;
        for (int i = 0; i < mapRows+3; i++) {
            for (int j = 0; j < mapCols+3; j++) {
                ImageView cell = new ImageView();
                cell.setX(j * cellSize);
                cell.setY(i * cellSize);
                cell.setFitWidth(cellSize);
                cell.setFitHeight(cellSize);

                this.gridCells.add(cell);
                this.getChildren().add(cell);
            }
        }
    }
    public ImageView getImageView(int row, int col){
        return this.gridCells.get(row*mapWidth+col);
    }


}

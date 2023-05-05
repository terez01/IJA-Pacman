package ija.proj.pacman;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class GameView extends Group {
    private static GameView instance;
    private ImageView[][] gridCells;


    private GameView(){

    }
    public static GameView getInstance(){
        if (instance == null){
            instance = new GameView();
        }
        return instance;
    }
    public void createGrid(){
        this.gridCells = new ImageView[12][12];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                ImageView cell = new ImageView();
                cell.setStyle("-fx-background-color: BLACK");
                cell.setX(j * 30);
                cell.setY(i * 30);
                cell.setFitWidth(30);
                cell.setFitHeight(30);
                this.gridCells[i][j] = cell;
                this.getChildren().add(cell);
            }
        }
    }
    public ImageView getImageView(int row, int col){
        return this.gridCells[row][col];
    }


}

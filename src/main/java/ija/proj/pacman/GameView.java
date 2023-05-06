package ija.proj.pacman;

import ija.proj.pacman.game.PacmanObject;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class GameView extends Group implements Observer {
    private static GameView instance;
    private List<ImageView> gridCells = new ArrayList<>();
    public int cellSize = 30;
    private int mapWidth;
    private Label labelLives, labelStatus;

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
        this.mapWidth = mapCols;
        for (int i = 0; i < mapRows; i++) {
            for (int j = 0; j < mapCols; j++) {
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

    public void setLabelLives(Label labelLives) {
        this.labelLives = labelLives;
    }

    public void setLabelStatus(Label labelStatus) {
        this.labelStatus = labelStatus;
    }

    public void setLabelLivesText(int lives){
        if(lives < 0){
            lives = 0;
        }
        this.labelLives.setText("Lives: " + String.valueOf(lives));
    }
    public void setLabelStatusText(String string){

        this.labelStatus.setText(string);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof PacmanObject){
            setLabelLivesText(((PacmanObject) o).lifeCnt);
        }
        if (o instanceof GameController){
            if(((GameController) o).defeat){
                setLabelStatusText("Game Over");
            }
            else if(((GameController) o).victory){
                setLabelStatusText("Victory");
            }
            else{
                setLabelStatusText("");
                setLabelLivesText(3);
            }
        }
    }
}

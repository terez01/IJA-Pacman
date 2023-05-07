package ija.proj.pacman;

import ija.proj.pacman.game.Leaderboard;
import ija.proj.pacman.game.PacmanObject;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class GameView extends Group implements Observer {
    private static GameView instance;
    private Leaderboard leaderboard = new Leaderboard();
    private List<ImageView> gridCells = new ArrayList<>();
    public int cellSize = 30;
    private int mapWidth;
    private Label labelLives, labelStatus, labelPlay;

//    private Label labelLives, labelStatus;
    private Button startButton, endButton, nextButton, previousButton;
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
    public void setLabelPlay(Label labelPlay) {
        this.labelPlay = labelPlay;
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
                this.labelPlay.setText("Press SPACE to play again");
            }
            else if(((GameController) o).victory){
                setLabelStatusText("Victory");
                this.labelPlay.setText("Press SPACE to play again");
//                leaderboard.getScore(((GameController) o).maze.pacman.stepCnt);
                try {
                    leaderboard.setScoreNew(((GameController) o).maze.pacman.stepCnt, "data/leaderboard.txt");
                    List scoreListOut = leaderboard.getTopScores("data/leaderboard.txt", 5);
                    String message = String.join("\n", scoreListOut);
                    Alert alert = new Alert(Alert.AlertType.NONE);
                    alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    alert.setTitle("Leaderboard Top 5");
                    alert.setHeaderText("Pacman moved "+ String.valueOf(((GameController) o).maze.pacman.stepCnt)+" steps");
                    alert.setContentText(message);

                    alert.showAndWait();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else if(((GameController) o).mode == GameController.Mode.Play){
                setLabelStatusText(String.valueOf(((GameController) o).maze.pacman.stepCnt));
            }
            else{
                setLabelStatusText("");
                this.labelPlay.setText("");
                setLabelLivesText(3);
            }
        }
    }
    public void setPlaybackButtons(Button startButton, Button endButton, Button nextButton, Button previousButton){
        this.startButton = startButton;
        this.startButton.setFocusTraversable(false);
        this.endButton = endButton;
        this.endButton.setFocusTraversable(false);
        this.nextButton = nextButton;
        this.nextButton.setFocusTraversable(false);
        this.previousButton = previousButton;
        this.previousButton.setFocusTraversable(false);

    }
    public void showPlaybackButtons(){
        this.startButton.setVisible(true);
        this.endButton.setVisible(true);
        this.nextButton.setVisible(true);
        this.previousButton.setVisible(true);
    }
    public void hidePlaybackButtons(){
        this.startButton.setVisible(false);
        this.endButton.setVisible(false);
        this.nextButton.setVisible(false);
        this.previousButton.setVisible(false);
    }
}

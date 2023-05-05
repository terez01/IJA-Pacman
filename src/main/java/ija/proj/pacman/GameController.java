package ija.proj.pacman;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameController {
    /*test comment for commit
    *
  public static void main(String... args) throws FileNotFoundException {

        File file = new File("C:/School/git/3bit/summer2023/ija/proj/ija/map01.txt");   //path to the map

        Scanner scan = new Scanner(file);

        String fileContent = "";    //string storing the file

        MazeConfigure cfg = new MazeConfigure();
        //scan next line

        //splitting the first two numbers
        String line = scan.nextLine();
        String[] parts = line.split(" ");
        int num1 = Integer.parseInt(parts[0]);
        int num2 = Integer.parseInt(parts[1]);

        cfg.startReading(num1, num2);

        while(scan.hasNextLine()){
            cfg.processLine(scan.nextLine());
        }

        cfg.stopReading();

        Maze maze = cfg.createMaze();
    }
    * */
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
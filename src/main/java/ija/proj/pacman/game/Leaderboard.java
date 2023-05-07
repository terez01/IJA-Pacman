package ija.proj.pacman.game;

import ija.proj.pacman.GameController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Leaderboard {
    public LinkedHashSet<Integer> scoreList = new LinkedHashSet<>();

    public Leaderboard(){

    }

    public void setScoreNew(int score, String scorePath) throws IOException {
        File file = new File(scorePath);
        Scanner scan = new Scanner(file);

        LinkedList<Integer> scores = new LinkedList<>();
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            if(!scores.contains(Integer.parseInt(line))){
                scores.add(Integer.parseInt(line));
            }
        }
        scan.close();
        if (!scores.contains(score)){
            scores.add(score);
        }

        Collections.sort(scores);
        FileWriter fw = new FileWriter(scorePath, false);
        for (Integer s : scores) {
            fw.write(s + "\n");
        }
        fw.close();
    }

    public List<String> getTopScores(String scorePath, int N) throws FileNotFoundException {
        File file = new File(scorePath);
        Scanner scan = new Scanner(file);

        LinkedList<String> scores = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if(scan.hasNextLine()) {
                String line = scan.nextLine();
                scores.add(line);
            }
        }

        return scores;
    }

    public void getScore(int score){
        appendScore(score);
        try {
            scanFile(scoreList);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList tmpList = new ArrayList<>(scoreList);

        Collections.sort(tmpList, Collections.reverseOrder());
        saveScore(tmpList);
    }
    private void saveScore(ArrayList list){
        String filePath = "data/leaderboard.txt";

        for(int i = 0; i < list.size(); i++){
            try{
                FileWriter fw = new FileWriter(filePath);
                fw.write(String.valueOf(list.get(i)));
                fw.close();
            }
            catch (IOException ioe){
                System.err.println("IOException:" + ioe.getMessage());
            }

        }

    }
    private void appendScore(int score){
        String filePath = "data/leaderboard.txt";
        try{
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(String.valueOf(score) + "\n");
            fw.close();
        }
        catch (IOException ioe){
            System.err.println("IOException:" + ioe.getMessage());
        }
    }
    private void scanFile(LinkedHashSet list) throws FileNotFoundException {
        String filePath = "data/leaderboard.txt";
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        if(!scanner.hasNextLine()){
            for(int i = 0; i < list.size(); i++){
                list.add(0);
            }
        }else{
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                list.add(Integer.parseInt(line));
            }

        }
        for(int i = 0; i < list.size(); i++){
            System.out.print(list);
        }
    }

}

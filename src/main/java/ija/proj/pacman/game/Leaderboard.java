package ija.proj.pacman.game;

import ija.proj.pacman.GameController;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Leaderboard {
    public ArrayList<Integer> scoreList = new ArrayList<>();

    public Leaderboard(){

    }
    public void getScore(int score){
        scoreList.add(score);
        Collections.sort(scoreList, Collections.reverseOrder());
        scoreList = removeListDuplicates(scoreList);
        saveScore(scoreList);
    }
    private <T> ArrayList<T>  removeListDuplicates(ArrayList list){
        Set<T> set = new LinkedHashSet<>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        return list;
    }
    private void saveScore(ArrayList list){
        String filePath = "data/leaderboard.txt";

        deleteScore(filePath);

        for(int i = 0; i < list.size(); i++){
            try{
                FileWriter fw = new FileWriter(filePath, true); //true flag will append the new data
                fw.write(String.valueOf(i) + " -> " +String.valueOf(list.get(i)) + "\n"); //string to be appended
                fw.close();
            }
            catch (IOException ioe){
                System.err.println("IOException:" + ioe.getMessage());
            }

        }

    }
    private void deleteScore(String path){
        try{
            FileWriter fw = new FileWriter(path); //true flag will append the new data
            fw.write(""); //writes an empty string
            fw.close();
        }
        catch (IOException ioe){
            System.err.println("IOException:" + ioe.getMessage());
        }
    }

}

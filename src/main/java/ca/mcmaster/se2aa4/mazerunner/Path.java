package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Path {

    private String userPath = ""; //tracks canonical form

    public String getUserPath(){
        return userPath;
    }

    public void addStep (String step){
        userPath += step;
    }

    public String factorizedForm (String s){
        if (s.equals("")){
            return s;
        }
        s = s.trim();
        String factorized = "";
    
        char currentChar = s.charAt(0); 
        int instances = 0; 
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == ' '){//ignore spaces 
                continue;
            }
            else if (s.charAt(i) == currentChar){
                instances +=1; //if repeating character, increment instance 
            }
            else{ //if new character, add number of instances and character to factorized path
                factorized += String.valueOf(instances) + currentChar + ' ';
                currentChar = s.charAt(i); 
                instances = 1; 
            }
        }

        factorized += String.valueOf(instances) + currentChar + ' ';
        return factorized;

    }

    public String canonicalForm(String factorized){
        StringBuilder canonical = new StringBuilder();
        StringBuilder currentDigit = new StringBuilder("");

        for (int i = 0; i < factorized.length(); i++){
            if (factorized.charAt(i) == 'F' || factorized.charAt(i) == 'R' || factorized.charAt(i)== 'L'){
                //assuming only captical letters inputted by users are valid
                int repetitions = 1; 
                if (!currentDigit.toString().equals("")){
                    repetitions = Integer.parseInt(currentDigit.toString());
                }
                for (int j = 0; j < repetitions; j++){
                        canonical.append(factorized.charAt(i));
                }
                currentDigit.replace(0, currentDigit.length(), ""); 
                
            }
            else if (factorized.charAt(i) >= '0' && factorized.charAt(i) <= '9'){
                currentDigit.append(factorized.charAt(i));
            }
            else if (factorized.charAt(i) != ' '){
                //"String inputted not a valid path";
                System.exit(1);

            }
        }
        return canonical.toString(); 
    }

    public boolean isCorrectPath(String inputString, Maze m){

        String inputCanonical = canonicalForm(inputString);
        Character c = new Character(m.getEntryRow());
        //try both the west to east and east to west paths
        if (c.runThroughMaze(inputCanonical, m, Direction.East) || c.runThroughMaze(inputCanonical, m, Direction.West)){
            return true;
        }
        return false;
    }

}

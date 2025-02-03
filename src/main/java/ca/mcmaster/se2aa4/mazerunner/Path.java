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
        String factorized = "";
        if (s == ""){
            return factorized;
        }
        char currentChar = s.charAt(0); 
        int instances = 0; 
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == ' '){
                continue;
            }
            if (s.charAt(i) == currentChar){
                instances +=1;
            }
            else if (i != s.length()-1){
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
                int repetitions = 1; 
                if (!currentDigit.toString().equals("")){
                    repetitions = Integer.parseInt(currentDigit.toString());
                }
                for (int j = 0; j < repetitions; j++){
                        canonical.append(factorized.charAt(i));
                }
                canonical.append(" ");
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

    public String reverse (String originalString){
        StringBuilder reversed = new StringBuilder();
        for (int i = originalString.length() - 1; i >= 0; i--){
            reversed.append(originalString.charAt(i));
        }
        return reversed.toString(); 
    }

    public boolean isCorrectPath(String inputString, Maze m){
        Character c = new Character(m.getEntryRow());
        RightHandSolver solver = new RightHandSolver();
        String inputFactorized = factorizedForm(canonicalForm(inputString));
        String reverseFactorized = factorizedForm(reverse(canonicalForm(inputString)));
        if (solver.solveMaze(c, m).equals(inputFactorized) || solver.solveMaze(c, m).equals(reverseFactorized)){
            return true; 
        }
        return false;
    }


}

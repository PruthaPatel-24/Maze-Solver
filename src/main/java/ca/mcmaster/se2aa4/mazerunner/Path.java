package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Path {

    private String s = ""; //tracks canonical form

    public void addStep (String step){
        s += step;
    }

    public String factoriedForm (){
        String factoried = "";
        if (s == ""){
            return factoried;
        }
        char currentChar = s.charAt(0); 
        int instances = 0; 
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == currentChar){
                instances +=1;
            }
            else if (i != s.length()-1){
                factoried += String.valueOf(instances) + currentChar + ' ';
                currentChar = s.charAt(i);
                instances = 1; 
            }
        }

        factoried += String.valueOf(instances) + currentChar + ' ';
        return factoried;

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
}

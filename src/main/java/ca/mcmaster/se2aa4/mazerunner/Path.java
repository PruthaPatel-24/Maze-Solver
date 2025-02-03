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

}

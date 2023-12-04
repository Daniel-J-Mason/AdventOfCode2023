package com.allthing.day02;

import com.allthing.parser.FileParser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartTwo {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        
        List<String> inputList = fileParser.toStringArray("02_DayTwo_PartOne.txt");
        
        System.out.println(sumOfValidGameIds(inputList));
    }
    
    public static int sumOfValidGameIds(List<String> inputList){
        int sum = 0;
        
        for (String line: inputList){
            sum += parseSingleGame(line);
        }
        
        return sum;
    }
    
    public static int parseSingleGame(String line){
        String[] idSplit = line.split(":");
        
        Game game = new Game(getGameId(line));
        
        game.addRed(getMaxColor("red", idSplit[1]));
        game.addBlue(getMaxColor("blue", idSplit[1]));
        game.addGreen(getMaxColor("green", idSplit[1]));
        
        System.out.println(game);
        
        return game.getPower();
    }
    
    private static int getGameId(String line){
        String idRegex = "Game (\\d+):";
        
        Pattern pattern = Pattern.compile(idRegex);
        Matcher matcher = pattern.matcher(line);
        
        if (matcher.find()){
            return Integer.parseInt(matcher.group(1));
        } else {
            return -99999; //throw a significant negative if no match
        }
    }
    
    private static int getMaxColor(String colorName, String gameInput){
        String idRegex = "(\\d+) " + colorName;
        
        Pattern pattern = Pattern.compile(idRegex);
        Matcher matcher = pattern.matcher(gameInput);
        
        int max = 0;
        while (matcher.find()) {
            int count = Integer.parseInt(matcher.group(1));
            if (count > max){
                max = count;
            }
        }
        
        return max;
    }
}

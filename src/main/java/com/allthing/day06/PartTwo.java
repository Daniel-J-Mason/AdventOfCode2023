package com.allthing.day06;

import com.allthing.parser.FileParser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartTwo {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        List<String> lines = fileParser.toStringArray("06_DaySix_PartOne.txt");
        
        long time = intFromLine(lines.get(0));
        long distance = intFromLine(lines.get(1));
        
        System.out.println(calculateWinConditionCount(time, distance));
        
        
    }
    
    public static long calculateWinConditionCount(long time, long distance){
        long firstValidTime = -1;
        long lastValidTime = -1;
        
        for (long i = 0; i < time; i++){
            long remainingTime = time - i;
            long speed = i;
            
            if ((speed * remainingTime) > distance){
                firstValidTime = i;
                break;
            }
        }
        
        for (long i = time; i > 0; i--){
            long remainingTime = time - i;
            long speed = i;
            
            if ((speed * remainingTime) > distance){
                lastValidTime = i;
                break;
            }
        }
        
        System.out.println("First valid " + firstValidTime);
        System.out.println("Last valid " + lastValidTime);
        
        return lastValidTime - firstValidTime + 1; //inclusive
    }
    
    public static long intFromLine(String line){
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        
        StringBuilder result = new StringBuilder();
        
        while (matcher.find()){
            result.append(matcher.group());
        }
        
        return Long.parseLong(result.toString());
    }
}

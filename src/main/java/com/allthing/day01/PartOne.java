package com.allthing.day01;

import com.allthing.parser.FileParser;

import java.util.List;

public class PartOne {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        
        List<String> calibrationValues = fileParser.toStringArray("01_DayOne_PartOne.txt");
        
        System.out.println(sumOfAll(calibrationValues));
    }
    
    public static Integer sumOfAll(List<String> calibrationValues){
        int sum = 0;
        
        for (String value: calibrationValues){
            sum += findSingleCalibration(value);
        }
        
        return sum;
    }
    
    private static Integer findSingleCalibration(String input){
        Character first = null;
        Character second = null;
        
        for (Character character: input.toCharArray()){
            if (Character.isDigit(character)) {
                if (first == null) {
                    first = character;
                } else {
                    second = character;
                }
            }
        }
        
        if (second == null){
            return twoCharactersToNumber(first, first);
        } else {
            return twoCharactersToNumber(first, second);
        }
    }
    
    private static Integer twoCharactersToNumber(Character first, Character second){
        String stringValue = "" + first + second;
        
        return Integer.parseInt(stringValue);
    }
}

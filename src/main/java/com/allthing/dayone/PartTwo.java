package com.allthing.dayone;

import com.allthing.parser.FileParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PartTwo {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        
        List<String> calibrationValues = fileParser.toStringArray("01_DayOne_PartTwo.txt");
        
        System.out.println(sumOfAll(calibrationValues));
    }
    
    public static Integer sumOfAll(List<String> calibrationValues){
        int sum = 0;
        
        for (String value: calibrationValues){
            sum += findSingleCalibration(value);
        }
        
        return sum;
    }
    
    
    // Converts single line to result
    private static Integer findSingleCalibration(String input){
        Integer first = null;
        Integer second = null;
        
        List<Integer> sanitizedLine = convertCalibrationString(input);

        for (Integer value: sanitizedLine){
            if (first == null){
                first = value;
            } else {
                second = value;
            }
        }
        
        if (second == null){
            return twoIntegersToNumber(first, first);
        } else {
            return twoIntegersToNumber(first, second);
        }
    }
    
    
    // Converts a single line of text to a text array so 3oneight becomes 3, 1, 8
    private static List<Integer> convertCalibrationString(String input){
        List<Integer> result =  new ArrayList<>();
        
        String regex = "(?=(";
        
        regex += Arrays.stream(NumberWord.values())
                .map(NumberWord::getWord)
                .collect(Collectors.joining("|"));
        
        regex += "|\\d))";
        
        System.out.println(regex);
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        
        List<String> allMatches = new ArrayList<>();
        
        while (matcher.find()) {
            allMatches.add(matcher.group(1));
        }
        
        for(String match: allMatches){
            if (match.length() == 1){
                result.add(Integer.parseInt(match));
            } else {
                result.add(NumberWord.convertToNumber(match));
            }
        }
        
        return result;
    }
    
    // Concatenate two ints
    private static Integer twoIntegersToNumber(Integer first, Integer second){
        String stringValue = "" + first + second;
        
        return Integer.parseInt(stringValue);
    }
    
}

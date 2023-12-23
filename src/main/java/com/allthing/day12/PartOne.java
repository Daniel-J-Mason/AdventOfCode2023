package com.allthing.day12;

import com.allthing.parser.FileParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartOne {
    private static final String workingRegex = "^\\.+";
    private static final Pattern pattern = Pattern.compile(workingRegex);
    
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        
        List<String> lines = fileParser.toStringArray("12_DayTwelve_PartOne.txt");
        

        
        System.out.println(sumLines(lines));
    }
    
    public static int sumLines(List<String> lines){
        int sum = 0;
        
        for(String line: lines){
            sum += countPermutations(parseRow(line) + " ", parseList(line));
        }
        
        return sum;
    }
    
    public static int countPermutations(String row, List<Integer> listOfDamaged) {
        //If we reach the end of the input, but we have remaining values to check, fail else pass
        if (row.isEmpty()){
            if (!listOfDamaged.isEmpty()){
                return 0;
            } else {
                return 1;
            }
        }
        
        //have to utilize known broken machines
        if (listOfDamaged.isEmpty()){
            if (row.contains("#")){
                return 0;
            } else {
                return 1;
            }
        }
        
        //skip working machines, continue checking
        Matcher matcher = pattern.matcher(row);
        if (matcher.find()){
            String found = matcher.group();
            return countPermutations(row.substring(found.length()), listOfDamaged);
        }
        
        //Base cases are done, now set up to check with all wild cards being checked as # or .
        
        int permutations = 0;
        
        //assume '.'
        if (row.charAt(0) == '?'){
            permutations += countPermutations(row.substring(1), listOfDamaged);
        }
        
        Matcher matcher1 = checkDamageGroupPattern(listOfDamaged.getFirst()).matcher(row);
        
        if (matcher1.find()){
            List<Integer> subListOfDamaged = listOfDamaged.subList(1, listOfDamaged.size());
            permutations += countPermutations(row.substring(matcher1.group().length()), subListOfDamaged);
        }
        
        return permutations;
        
    }
    
    private static Pattern checkDamageGroupPattern(int damageCount){
        String regex = String.format("^[#?]{%s}(\\.|\\?|\\s)", damageCount);
        return Pattern.compile(regex);
    }
    
    private static String parseRow(String line){
        String[] split = line.split(" ");
        return split[0];
    }
    
    private static List<Integer> parseList(String line){
        String[] split = line.split(" ");
        List<Integer> values = new ArrayList<>();
        
        for (String character: split[1].split(",")){
            values.add(Integer.parseInt(String.valueOf(character)));
        }
        
        return values;
    }
}
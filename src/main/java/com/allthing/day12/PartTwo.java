package com.allthing.day12;

import com.allthing.parser.FileParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PartTwo {
    private static final String workingRegex = "^\\.+";
    private static final Pattern pattern = Pattern.compile(workingRegex);
    
    private static HashMap<Map.Entry<String, List<Integer>>, Long> memo = new HashMap<>();
    
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        
        List<String> lines = fileParser.toStringArray("12_DayTwelve_PartTwo.txt");
        
        
        System.out.println(sumLines(lines));
    }
    
    public static long sumLines(List<String> lines){
        long sum = 0;
        
        for(String line: lines){
            sum += countPermutations(parseRow(line) + " ", parseList(line));
        }
        
        return sum;
    }
    
    public static long countPermutations(String row, List<Integer> listOfDamaged) {
        //If we reach the end of the input, but we have remaining values to check, fail else pass
        
        Map.Entry<String, List<Integer>> key = Map.entry(row, listOfDamaged);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
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
        
        long permutations = 0;
        
        //assume '.'
        if (row.charAt(0) == '?'){
            permutations += countPermutations(row.substring(1), listOfDamaged);
        }
        
        Matcher matcher1 = checkDamageGroupPattern(listOfDamaged.getFirst()).matcher(row);
        
        if (matcher1.find()){
            List<Integer> subListOfDamaged = listOfDamaged.subList(1, listOfDamaged.size());
            permutations += countPermutations(row.substring(matcher1.group().length()), subListOfDamaged);
        }
        
        memo.put(key, permutations);
        
        return permutations;
        
    }
    
    private static Pattern checkDamageGroupPattern(int damageCount){
        String regex = String.format("^[#?]{%s}(\\.|\\?|\\s)", damageCount);
        return Pattern.compile(regex);
    }
    
    private static String parseRow(String line){
        String[] split = line.split(" ");
        return split[0] + "?" + split[0] + "?" + split[0] + "?" + split[0] + "?" + split[0];
    }
    
    private static List<Integer> parseList(String line){
        String[] split = line.split(" ");
            List<Integer> values = new ArrayList<>();
        
        for (String character: split[1].split(",")){
            values.add(Integer.parseInt(String.valueOf(character)));
        }
        
        return Stream.generate(() -> values)
                .limit(5)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
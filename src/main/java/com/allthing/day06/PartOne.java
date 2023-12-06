package com.allthing.day06;

import com.allthing.parser.FileParser;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class PartOne {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        List<String> lines = fileParser.toStringArray("06_DaySix_PartOne.txt");
        
        List<Integer> times = listFromLine(lines.get(0));
        List<Integer> ditances = listFromLine(lines.get(1));
        
        List<Pair<Integer, Integer>> timeDistancePairs = zipLists(times, ditances);
        
        
        System.out.println(calculateProductOfWinCounts(timeDistancePairs));
        
        
    }
    
    public static int calculateProductOfWinCounts(List<Pair<Integer, Integer>> timeDistancePairs){
        timeDistancePairs.forEach(
                e -> System.out.println(calculateWinConditionCount(e.getKey(), e.getValue()))
        );
        
        return timeDistancePairs.stream()
                .mapToInt(e -> calculateWinConditionCount(e.getKey(), e.getValue()))
                .reduce(1, (a, b) -> a * b);
    }
    
    public static int calculateWinConditionCount(int time, int distance){
        int firstValidTime = -1;
        int lastValidTime = -1;
        
        for (int i = 0; i < time; i++){
            int remainingTime = time - i;
            int speed = i;
            
            if ((speed * remainingTime) > distance){
                firstValidTime = i;
                break;
            }
        }
        
        for (int i = time; i > 0; i--){
            int remainingTime = time - i;
            int speed = i;
            
            if ((speed * remainingTime) > distance){
                lastValidTime = i;
                break;
            }
        }
        
        System.out.println("First valid " + firstValidTime);
        System.out.println("Last valid " + lastValidTime);
        
        return lastValidTime - firstValidTime + 1; //inclusive
    }
    
    public static List<Pair<Integer, Integer>> zipLists(List<Integer> times, List<Integer> distances){
        
        return IntStream
                .range(0, times.size())
                .mapToObj(i -> Pair.of(times.get(i), distances.get(i)))
                .toList();
    }
    
    public static List<Integer> listFromLine(String line){
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        
        List<Integer> result = new ArrayList<>();
        
        while (matcher.find()){
            result.add(Integer.parseInt(matcher.group()));
        }
        
        return result;
    }
}

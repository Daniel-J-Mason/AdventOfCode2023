package com.allthing.day09;

import com.allthing.parser.FileParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PartTwo {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        
        List<String> lines = fileParser.toStringArray("09_DayNine_PartOne.txt");
        
        System.out.println(getTotal(lines));
    }
    
    public static int getTotal(List<String> lines){
        int total = 0;
        
        for (String line: lines){
            total += getExtrapolatedValue(line);
        }
        
        return total;
    }
    
    public static int getExtrapolatedValue(String line){
        List<Integer> data = Arrays.stream(line.split(" ")).map(Integer::valueOf).toList();
        List<Integer> firstValues = new ArrayList<>();
        while (!isAllZeros(data)){
            firstValues.add(data.getFirst());
            data = differenceList(data);
        }
        firstValues.add(0);
        
        firstValues = firstValues.reversed();
        System.out.println(firstValues);
        
        int extrapolateValue = 0;
        for (int i = 0; i < firstValues.size() - 1; i++){
            int next = firstValues.get(i + 1);
            extrapolateValue = next - extrapolateValue;
        }
        
        return extrapolateValue;
    }
    
    public static List<Integer> differenceList(List<Integer> startingList){
        List<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < startingList.size() - 1; i++){
            result.add(startingList.get(i + 1) - startingList.get(i));
        }
        
        return result;
    }
    
    public static boolean isAllZeros(List<Integer> listToCheck){
        for (Integer element: listToCheck){
            if (element != 0){
                return false;
            }
        }
        
        return true;
    }
}

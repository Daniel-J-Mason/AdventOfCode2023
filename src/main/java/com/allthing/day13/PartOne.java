package com.allthing.day13;

import com.allthing.parser.FileParser;

import java.util.ArrayList;
import java.util.List;

public class PartOne {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        
        List<String> lines = fileParser.toStringArray("13_DayThirteen_PartOne.txt");
        
        System.out.println(sumOfGridValues(lines));
    }
    
    public static int sumOfGridValues(List<String> lines){
        int total = 0;
        
        for (List<String> grid: listOfGrids(lines)){
            total += getGridValue(grid);
        }
        
        return total;
    }
    
    public static List<List<String>> listOfGrids(List<String> lines){
        List<List<String>> result = new ArrayList<>();
        
        List<String> current = new ArrayList<>();
        for (String line: lines){
            if (!line.isEmpty()){
                current.add(line);
            } else {
                result.add(new ArrayList<>(current));
                current.clear();
            }
        }
        
        if (!current.isEmpty()){
            result.add(current);
        }
        
        return result;
    }
    
    
    public static int getGridValue(List<String> lines){
        lines.forEach(System.out::println);
        
        int horizontalValue = calculateReflectionValue(binaryRows(lines));
        int verticalValue = calculateReflectionValue(binaryColumns(lines));
        if (horizontalValue != -1){
            System.out.println(100 * horizontalValue);
            return 100 * horizontalValue;
        } else if (verticalValue != -1){
            System.out.println(verticalValue);
            return verticalValue;
        } else {
            System.out.println(0);
            return 0;
        }
    }
    
    private static int calculateReflectionValue(List<String> binaryList){
        for (int i = 0; i < binaryList.size() - 1; i++){
            int left = i;
            int right = i +1;
            
            while (left > 0 && right < binaryList.size() - 1 && reflects(binaryList.get(left), binaryList.get(right))){
                left--;
                right++;
            }
            
            System.out.println(left + ", " + right);
            
            if ((left == 0 || right == binaryList.size() - 1) && (reflects(binaryList.get(left), binaryList.get(right)))){
                return i + 1;
            }
        }
        
        return -1;
    }
    
    private static boolean reflects(String first, String second){
        return (Integer.parseInt(first, 2) ^ (Integer.parseInt(second, 2))) == 0;
    }
    
    private static List<String> binaryRows(List<String> lines){
        List<String> result = new ArrayList<>();
        
        for (String line: lines){
            StringBuilder builder = new StringBuilder();
            
            for (char item: line.toCharArray()){
                builder.append(translate(item));
            }
            result.add(builder.toString());
        }
        
        System.out.println(result);
        
        return result;
    }
    
    private static List<String> binaryColumns(List<String> lines){
        List<String> result = new ArrayList<>();
        
        for (int width = 0; width < lines.getFirst().length(); width++){
            StringBuilder builder = new StringBuilder();
            for (int height = 0; height < lines.size(); height++){
                builder.append(translate(lines.get(height).charAt(width)));
            }
            
            result.add(builder.toString());
        }
        
        return result;
    }
    
    private static char translate(char hashOrPeriod){
        return switch (hashOrPeriod){
            case '#' -> '1';
            case '.' -> '0';
            default -> '?';
        };
    }
}

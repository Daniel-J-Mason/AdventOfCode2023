package com.allthing.day14;

import com.allthing.parser.FileParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//TODO: This is very brute force 10 min runtime, but successful, optimize
public class PartTwo {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        
        List<String> lines = fileParser.toStringArray("14_DayFourteen_PartTwo.txt");
        
        System.out.println(sumOfColumns(lines));
    }
    
    public static int sumOfColumns(List<String> lines) {
        Map<List<String>, List<String>> memonic = new HashMap<>();
        List<String> result = lines;
        
        int counter = 0;
        
        for (int i = 0; i < 1000000000; i++) {
            if (!memonic.containsKey(result)){
                memonic.put(result, shiftEast(shiftSouth(shiftWest(shiftNorth(result)))));
            }
            result = memonic.get(result);
            counter++;
            if (counter % 1000000 == 0){
                System.out.println(counter);
            }
        }
        
        result.forEach(System.out::println);
        
        result = getColumnsFromGrid(result);
        
        return result.stream()
                .mapToInt(PartTwo::calculateColumnStrain)
                .sum();
    }
    
    public static List<String> shiftNorth(List<String> grid){
        List<String> columns = getColumnsFromGrid(grid);
        List<String> result = new ArrayList<>();
        
        for (String line: columns){
            result.add(reorderColumnOrRow(line));
        }
        
        return getColumnsFromGrid(result);
    }
    
    public static List<String> shiftEast(List<String> grid){
        List<String> result = new ArrayList<>();
        
        for (String line: grid){
            result.add(reorderColumnOrRow(new StringBuilder(line).reverse().toString()));
        }
        
        return result.stream()
                .map(line -> new StringBuilder(line).reverse().toString())
                .toList();
    }
    
    public static List<String> shiftSouth(List<String> grid){
        List<String> columns = getColumnsFromGrid(grid);
        List<String> result = new ArrayList<>();
        
        for (String line: columns){
            result.add(reorderColumnOrRow(new StringBuilder(line).reverse().toString()));
        }
        
        return getColumnsFromGrid(result.stream()
                .map(line -> new StringBuilder(line).reverse().toString())
                .toList());
    }
    
    public static List<String> shiftWest(List<String> grid){
        List<String> result = new ArrayList<>();
        
        for (String line: grid){
            result.add(reorderColumnOrRow(line));
        }
        
        return result;
    }
    
    /*
Pseudo Rotation of grid, collect columns from input
 */
    public static List<String> getColumnsFromGrid(List<String> lines){
        List<List<Character>> listOfCharArrays = new ArrayList<>();
        
        for (int i = 0; i < lines.size(); i++){
            String row = lines.get(i);
            for (int character = 0; character < row.length(); character++){
                if (listOfCharArrays.size() <= character){
                    listOfCharArrays.add(new ArrayList<>());
                }
                listOfCharArrays.get(character).add(row.charAt(character));
            }
        }
        
        return listOfCharArrays.stream()
                .map(characters -> characters.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining()))
                .collect(Collectors.toList());
    }
    
    /*
    Allow O's to collect at hash marks
     */
    public static String reorderColumnOrRow(String line) {
        StringBuilder output = new StringBuilder();
        
        int i = 0;
        int rockCount = 0;
        int dotCount = 0;
        while (i < line.length()) {
            char character = line.charAt(i);
            if (character == '#') {
                IntStream.range(0, rockCount).forEach(count -> output.append('O'));
                IntStream.range(0, dotCount).forEach(dot -> output.append('.'));
                output.append('#');
                rockCount = 0;
                dotCount = 0;
            } else if (character == 'O') {
                rockCount++;
            } else if (character == '.') {
                dotCount++;
            }
            i++;
        }
        
        IntStream.range(0, rockCount).forEach(count -> output.append('O'));
        IntStream.range(0, dotCount).forEach(dot -> output.append('.'));
        
        return output.toString();
    }
    
    /*
    To sum each column, O's collect by hashes, we can short circuit this by indexing the '#'
    marks (storing them in sumCounter) then counting the number of 0's that follow it to align with
    the problem strain parameters
    */
    public static int calculateColumnStrain(String column) {
        int sum = 0;
        
        for (int i = 0; i < column.length(); i++){
            char element = column.charAt(i);
            if (element == 'O'){
                sum += column.length() - i;
            }
        }
        
        return sum;
    }
    
}

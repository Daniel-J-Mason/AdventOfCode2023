package com.allthing.day14;

import com.allthing.parser.FileParser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PartOne {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        
        List<String> lines = fileParser.toStringArray("14_DayFourteen_PartOne.txt");
        
        System.out.println(sumOfColumns(lines));
    }
    
    public static int sumOfColumns(List<String> lines){
        return getColumnsFromGrid(lines).stream()
                .mapToInt(PartOne::calculateColumnStrain)
                .sum();
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
    To sum each column, O's collect by hashes, we can short circuit this by indexing the '#'
    marks (storing them in sumCounter) then counting the number of 0's that follow it to align with
    the problem strain parameters
     */
    public static int calculateColumnStrain(String column){
        int sum = 0;
        
        int i = 0;
        int sumCounter = column.length();
        
        while (i < column.length()){
            if (column.charAt(i) == '#'){
                sumCounter = column.length() - i - 1;
            }
            
            if (column.charAt(i) == 'O'){
                sum += sumCounter;
                sumCounter--;
            }
            
            i++;
        }
        
        return sum;
    }
    
}

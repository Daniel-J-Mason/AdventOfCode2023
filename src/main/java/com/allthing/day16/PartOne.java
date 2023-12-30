package com.allthing.day16;

import com.allthing.parser.FileParser;

import java.util.List;
import java.util.Set;

//TODO: enhance more by removing duplicate dfs calls
public class PartOne {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        
        List<String> gridLines = fileParser.toStringArray("16_DaySixteen_PartOne.txt");
        
        Grid testGrid = new Grid(gridLines);
        Pathfind pathfind = new Pathfind(testGrid);
        
        printVisitedGrid(testGrid, pathfind.dfsFindPaths(new Pair<>(0, 0), GridDirection.RIGHT));
        System.out.println(countEnergizedCells(testGrid, pathfind.dfsFindPaths(new Pair<>(0, 0), GridDirection.RIGHT)));
    }
    
    public static int countEnergizedCells(Grid grid, Set<Pair<Integer, Integer>> foundCoords) {
        int sum = 0;
        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                if (foundCoords.contains(new Pair<>(x, y))) {
                    sum++;
                }
            }
        }
        return sum;
    }
    
    public static void printVisitedGrid(Grid grid, Set<Pair<Integer, Integer>> foundCoords) {
        
        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                if (foundCoords.contains(new Pair<>(x, y))){
                    System.out.print('#');
                } else {
                    System.out.print('.');
                }
            }
            System.out.println();
        }
    }
    
    

}



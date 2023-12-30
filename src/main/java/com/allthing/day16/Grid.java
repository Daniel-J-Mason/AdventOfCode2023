package com.allthing.day16;

import java.util.List;

public class Grid {
    private final char[][] grid;

    
    public Grid(List<String> inputLines){
        grid = generateGrid(inputLines);
    }
    
    public char getSymbol(int x, int y){
        return grid[y][x];
    }
    
    
    public char[][] generateGrid(List<String> inputLines){
        char[][] grid = new char[inputLines.size()][inputLines.getFirst().length()];
        
        for (int i = 0; i < inputLines.size(); i++){
            grid[i] = inputLines.get(i).toCharArray();
        }
        
        return grid;
    }
    
    public void printGrid(){
        for (int y = 0; y < grid.length; y++){
            for (int x = 0; x < grid[0].length; x++){
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }
    }
    
    public int getWidth(){
        return grid[0].length;
    }
    
    public int getHeight(){
        return grid.length;
    }
}

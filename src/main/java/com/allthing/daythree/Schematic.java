package com.allthing.daythree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Schematic {
    char[][] grid;
    
    public Schematic(List<String> input) {
        int height = input.toArray().length;
        int width = input.get(0).length();
        
        grid = new char[width][height];
        
        int heightCounter = 0;
        for (String item : input) {
            int widthCounter = 0;
            for (char character : item.toCharArray()) {
                grid[widthCounter][heightCounter] = character;
                widthCounter++;
            }
            heightCounter++;
        }
    }
    
    public int calculatePartNumber() {
        List<Integer> getNumber = new ArrayList<>();
        
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                if (getFromCoordinate(i, j) != '.' && !Character.isDigit(getFromCoordinate(i, j))){
                    getNumber.addAll(getNumberNeighbors(i, j));
                }
            }
        }
        
        return getNumber.stream().reduce(0, Integer::sum);
    }
    
    public int calculateGearRatio(){
        List<Integer> getNumber = new ArrayList<>();
        
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                if (getFromCoordinate(i, j) == '*'){
                    List<Integer> checkNeighbors= getNumberNeighbors(i, j);
                    if (checkNeighbors.size() == 2){
                        getNumber.add(checkNeighbors.get(0) * checkNeighbors.get(1));
                    }
                }
            }
        }
        
        return getNumber.stream().reduce(0, Integer::sum);
    }
    
    public void printGrid() {
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[j][i] + " ");
            }
            System.out.println();
        }
    }
    
    // (0, 0) is top left corner
    public char getFromCoordinate(int x, int y) {
        return grid[x][y];
    }
    
    public int getWidth(){
        return grid.length;
    }
    
    public int getHeight(){
        return grid[0].length;
    }
    
    public List<Integer> getNumberNeighbors(int x, int y) {
        Set<String> numberCoordinates = new HashSet<>();
        List<Integer> neighbors = new ArrayList<>();
        
        int[][] directions = {
                {-1, -1}, {0, -1}, {1, -1},
                {-1, 0}, /* (x, y) */ {1, 0},
                {-1, 1}, {0, 1}, {1, 1}
        };
        

        for (int[] direction : directions) {
            StringBuilder intConcatenate = new StringBuilder();
            int dx = direction[0];
            int dy = direction[1];
            
            int nx = x + dx;
            int ny = y + dy;
            
            // Check if this coordinate is already part of another number
            if (numberCoordinates.contains(nx + "," + ny)) {
                continue;
            }
            
            if (isValid(nx, ny) && Character.isDigit(grid[nx][ny])){
                int xCounter = 0;
                
                while (isValid(nx + xCounter, ny) && Character.isDigit(grid[nx + xCounter][ny])) {
                    xCounter--;
                }
                
                xCounter++;
                
                while (isValid(nx + xCounter, ny) && Character.isDigit(grid[nx + xCounter][ny])){
                    intConcatenate.append(grid[nx + xCounter][ny]);
                    numberCoordinates.add((nx + xCounter) + "," + ny);
                    xCounter++;
                }
                
                neighbors.add(Integer.parseInt(intConcatenate.toString()));
            }
        }
        return neighbors;
    }
    
    private boolean isValid(int x, int y){
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }
}

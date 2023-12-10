package com.allthing.day10;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class FloodFiller {
    boolean[][] enclosed;
    List<Pair<Integer, Integer>> pathCoords;
    List<Pair<Integer, Integer>> validCounts;
    private Set<Pair<Integer, Integer>> filled;
    
    private int width;
    private int height;
    
    public FloodFiller(List<String> inputLines){
        Field field = new Field(inputLines);
        validCounts = getAllEnclosed(inputLines);
        pathCoords = field.getPathNodeCoords();
        filled = new HashSet<>();
        
        width = field.getWidth();
        height = field.getHeight();
        enclosed = new boolean[width][height];
        
        for (int i = 0; i < width; ++i){
            for (int j = 0; j < height; ++j){
                enclosed[i][j] = !pathCoords.contains(Pair.of(i, j));
            }
        }
        
        for (int i = 0; i < width; ++i){
            floodFill(i, 0);
            floodFill(i, height - 1);
        }
        
        for (int j = 0; j < height; ++j){
            floodFill(0, j);
            floodFill(width - 1, j);
        }
        
        System.out.println("VALID " + validCounts);
        
        for (int i = 0; i < height; ++i){
            for (int j = 0; j < width; ++j){
                if(pathCoords.contains(Pair.of(j, i))){
                    System.out.print('#');
                } else if(enclosed[j][i] && validCounts.contains(Pair.of(j, i))){
                    System.out.print('!');
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }
    
    private void floodFill(int x, int y) {
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(Pair.of(x, y));
        
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> p = queue.remove();
            
            x = p.getLeft();
            y = p.getRight();
            
            if (x < 0 || x >= enclosed.length || y < 0 || y >= enclosed[0].length || filled.contains(Pair.of(x, y)))
                continue;
            
            if (!enclosed[x][y] || pathCoords.contains(Pair.of(x, y)))
                continue;
            
            filled.add(Pair.of(x, y));
            enclosed[x][y] = false;
            
            queue.add(Pair.of(x+1, y));
            queue.add(Pair.of(x-1, y));
            queue.add(Pair.of(x, y+1));
            queue.add(Pair.of(x, y-1));
        }
    }
    
    public List<Pair<Integer, Integer>> getAllEnclosed(List<String> inputLines){
        List<Pair<Integer, Integer>> result = new ArrayList<>();
        for (int y = 0; y < inputLines.size(); y++){
            String line = inputLines.get(y);
            for (int x = 0; x < line.length(); x++){
                char pipe = line.charAt(x);
                if (pipe == '.'){
                    result.add(Pair.of(x, y));
                }
            }
        }
        
        return result;
    }
    
    public int countEnclosed() {
        int count = 0;
        for (int i = 0; i < enclosed[0].length; ++i){
            for (int j = 0; j < enclosed.length; ++j){
                if (enclosed[j][i] && validCounts.contains(Pair.of(j, i))){
                    ++count; // Count true cells
                }
            }
        }
        return count;
    }
}

package com.allthing.day16;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class Pathfind {
    private final Grid grid;
    
    public Pathfind(Grid grid){
        this.grid = grid;
    }
    
    public Set<Pair<Integer, Integer>> dfsFindPaths(Pair<Integer, Integer> startingPoint, GridDirection startingDirection){
        Set<Pair<Pair<Integer, Integer>, GridDirection>> visited = new HashSet<>();
        Stack<Pair<Pair<Integer, Integer>, GridDirection>> stack = new Stack<>();
        // POINT AND DIRECTION IS POINT AND DIRECTION COMING INTO THE POINT, IE PREVIOUS HEADING
        Pair<Pair<Integer, Integer>, GridDirection> pointAndDirection = new Pair<>(startingPoint, startingDirection);
        stack.add(pointAndDirection);
        
        while (!stack.isEmpty()){
            Pair<Pair<Integer, Integer>, GridDirection> currentPointAndDirection = stack.pop();
            
            if (visited.contains(currentPointAndDirection)){
                continue;
            }
            
            visited.add(currentPointAndDirection);
            
            int x = currentPointAndDirection.getFirst().getFirst();
            int y = currentPointAndDirection.getFirst().getSecond();
            
            char symbol = grid.getSymbol(x, y);
            
            for (GridDirection direction: getDirections(symbol, currentPointAndDirection.getSecond())){
                Pair<Integer, Integer> newCoordinate = addPoints(currentPointAndDirection.getFirst(), direction.toPair());
                if (newCoordinate.getFirst() < 0 || newCoordinate.getFirst() >= grid.getWidth() || newCoordinate.getSecond() < 0 || newCoordinate.getSecond() >= grid.getHeight()){
                    continue;
                }
                stack.add(new Pair<>(newCoordinate, direction));
            }
            // VISUALIZER
//            printVisitedGrid(grid, visited.stream().map(Pair::getFirst).collect(Collectors.toSet()));
//            try {
//                // pause the thread for 1000 milliseconds, which is 1 second
//                Thread.sleep(1000);
//                System.out.println("--------");
//            } catch (InterruptedException ex) {
//                // handle the exception...
//                Thread.currentThread().interrupt(); // restore interrupted status
//            }
            
        }
        
        return visited.stream()
                .map(Pair::getFirst)
                .collect(Collectors.toSet());
    }
    
    public Set<Pair<Integer, Integer>> dfsFindBestStart(){
        Stack<Pair<Pair<Integer, Integer>, GridDirection>> startingSet = new Stack<>();
        
        for (int i = 0; i < grid.getWidth(); i++){
            Pair<Integer, Integer> coord = new Pair<>(i, 0);
            startingSet.add(new Pair<>(coord, GridDirection.DOWN));
            
            Pair<Integer, Integer> coord2 = new Pair<>(i, grid.getHeight() - 1);
            startingSet.add(new Pair<>(coord2, GridDirection.UP));
        }
        
        for (int i = 0; i < grid.getHeight(); i++){
            Pair<Integer, Integer> coord = new Pair<>(0, i);
            startingSet.add(new Pair<>(coord, GridDirection.RIGHT));
            
            Pair<Integer, Integer> coord2 = new Pair<>(grid.getWidth() - 1, i);
            startingSet.add(new Pair<>(coord2, GridDirection.LEFT));
        }
        
        int best = 0;
        Set<Pair<Integer, Integer>> bestSet = null;
        
        for (Pair<Pair<Integer, Integer>, GridDirection> element: startingSet){
            Set<Pair<Integer, Integer>> currentPath = dfsFindPaths(element.getFirst(), element.getSecond());
            int energizedCount = countEnergizedCells(currentPath);
            if (energizedCount > best){
                best = energizedCount;
                bestSet = currentPath;
            }
        }
        
        return bestSet;
    }
    
    public int countEnergizedCells(Set<Pair<Integer, Integer>> foundCoords) {
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
    
    public List<GridDirection> getDirections(char symbol, GridDirection inboundDirection){
        return switch (symbol){
            case '/' -> GridSymbol.FORWARDSLASH.getDirections(inboundDirection);
            case '\\' -> GridSymbol.BACKSLASH.getDirections(inboundDirection);
            case '|' -> GridSymbol.PIPE.getDirections(inboundDirection);
            case '-' -> GridSymbol.HYPHEN.getDirections(inboundDirection);
            case '.' -> GridSymbol.PERIOD.getDirections(inboundDirection);
            default -> new ArrayList<>();
        };
    }

    private Pair<Integer, Integer> addPoints(Pair<Integer, Integer> first, Pair<Integer, Integer> second){
        int x = first.getFirst() + second.getFirst();
        int y = first.getSecond() + second.getSecond();
        
        return new Pair<>(x, y);
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

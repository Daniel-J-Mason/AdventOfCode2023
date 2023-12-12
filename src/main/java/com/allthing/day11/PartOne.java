package com.allthing.day11;

import com.allthing.parser.FileParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PartOne {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        
        List<String> lines = fileParser.toStringArray("11_DayEleven_PartOne.txt");
        
        Galaxy galaxy = new Galaxy(lines);
        
        galaxy.printGrid();
        
        System.out.println(galaxy.getTotalDistances());
    }
    
    public static class Galaxy{
        private char[][] grid;
        
        public Galaxy(List<String> lines){
            grid = new char[lines.size()][lines.getFirst().length()];
            
            int i = 0;
            for (String line: lines){
                grid[i] = line.toCharArray();
                i++;
            }
            
            grid = expandGalaxy(grid);
        }
        
        public int getTotalDistances(){
            int sum = 0;
            List<Node> galaxyNodes = getGalaxyNodes();
            for (int i = 0; i < galaxyNodes.size(); i++){
                for (int remaining = i + 1; remaining < galaxyNodes.size(); remaining++){
                    sum += getShortestPath(galaxyNodes.get(i), galaxyNodes.get(remaining));
                }
            }
            return sum;
        }
        
        private int getShortestPath(Node first, Node second){
            return (Math.abs(second.x - first.x) + Math.abs(second.y - first.y));
        }
        
        private List<Node> getGalaxyNodes(){
            List<Node> result = new ArrayList<>();
            
            
            for (int i = 0; i < grid.length; i++){
                for (int j = 0; j < grid[0].length; j++){
                    if (grid[i][j] == '#') {
                        result.add(new Node(j, i));
                    }
                }
            }
            
            return result;
        }
        
        public void printGrid(){
            for (int y = 0; y< grid.length; y++){
                for (int x = 0; x < grid[0].length; x++){
                    System.out.print(grid[y][x]);
                }
                System.out.println();
            }
        }
        
        private char[][] expandGalaxy(char[][] originalGalaxy){
            return expandVertically(expandHorizontally(originalGalaxy));
        }
        
        private char[][] expandVertically(char[][] inputGalaxy){
            List<Integer> emptyRows = new ArrayList<>();
            
            for (int i = 0; i < inputGalaxy.length; i++){
                if (isEmptyRow(inputGalaxy, i)){
                    emptyRows.add(i);
                }
            }
            
            int insertCounter = 0;
            char[][] expandedGalaxy = new char[inputGalaxy.length + emptyRows.size()][inputGalaxy[0].length];
            
            char[] blankrow = new char[inputGalaxy[0].length];
            for (int i = 0; i < inputGalaxy[0].length; i++){
                blankrow[i] = '.';
            }
            
            
            
            for (int i = 0; i < inputGalaxy.length; i++){
                if (emptyRows.contains(i)){
                    expandedGalaxy[i + insertCounter] = blankrow;
                    insertCounter++;
                }
                expandedGalaxy[i + insertCounter] = inputGalaxy[i];
            }
            
            return expandedGalaxy;
        }
        
        private char[][] expandHorizontally(char[][] inputGalaxy){
            List<Integer> emptyColumns = new ArrayList<>();
            for (int i = 0; i < inputGalaxy[0].length; i++){
                if (isEmptyColumn(inputGalaxy, i)){
                    emptyColumns.add(i);
                }
            }
            
            int insertCounter = 0;
            char[][] expandedGalaxy = new char[inputGalaxy.length][inputGalaxy[0].length + emptyColumns.size()];
            
            
            
            for (int i = 0; i < inputGalaxy.length; i++){      // 'i' is used for rows
                for (int j = 0; j < inputGalaxy.length; j++){
                    if (emptyColumns.contains(j)){
                        expandedGalaxy[i][j + insertCounter] = '.';
                        insertCounter ++;
                    }
                    expandedGalaxy[i][j +insertCounter] = inputGalaxy[i][j];
                }
                insertCounter = 0;
            }

            
            return expandedGalaxy;
            
        }
        
        private boolean isEmptyColumn(char[][] gridCheck, int column){
            for (int i = 0; i < gridCheck.length; i++){
                if (gridCheck[i][column] != '.'){
                    return false;
                }
            }
            
            return true;
        }
        
        private boolean isEmptyRow(char[][] gridCheck, int row){
            for (int i = 0; i < gridCheck[0].length; i ++){
                if (gridCheck[row][i] != '.'){
                    return false;
                }
            }
            
            return true;
        }
        
        private static class Node{
            int x;
            int y;
            
            public Node(int x, int y){
                this.x = x;
                this.y = y;
            }
            
            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Node node)) return false;
                return  x == node.x && y == node.y;
            }
            
            @Override
            public int hashCode() {
                return Objects.hash(x, y);
            }
        }
    }
}

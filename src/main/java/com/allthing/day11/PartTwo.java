package com.allthing.day11;

import com.allthing.parser.FileParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PartTwo {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        
        List<String> lines = fileParser.toStringArray("11_DayEleven_PartTwo.txt");
        
        Galaxy galaxy = new Galaxy(lines, 999999);
        
        galaxy.printGrid();
        
        System.out.println(galaxy.getTotalDistances());
    }
    
    public static class Galaxy{
        private char[][] grid;
        private List<Long> emptyRows;
        private List<Long> emptyColumns;
        private int expansionValue;
        
        public Galaxy(List<String> lines, int expansionValue){
            grid = new char[lines.size()][lines.getFirst().length()];
            this.expansionValue = expansionValue;
            
            int i = 0;
            for (String line: lines){
                grid[i] = line.toCharArray();
                i++;
            }
            
            emptyRows = getEmptyRows();
            emptyColumns = getEmptyColumns();
            
            System.out.println(emptyRows);
            System.out.println(emptyColumns);
            
        }
        
        public long getTotalDistances(){
            long sum = 0;
            List<Node> galaxyNodes = getGalaxyNodes();
            for (int i = 0; i < galaxyNodes.size(); i++){
                for (int remaining = i + 1; remaining < galaxyNodes.size(); remaining++){
                    sum += getShortestPath(galaxyNodes.get(i), galaxyNodes.get(remaining));
                }
            }
            return sum;
        }
        
        private List<Long> getEmptyColumns(){
            List<Long> emptyRows = new ArrayList<>();
            
            for (int i = 0; i < grid.length; i++){
                if (isEmptyColumn(grid, i)){
                    emptyRows.add((long)i);
                }
            }
            
            
            
            return emptyRows;
        }
        
        private List<Long> getEmptyRows(){
            List<Long> emptyColumns = new ArrayList<>();
            for (int i = 0; i < grid[0].length; i++){
                if (isEmptyRow(grid, i)){
                    emptyColumns.add((long)i);
                }
            }
            
            return emptyColumns;
        }
        
        private long getShortestPath(Node first, Node second){
            // System.out.println("Node 1: " + first.x + ", " + first.y);
            // System.out.println("Node 2: " + second.x + ", " + second.y);
            
            long minX = Math.min(first.x, second.x);
            long maxX = Math.max(first.x, second.x);
            
            long minY = Math.min(first.y, second.y);
            long maxY = Math.max(first.y, second.y);
            
            // System.out.println(maxX);
            // System.out.println(maxY);
            
            long expandedX = maxX;
            
            for (long x = minX; x < maxX; x++){
                if (emptyColumns.contains(x)){
                    expandedX += expansionValue;
                }
            }
            
            long expandedY = maxY;
            
            for (long y = minY; y < maxY; y++){
                if (emptyRows.contains(y)){
                    expandedY += expansionValue;
                }
            }
            
            //System.out.println("Expanded: " + expandedX + ", " + expandedY);
            
            
            
            long distance = (expandedY - minY + expandedX - minX);
            
            return distance;
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

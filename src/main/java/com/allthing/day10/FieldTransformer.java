package com.allthing.day10;

import com.allthing.parser.FileParser;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FieldTransformer {
    
    private List<Pair<Integer, Integer>> pathCoords;
    private Map<Pair<Integer, Integer>, Field.Node> pathNodes;
    private List<String> newInputArray;
    
    public FieldTransformer(Field field){
        pathCoords = field.getPathNodeCoords();
        pathNodes = field.getPathNodes();
        newInputArray =inlayPeriodsAndPath(field.getWidth(), field.getHeight());
    }
    
    private List<String> inlayPeriodsAndPath(int width, int height){
        List<String> result = new ArrayList<>();
        for (int y = 0; y < height; y++){
            StringBuilder line = new StringBuilder();
            for (int x = 0; x < width; x++ ){
                if (pathCoords.contains(Pair.of(x, y))){
                    line.append(pathNodes.get(Pair.of(x, y)).pipeType);
                } else {
                    line.append('.');
                }
            }
            result.add(line.toString());
        }
        
        return result;
    }
    
    public List<String> doubleGridSize(){
        List<String> originalArray = newInputArray;
        char[][] oldGrid = new char[originalArray.size()][originalArray.getFirst().length()];
        for (int i = 0; i < originalArray.size(); i++){
            for (int j = 0; j < originalArray.getFirst().length(); j++){
                oldGrid[i][j] = originalArray.get(i).charAt(j);
            }
        }
        
        int newHeight = 2 * originalArray.size() - 1;
        int newWidth = 2 * originalArray.getFirst().length() -1;
        
        char[][] newGrid = new char[newHeight][newWidth];
        
        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                if (y % 2 != 0) {
                    // if the row is one of the new ones, fill with '#'
                    newGrid[y][x] = ' ';
                } else if (x % 2 != 0) {
                    // if the column is one of the new ones, fill with '#'
                    newGrid[y][x] = ' ';
                } else {
                    // else copy the is the original cell to the new grid
                    newGrid[y][x] = oldGrid[y / 2][x / 2];
                }
            }
        }
        
        newGrid = connectPipes(newGrid);
        
        List<String> reformatted = new ArrayList<>();
        for (int y =0; y < newGrid.length; y++){
            char[] row = newGrid[y];
            reformatted.add(String.valueOf(row));
        }
        
        reformatted.forEach(System.out::println);
        
        return reformatted;
    }
    
    private char[][] connectPipes(char[][] orginalGrid){
        int height = orginalGrid.length;
        int width = orginalGrid[0].length;
        
        char[][] newGrid = new char[height][width];
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (checkLeft(y, x, orginalGrid) && checkRight(y, x, orginalGrid)){
                    newGrid[y][x] = '-';
                } else if (checkUp(y, x, orginalGrid) && checkDown(y, x, orginalGrid)){
                    newGrid[y][x] = '|';
                } else {
                    newGrid[y][x] = orginalGrid[y][x];
                }
            }
        }
        
        return newGrid;
    }
    
    private boolean checkLeft(int i, int j, char[][] grid) {
        if (j > 0) {
            char leftNeighbour = grid[i][j - 1];
            return leftNeighbour == 'S' || leftNeighbour == 'F' || leftNeighbour == 'L' || leftNeighbour == '-';
        }
        return false; // No left neighbour
    }
    
    private boolean checkRight(int i, int j, char[][] grid) {
        if (j < grid[0].length - 1) {
            char rightNeighbour = grid[i][j + 1];
            return rightNeighbour == 'S' || rightNeighbour == '7' || rightNeighbour == 'J' || rightNeighbour == '-';
        }
        return false; // No right neighbour
    }
    
    private boolean checkUp(int i, int j, char[][] grid) {
        if (i > 0) {
            char upNeighbour = grid[i - 1][j];
            return upNeighbour == 'S' || upNeighbour == 'F' || upNeighbour == '7' || upNeighbour == '|';
        }
        return false; // No up neighbour
    }
    
    private boolean checkDown(int i, int j, char[][]grid) {
        if (i < grid.length - 1) {
            char downNeighbour = grid[i + 1][j];
            return downNeighbour == 'S' || downNeighbour == 'J' || downNeighbour == 'L' || downNeighbour == '|';
        }
        return false; // No down neighbour
    }
}

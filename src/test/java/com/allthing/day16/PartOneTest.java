package com.allthing.day16;

import com.allthing.parser.FileParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PartOneTest {
    FileParser fileParser;
    List<String> lines;
    
    @BeforeEach
    void setUp(){
        fileParser = new FileParser();
        lines = fileParser.toStringArray("16_DaySixteen_PartOne_Test.txt");
        
    }
    
    @Test
    void correctEnergizedCount(){
        Grid testGrid = new Grid(lines);
        Pathfind pathfind = new Pathfind(testGrid);
        Assertions.assertEquals(46, PartOne.countEnergizedCells(testGrid, pathfind.dfsFindPaths(new Pair<>(0, 0), GridDirection.RIGHT)));
    }
}

package com.allthing.day16;

import com.allthing.parser.FileParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PartTwoTest {
    FileParser fileParser;
    List<String> lines;
    
    @BeforeEach
    void setUp(){
        fileParser = new FileParser();
        lines = fileParser.toStringArray("16_DaySixteen_PartTwo_Test.txt");
    }
    
    @Test
    void correctBest(){
        Grid testGrid = new Grid(lines);
        Pathfind pathfind = new Pathfind(testGrid);
        Assertions.assertEquals(51, PartOne.countEnergizedCells(testGrid, pathfind.dfsFindBestStart()));
    }
}

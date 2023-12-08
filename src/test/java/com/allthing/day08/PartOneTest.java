package com.allthing.day08;

import com.allthing.parser.FileParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import com.allthing.day08.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartOneTest {
    List<String> lines;
    
    @BeforeEach
    void setUp(){
        FileParser fileParser = new FileParser();
        lines = fileParser.toStringArray("08_DayEight_PartOne_Test.txt");
    }
    
    @Test
    void correctCount(){
         assertEquals(6, PartOne.stepsToComplete(lines));
    }
}

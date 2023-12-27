package com.allthing.day14;

import com.allthing.parser.FileParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartOneTest {
    FileParser fileParser;
    List<String> lines;
    
    @BeforeEach
    void setUp(){
        fileParser = new FileParser();
        lines = fileParser.toStringArray("14_DayFourteen_PartOne_Test.txt");
    }
    
    @Test
    void correctSum(){
        assertEquals(136, PartOne.sumOfColumns(lines));
    }
}

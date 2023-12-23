package com.allthing.day13;

import com.allthing.parser.FileParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartTwoTest {
    FileParser fileParser;
    List<String> lines;
    
    @BeforeEach
    void setUp(){
        fileParser = new FileParser();
        lines = fileParser.toStringArray("13_DayThirteen_PartTwo_Test.txt");
    }
    
    @Test
    void correctSum(){
        assertEquals(400, PartTwo.sumOfGridValues(lines));
    }
}

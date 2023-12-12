package com.allthing.day11;

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
        lines = fileParser.toStringArray("11_DayEleven_PartOne_Test.txt");
    }
    
    @Test
    void correctSum(){
        PartOne.Galaxy galaxy = new PartOne.Galaxy(lines);
        
        assertEquals(374, galaxy.getTotalDistances());
    }
}

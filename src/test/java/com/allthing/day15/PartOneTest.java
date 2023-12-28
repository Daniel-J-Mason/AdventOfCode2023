package com.allthing.day15;

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
        lines = fileParser.toStringArray("15_DayFifteen_PartOne_Test.txt", ",");
    }
    
    @Test
    void singleHash(){
        assertEquals(52, PartOne.stringHash("HASH", 0));
    }
    
    @Test
    void hashFromTestInput(){
        assertEquals(1320, PartOne.hashSumFromInput(lines));
    }
}

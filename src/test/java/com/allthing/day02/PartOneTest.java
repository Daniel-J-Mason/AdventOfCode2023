package com.allthing.day02;

import com.allthing.parser.FileParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PartOneTest {
    
    List<String> gameValues;
    
    @BeforeEach
    void setUp(){
        FileParser fileParser = new FileParser();
        gameValues = fileParser.toStringArray("02_DayTwo_PartOne_Test.txt");
    }
    
    @Test
    void totalParseTest(){
        assertEquals(8, PartOne.sumOfValidGameIds(gameValues));
    }

}
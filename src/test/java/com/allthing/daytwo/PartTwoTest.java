package com.allthing.daytwo;

import com.allthing.parser.FileParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PartTwoTest {
    
    List<String> gameValues;
    
    @BeforeEach
    void setUp(){
        FileParser fileParser = new FileParser();
        gameValues = fileParser.toStringArray("02_DayTwo_PartOne_Test.txt");
    }
    
    @Test
    void totalParseTest(){
        assertEquals(2286, PartTwo.sumOfValidGameIds(gameValues));
    }

}
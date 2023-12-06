package com.allthing.day06;

import com.allthing.parser.FileParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PartOneTest {
    List<String> lines;
    
    @BeforeEach
    void setUp(){
        FileParser fileParser = new FileParser();
        lines = fileParser.toStringArray("06_DaySix_PartOne_Test.txt");
        lines.add("");
    }
    
    @ParameterizedTest
    @CsvSource({
            "7, 9, 4",
            "15, 40, 8",
            "30, 200, 9",
    })
    void singleTest(int time, int distance, int expectedCount){
        assertEquals(expectedCount, PartOne.calculateWinConditionCount(time, distance));
    }
    
}
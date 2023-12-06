package com.allthing.day06;

import com.allthing.parser.FileParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PartTwoTest {
    List<String> lines;
    
    @BeforeEach
    void setUp(){
        FileParser fileParser = new FileParser();
        lines = fileParser.toStringArray("06_DaySix_PartOne_Test.txt");
    }
    
    @ParameterizedTest
    @CsvSource({
            "71530, 940200, 71503",
    })
    void singleTest(int time, int distance, int expectedCount){
        assertEquals(expectedCount, PartTwo.calculateWinConditionCount(time, distance));
    }
    
}
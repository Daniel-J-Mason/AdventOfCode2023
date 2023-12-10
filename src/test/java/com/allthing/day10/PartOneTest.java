package com.allthing.day10;

import com.allthing.parser.FileParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartOneTest {
    FileParser fileParser;
    
    @BeforeEach
    void setUp(){
        fileParser = new FileParser();
    }
    
    @ParameterizedTest
    @CsvSource({
            "4, 10_DayTen_PartOne_Test1.txt",
            "8, 10_DayTen_PartOne_Test2.txt",
    })
    void correctCount(int expected, String inputFile){
        List<String> lines = fileParser.toStringArray(inputFile);
        
        Field field = new Field(lines);
        int halfDistance = field.getMaxSteps() / 2 + 1;
         assertEquals(expected, halfDistance);
    }
}

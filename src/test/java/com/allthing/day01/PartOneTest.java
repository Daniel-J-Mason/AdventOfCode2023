package com.allthing.day01;

import com.allthing.parser.FileParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PartOneTest {
    List<String> calibrationValues;
    
    @BeforeEach
    void setUp(){
        FileParser fileParser = new FileParser();
        calibrationValues = fileParser.toStringArray("01_DayOne_PartOne_Test.txt");
    }
    
    @Test
    void CorrectSumTest(){
        Integer actual = PartOne.sumOfAll(calibrationValues);
        assertEquals(142, actual);
    }
}
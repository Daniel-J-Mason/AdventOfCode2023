package com.allthing.day12;

import com.allthing.day11.PartTwo;
import com.allthing.parser.FileParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartOneTest {
    FileParser fileParser;
    List<String> lines;
    
    @BeforeEach
    void setUp(){
        fileParser = new FileParser();
        lines = fileParser.toStringArray("12_DayTwelve_PartOne_Test.txt");
    }
    
    @Test
    void correctSum(){
        assertEquals(21, PartOne.sumLines(lines));
    }
}

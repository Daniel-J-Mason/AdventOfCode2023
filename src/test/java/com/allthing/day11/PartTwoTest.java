package com.allthing.day11;

import com.allthing.parser.FileParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartTwoTest {
    FileParser fileParser;
    List<String> lines;
    
    @BeforeEach
    void setUp(){
        fileParser = new FileParser();
        lines = fileParser.toStringArray("11_DayEleven_PartTwo_Test.txt");
    }
    
    @ParameterizedTest
    @CsvSource({
            "374, 1",
            "1030, 9",
            "8410, 99",
    })
    void correctSum(int expected, int expansionValue){
        PartTwo.Galaxy galaxy = new PartTwo.Galaxy(lines, expansionValue);
        
        assertEquals(expected, galaxy.getTotalDistances());
    }
}

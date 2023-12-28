package com.allthing.day15;

import com.allthing.parser.FileParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartTwoTest {
    FileParser fileParser;
    List<String> lines;
    
    @BeforeEach
    void setUp(){
        fileParser = new FileParser();
        lines = fileParser.toStringArray("15_DayFifteen_PartTwo_Test.txt", ",");
    }
    
    @Test
    void correctTotalFocusPower(){
        assertEquals(145, PartTwo.totalFocusingPower(lines));
    }
}

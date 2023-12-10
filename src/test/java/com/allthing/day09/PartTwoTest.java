package com.allthing.day09;

import com.allthing.day09.PartOne;
import com.allthing.day09.PartTwo;
import com.allthing.parser.FileParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartTwoTest {
    List<String> lines;
    
    @BeforeEach
    void setUp(){
        FileParser fileParser = new FileParser();
        lines = fileParser.toStringArray("09_DayNine_PartTwo_Test.txt");
    }
    
    @Test
    void correctCount(){
         assertEquals(2, PartTwo.getTotal(lines));
    }
}

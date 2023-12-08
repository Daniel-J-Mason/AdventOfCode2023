package com.allthing.day08;

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
        lines = fileParser.toStringArray("08_DayEight_PartTwo_Test.txt");
    }
    
    @Test
    void correctCount(){
         assertEquals(6, PartTwo.lowestStepsToCoincide(lines));
    }
}

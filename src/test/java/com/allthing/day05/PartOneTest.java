package com.allthing.day05;

import com.allthing.day04.ScratchCard;
import com.allthing.parser.FileParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PartOneTest {
    List<String> almanac;
    
    @BeforeEach
    void setUp(){
        FileParser fileParser = new FileParser();
        almanac = fileParser.toStringArray("05_DayFive_PartOne_Test.txt");
        almanac.add("");
    }
    
    @ParameterizedTest
    @CsvSource({
            "79, 82",
            "14, 43",
            "55, 86",
            "13, 35"
    })
    void singleTest(long seed, long expectedLocation){
        assertEquals(expectedLocation, PartOne.calculateSeed(seed, almanac));
    }
}
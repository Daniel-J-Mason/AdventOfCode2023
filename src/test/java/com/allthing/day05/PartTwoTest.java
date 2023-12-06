package com.allthing.day05;

import com.allthing.parser.FileParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PartTwoTest {
    List<String> almanac;
    
    @BeforeEach
    void setUp(){
        FileParser fileParser = new FileParser();
        almanac = fileParser.toStringArray("05_DayFive_PartTwo_Test.txt");
        almanac.add("");
    }
    
    @Test
    void correctMinimum(){
        List<PartTwo.PseudoRange> seeds = PartTwo.collectPseudoSeedsFromAlmanac(almanac.get(0));
        assertEquals(46, PartTwo.calculateLowestDistanceFromAllSeeds(seeds, PartTwo.parseAlmanacSteps(almanac)));
    }
}
package com.allthing.daythree;

import com.allthing.parser.FileParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PartTwoTest {
    
    List<String> schematicInput;
    
    @BeforeEach
    void setUp(){
        FileParser fileParser = new FileParser();
        schematicInput = fileParser.toStringArray("03_DayThree_PartOne_Test.txt");
    }
    
    @Test
    void partNumberCheck(){
        Schematic schematic = new Schematic(schematicInput);
        assertEquals(467835, schematic.calculateGearRatio());
    }
    
}
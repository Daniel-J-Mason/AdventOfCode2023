package com.allthing.day03;

import com.allthing.parser.FileParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PartOneTest {
    
    List<String> schematicInput;
    
    @BeforeEach
    void setUp(){
        FileParser fileParser = new FileParser();
        schematicInput = fileParser.toStringArray("03_DayThree_PartOne_Test.txt");
    }
    
    @Test
    void partNumberCheck(){
        Schematic schematic = new Schematic(schematicInput);
        assertEquals(4361, schematic.calculatePartNumber());
    }
    
}
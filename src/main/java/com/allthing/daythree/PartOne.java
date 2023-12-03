package com.allthing.daythree;

import com.allthing.parser.FileParser;

import java.util.ArrayList;
import java.util.List;

public class PartOne {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        
        List<String> schematicInput = fileParser.toStringArray("03_DayThree_PartOne.txt");
        
        
        Schematic schematic = new Schematic(schematicInput);
        
        schematic.printGrid();
        
        System.out.println(schematic.calculatePartNumber());
    }
}

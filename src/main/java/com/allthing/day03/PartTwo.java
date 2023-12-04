package com.allthing.day03;

import com.allthing.parser.FileParser;

import java.util.List;

public class PartTwo {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        
        List<String> schematicInput = fileParser.toStringArray("03_DayThree_PartTwo.txt");
        
        
        Schematic schematic = new Schematic(schematicInput);
        
        schematic.printGrid();
        
        System.out.println(schematic.calculateGearRatio());
    }
}

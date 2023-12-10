package com.allthing.day10;

import com.allthing.parser.FileParser;

import java.util.LinkedList;
import java.util.List;

public class PartOne {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        
        List<String> array = fileParser.toStringArray("10_DayTen_PartOne.txt");
        
        Field field = new Field(array);
        
        int maxSteps = field.getMaxSteps();
        
        System.out.println(maxSteps / 2 + 1);
    }
    

}

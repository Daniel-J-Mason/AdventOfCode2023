package com.allthing.day10;

import com.allthing.parser.FileParser;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class PartTwo {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        
        List<String> array = fileParser.toStringArray("10_DayTen_PartOne.txt");
        
        FieldTransformer fieldTransformer = new FieldTransformer(new Field(array));
        
        FloodFiller floodFiller = new FloodFiller(fieldTransformer.doubleGridSize());
        
        System.out.println(floodFiller.countEnclosed());
        
    }
}

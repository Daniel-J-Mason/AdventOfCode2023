package com.allthing.day04;

import com.allthing.parser.FileParser;

import java.util.List;

public class PartOne {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        
        List<String> scratchcards = fileParser.toStringArray("04_DayFour_PartOne.txt");
        
        double sum = 0;
        for (String scratchcard: scratchcards){
            ScratchCard scratchCard = new ScratchCard(scratchcard);
            sum+= scratchCard.points();
        }
        
        System.out.println(sum);
    }
}

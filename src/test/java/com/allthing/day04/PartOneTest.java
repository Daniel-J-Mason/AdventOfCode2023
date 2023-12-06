package com.allthing.day04;

import com.allthing.parser.FileParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PartOneTest {
    List<String> scratchcards;
    
    @BeforeEach
    void setUp(){
        FileParser fileParser = new FileParser();
        scratchcards = fileParser.toStringArray("04_DayFour_PartOne_Test.txt");
    }
    
    @ParameterizedTest
    @CsvSource({
            "0, 8",
            "1, 2",
            "2, 2",
            "3, 1",
            "4, 0",
            "5, 0"
    })
    void singleTest(int index, int expected){
        ScratchCard scratchCard = new ScratchCard(scratchcards.get(index));
        System.out.println("Winning Numbers: " );
        scratchCard.winningNumbers().forEach(System.out::println);
        assertEquals(expected, scratchCard.points());
    }
}
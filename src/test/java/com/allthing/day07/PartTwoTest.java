package com.allthing.day07;

import com.allthing.parser.FileParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PartTwoTest {
    List<String> lines;
    
    @BeforeEach
    void setUp(){
        FileParser fileParser = new FileParser();
        lines = fileParser.toStringArray("07_DaySeven_PartTwo_Test.txt");
    }
    
    @Test
    void correctTotal(){
        List<CamelCardHandTwo> hands = PartTwo.createHandsList(lines);
        
        hands.forEach(hand -> System.out.printf("Hands: %s, Score: %s, Index: %s%n", hand.getCards(), hand.getWager(), hands.indexOf(hand)));
        
        assertEquals(5905, PartTwo.getTotalWinnings(hands));
    }

}
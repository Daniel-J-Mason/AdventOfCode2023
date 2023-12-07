package com.allthing.day07;

import com.allthing.parser.FileParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PartOneTest {
    List<String> lines;
    
    @BeforeEach
    void setUp(){
        FileParser fileParser = new FileParser();
        lines = fileParser.toStringArray("07_DaySeven_PartOne_Test.txt");
    }
    
    @Test
    void correctTotal(){
        List<CamelCardHand> hands = PartOne.createHandsList(lines);
        
        hands.forEach(hand -> System.out.printf("Hands: %s, Score: %s, Index: %s%n", hand.getCards(), hand.getWager(), hands.indexOf(hand)));
        
        assertEquals(6440, PartOne.getTotalWinnings(hands));
    }

}
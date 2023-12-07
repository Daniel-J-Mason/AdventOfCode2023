package com.allthing.day07;

import com.allthing.parser.FileParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PartTwo {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        
        List<String> lines = fileParser.toStringArray("07_DaySeven_PartOne.txt");
        
        List<CamelCardHandTwo> hands = createHandsList(lines);
        
        System.out.println(getTotalWinnings(hands));
        
        
    }
    
    public static int getTotalWinnings(List<CamelCardHandTwo> allHands){
        ;
        
        allHands.forEach(hand -> System.out.println(hand.getCards() + " " + hand.getBestHand() + ": wager=" + hand.getWager() + " index multipliedBy=" + (allHands.indexOf(hand) + 1) + " score:" + hand.score()));
        //allHands.forEach(hand -> System.out.println(hand.getCards()));
        
        return  allHands.stream()
                .mapToInt(hand -> (allHands.indexOf(hand) + 1) * hand.getWager())
                .sum();
        
    }
    
    public static List<CamelCardHandTwo> createHandsList(List<String> inputArray){
        List<CamelCardHandTwo> result = new ArrayList<>();
        
        inputArray.forEach(line -> result.add(parseHand(line)));
        
        Collections.sort(result);
        
        return result;
    }
    
    private static CamelCardHandTwo parseHand(String line){
        String[] split = line.split(" ");
        return new CamelCardHandTwo(split[0], Integer.parseInt(split[1]));
    }
}

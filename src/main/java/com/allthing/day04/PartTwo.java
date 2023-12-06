package com.allthing.day04;

import com.allthing.parser.FileParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: ADD TESTS AND REWRITE WITH QUEUE's BETTER SOLUTION
public class PartTwo {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        
        List<String> scratchcards = fileParser.toStringArray("04_DayFour_PartOne.txt");
        
        
        Map<Integer, Integer> numberAndCount = new HashMap<>();
        for (String scratchcard: scratchcards){
            ScratchCard scratchCard = new ScratchCard(scratchcard);
            int count = scratchCard.numberOfWinningMatches();
            
            if (numberAndCount.containsKey(scratchCard.getCardNumber())){
                numberAndCount.put(scratchCard.getCardNumber(), numberAndCount.get(scratchCard.getCardNumber()) + 1);
            } else {
                numberAndCount.put(scratchCard.getCardNumber(), 1);
            }
            
            int numberOfCopies;
            
            numberOfCopies = numberAndCount.getOrDefault(scratchCard.getCardNumber(), 1);
            
            System.out.println("Outer loop starting with card: " + scratchCard.getCardNumber());
            
            for (int j = 0; j < numberOfCopies; j++) {
                for (int i = 0; i < count; i++) {
                    int cardNumber = scratchCard.getCardNumber() + i + 1;
                    if (numberAndCount.containsKey(cardNumber)) {
                        numberAndCount.put(cardNumber, numberAndCount.get(cardNumber) + 1);
                        
                    } else {
                        numberAndCount.put(cardNumber, 1);
                    }
                }
            }
        }
        
        numberAndCount.forEach((n, c) -> System.out.println("Card: " + n + " Count: " + c));
        
        int total = numberAndCount.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
        
        System.out.println(total);
    }
}

package com.allthing.day07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CamelCardHandTwo implements Comparable<CamelCardHandTwo> {
    
    private String cards;
    private int wager;
    private List<Character> cardOrdering =
            Arrays.asList('A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J');
    
    
    public CamelCardHandTwo(String cards, int wager) {
        this.cards = cards;
        this.wager = wager;
    }
    
    public String getCards() {
        return cards;
    }
    
    public int getWager() {
        return wager;
    }
    
    public String getBestHand() {
        Map<Character, Integer> cardAndCount = new HashMap<>();
        
        for (Character card : cards.toCharArray()) {
            cardAndCount.put(card, cardAndCount.getOrDefault(card, 0) + 1);
        }
        
        int maxCount = 0;
        List<Character> mostChars = new ArrayList<>(); // Can have multiple
        
        for (Character character : cardAndCount.keySet()) {
            
            if (character == 'J') {
                continue;
            }
            
            int count = cardAndCount.get(character);
            if (count > maxCount) {
                maxCount = count;
                mostChars.clear();
                mostChars.add(character);
            } else if (count == maxCount) {
                mostChars.add(character);
            }
        }
        
        char highestRanked = mostChars.stream()
                .max(Comparator.comparingInt(cardOrdering::indexOf)).orElse('-');
        
        
        return cards.replace('J', highestRanked);
    }
    
    public int score() {
        Map<Character, Integer> cardAndCount = new HashMap<>();
        
        for (Character card : getBestHand().toCharArray()) {
            cardAndCount.put(card, cardAndCount.getOrDefault(card, 0) + 1);
        }
        
        int highestCount = 0;
        
        for (Integer value : cardAndCount.values()) {
            if (value > highestCount) {
                highestCount = value;
            }
        }
        
        
        return switch (highestCount) {
            case 5 -> 6;
            case 4 -> 5;
            case 3 -> isFullHouse() ? 4 : 3;
            case 2 -> isTwoPair() ? 2 : 1;
            case 1 -> 0;
            default -> Integer.MIN_VALUE;
        };
    }
    
    public boolean isFullHouse() {
        Map<Character, Integer> cardAndCount = new HashMap<>();
        
        for (Character card : getBestHand().toCharArray()) {
            cardAndCount.put(card, cardAndCount.getOrDefault(card, 0) + 1);
        }
        
        return cardAndCount.size() == 2 && cardAndCount.containsValue(2) && cardAndCount.containsValue(3);
    }
    
    
    public boolean isTwoPair() {
        Map<Character, Integer> cardAndCount = new HashMap<>();
        
        for (Character card : getBestHand().toCharArray()) {
            cardAndCount.put(card, cardAndCount.getOrDefault(card, 0) + 1);
        }
        
        
        int counter = 0;
        for (Integer value : cardAndCount.values()) {
            if (value == 2) {
                counter++;
            }
        }
        
        return counter == 2;
        
    }
    
    public int compareTo(CamelCardHandTwo other) {
        
        int score = Integer.compare(this.score(), other.score());
        if (score != 0) {
            return score;
        }
        
        for (int i = 0; i < cards.length(); i++) {
            char selfCard = cards.charAt(i);
            char otherCard = other.cards.charAt(i);
            
            
            if (selfCard != otherCard) {
                return cardOrdering.indexOf(otherCard) - cardOrdering.indexOf(selfCard);
            }
        }
        
        return 0;
    }
}

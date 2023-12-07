package com.allthing.day07;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CamelCardHand implements Comparable<CamelCardHand>{

    private String cards;
    private int wager;
    
    public CamelCardHand(String cards, int wager){
        this.cards = cards;
        this.wager = wager;
    }
    
    public String getCards() {
        return cards;
    }
    
    public int getWager() {
        return wager;
    }
    
    private int score(){
        Map<Character, Integer> cardAndCount = new HashMap<>();
        
        for (Character card: cards.toCharArray()){
            cardAndCount.put(card, cardAndCount.getOrDefault(card, 0) + 1);
        }
        
        int highestCount = 0;
        
        for (Integer value: cardAndCount.values()){
            if (value > highestCount){
                highestCount = value;
            }
        }
        
        return switch (highestCount) {
            case 5 -> 6;
            case 4 -> 5;
            case 3 -> isFullHouse() ? 4: 3;
            case 2 ->  isTwoPair() ? 2: 1;
            case 1 -> 0;
            default -> Integer.MIN_VALUE;
        };
    }
    
    public boolean isFullHouse(){
        Map<Character, Integer> cardAndCount = new HashMap<>();
        
        for (Character card: cards.toCharArray()){
            cardAndCount.put(card, cardAndCount.getOrDefault(card, 0) + 1);
        }
        
        return cardAndCount.size() == 2 && cardAndCount.containsValue(2) && cardAndCount.containsValue(3);
    }
    
    public boolean isTwoPair(){
        Map<Character, Integer> cardAndCount = new HashMap<>();
        
        for (Character card: cards.toCharArray()){
            cardAndCount.put(card, cardAndCount.getOrDefault(card, 0) + 1);
        }
        
        
        int counter = 0;
        for (Integer value: cardAndCount.values()){
            if (value == 2){
                counter++;
            }
        }
        
        return counter == 2;
        
    }
    
    public int compareTo(CamelCardHand other){
        List<Character> cardOrdering =
                Arrays.asList('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2');
        
        int score = Integer.compare(this.score(), other.score());
        System.out.println("Comparing " + this.cards + ":" + this.score() + " to " + other.cards + ":" + other.score());
        System.out.println("RESULT: " + score);
        if (score != 0){
            return score;
        }
        
        for (int i = 0; i < cards.length(); i++){
            char selfCard = cards.charAt(i);
            char otherCard = other.cards.charAt(i);
            
            System.out.println("HERE");
            System.out.println(selfCard);
            System.out.println(otherCard);
            
            if (selfCard != otherCard){
                System.out.println("Comparing " + selfCard + " to " + otherCard);
                if (cardOrdering.indexOf(otherCard) > cardOrdering.indexOf(selfCard)){
                    System.out.println(otherCard+" first");
                } else if ((cardOrdering.indexOf(otherCard) < cardOrdering.indexOf(selfCard))){
                    System.out.println(selfCard+" first");
                }
                return cardOrdering.indexOf(otherCard) - cardOrdering.indexOf(selfCard);
            }
        }
        
        return 0;
    }
}

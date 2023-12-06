package com.allthing.day04;

import java.util.Arrays;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ScratchCard {
    private final String card;
    
    public ScratchCard(String input){
        card = input;
    }
    
    public Set<Integer> winningNumbers(){
        String[] split = card.split("[:|]");
        
        String[] winningNumbers = split[1].split(" ");
        
        return Arrays.stream(winningNumbers)
                .toList()
                .stream()
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }
    
    public int numberOfWinningMatches(){
        String[] split = card.split("[:|]");
        
        String[] scratchOff = split[2].split(" ");
        
        int count = 0;
        
        for (String value: scratchOff){
            if (value.isEmpty()){
                continue;
            }
            if (winningNumbers().contains(Integer.parseInt(value))){
                count++;
            }
        }
        
        return count;
    }
    
    public double points(){
        if (numberOfWinningMatches() == 0){
            return 0;
        } else {
            return Math.pow(2, numberOfWinningMatches() - 1);
        }
    }
    
    public int getCardNumber(){
        String regex = "(\\d+):";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(card);
        
        if (matcher.find()){
            return Integer.parseInt(matcher.group(1));
        } else {
            return Integer.MIN_VALUE;
        }
    }
}

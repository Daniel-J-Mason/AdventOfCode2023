package com.allthing.day15;

import com.allthing.parser.FileParser;

import java.util.List;

public class PartOne {
    public static void main(String[] args) {
        FileParser fileParser  =new FileParser();
        List<String> inputs = fileParser.toStringArray("15_DayFifteen_PartOne.txt", ",");
        
        System.out.println(hashSumFromInput(inputs));
    }
    
    public static int hashSumFromInput(List<String> inputStrings){
    
        for (String input: inputStrings){
            System.out.println(input);
            System.out.println(stringHash(input, 0));
        }
        
        return inputStrings.stream()
                .mapToInt(string -> stringHash(string, 0))
                .sum();
    }
    
    public static int stringHash(String input, int initialHash){
        if (input.isEmpty()){
            return initialHash;
        } else {
            char currentChar = input.charAt(0);
            int nextHash = characterHash(currentChar, initialHash);
            return stringHash(input.substring(1), nextHash);
        }
    }
    
    public static int characterHash(char character, int startingHash){
        int hashValue = startingHash;
        
        hashValue += character;
        
        hashValue *= 17;
        
        hashValue %= 256;
        
        return hashValue;
    }
}

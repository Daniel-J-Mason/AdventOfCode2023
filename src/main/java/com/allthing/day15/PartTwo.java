package com.allthing.day15;

import com.allthing.parser.FileParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PartTwo {
    
    public static void main(String[] args) {
        FileParser fileParser  =new FileParser();
        List<String> inputs = fileParser.toStringArray("15_DayFifteen_PartTwo.txt", ",");
        
        System.out.println(totalFocusingPower(inputs));
    }
    
    /*
    TODO: Break down into simpler methods!
     */
    public static int totalFocusingPower(List<String> inputs){
        HashMap<Integer, List<String>> boxList = new HashMap<>();
        
        for (String input: inputs){
            if (input.contains("=")){
                String[] split = input.split("=");
                int boxNumber = stringHash(split[0], 0);
                
                List<String> box;
                if (boxList.get(boxNumber) == null){
                    box = new ArrayList<>();
                } else {
                    box = boxList.get(boxNumber);
                }
                
                int foundIndex = -1;
                
                for (int i = 0; i < box.size(); i++){
                    if (box.get(i).startsWith(split[0])){
                        foundIndex = i;
                    }
                }
                
                if (foundIndex == -1){
                    box.add(input);
                } else {
                    box.set(foundIndex, input);
                }
                
                boxList.put(boxNumber, box);
            } else {
                String[] split = input.split("-");
                int boxNumber = stringHash(split[0], 0);
                if (boxList.get(boxNumber) != null){
                    List<String> box = boxList.get(boxNumber);
                    for (int i = 0; i < box.size(); i++){
                        if (box.get(i).startsWith(split[0])){
                            box.remove(box.get(i));
                        }
                    }
                    boxList.put(boxNumber, box);
                }
            }
        }
        
        
        int sum = 0;
        for (Integer key: boxList.keySet()){
            List<String> box = boxList.get(key);
            
            int i = 1;
            for (String lense: box){
                String[] split = lense.split("=");
                String label = split[0];
                int focalLength = Integer.parseInt(split[1]);
                sum += ((stringHash(label, 0) + 1) * i * focalLength);
                i++;
            }
        }
        
        return sum;
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

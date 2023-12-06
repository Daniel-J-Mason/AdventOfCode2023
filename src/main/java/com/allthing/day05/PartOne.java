package com.allthing.day05;

import com.allthing.parser.FileParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartOne {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        
        List<String> almanac = fileParser.toStringArray("05_DayFive_PartOne.txt");
        almanac.add(""); //new empty string to trigger getAlmanacMaps correct;y
    
        List<Long> getDistancesForSeeds = new ArrayList<>();
        
        for (Long seed: getSeeds(almanac.get(0))){
            getDistancesForSeeds.add(calculateSeed(seed, almanac));
        }
        
        long minimumDistance = Collections.min(getDistancesForSeeds);
        
        System.out.println(minimumDistance);
    
    }
    
    public static List<Long> getSeeds(String line){
        List<Long> result = new ArrayList<>();
        
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        
        while (matcher.find()){
            result.add(Long.parseLong(matcher.group()));
        }
        
        return result;
    }
    
    public static Long calculateSeed(Long seed, List<String> almanac){
        List<Map<String, Long>> mapOfMaps = mapOfMaps(almanac);
        
        long result = seed;
        for (Map<String, Long> map: mapOfMaps){
            for (String range: map.keySet()){
                String[] split = range.split("-");
                long start = Long.parseLong(split[0]);
                long finish = Long.parseLong(split[1]);
                
                if ((start <= result) && (result < finish)){
                    result = result + map.get(range); // add delta
                    break;
                }
            }
        }
        
        return result;
    }
    
    public static List<Map<String, Long>> mapOfMaps(List<String> almanac){
        List<List<String>> almanacMaps = getAlmanacMaps(almanac);
        
        List<Map<String, Long>> result = new ArrayList<>();
        
        for (List<String> subSet: almanacMaps){
            result.add(createStepMap(subSet));
        }
        
        return result;
    }
    
    public static Map<String, Long> createStepMap(List<String> subSet){
        Map<String, Long> aggregateMap = new HashMap<>();
        
        for (String line: subSet){
            String[] inputs = line.split(" ");
            aggregateMap.putAll(createMap(Long.parseLong(inputs[0]), Long.parseLong(inputs[1]), Long.parseLong(inputs[2])));
        }
        
        return aggregateMap;
    }
    
    public static Map<String, Long> createMap(Long value, Long key, Long count) {
        Map<String, Long> result = new HashMap<>();
        
        String startFinish = String.format("%s-%s", key, key + count);
        long delta = value - key;
        result.put(startFinish, delta);
        
        return result;
    }
    
    public static List<List<String>> getAlmanacMaps(List<String> almanac){
        List<List<String>> mapResult = new ArrayList<>();
        
        boolean isCapturing = false;
        
        List<String> subList = new ArrayList<>();
        for (String line: almanac){
            if (!isCapturing && line.contains("map:")){
                isCapturing = true;
                subList = new ArrayList<>();
            } else if (isCapturing && line.isEmpty()) {
                isCapturing = false;
                mapResult.add(subList);
                subList = new ArrayList<>();
            } else if (isCapturing){
                subList.add(line);
            }
        }
        
        return mapResult;
    }
}

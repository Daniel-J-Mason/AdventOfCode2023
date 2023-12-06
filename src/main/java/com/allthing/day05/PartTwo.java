package com.allthing.day05;

import com.allthing.parser.FileParser;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartTwo {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        
        List<String> almanac = fileParser.toStringArray("05_DayFive_PartTwo.txt");
        almanac.add(""); //new empty string to trigger getAlmanacMaps correct;
        
        List<PseudoRange> seeds = collectPseudoSeedsFromAlmanac(almanac.get(0));
        
        List<LinkedHashMap<PseudoRange, Long>> steps = parseAlmanacSteps(almanac);
        
        System.out.println(calculateLowestDistanceFromAllSeeds(seeds, steps));

    }
    
    public static List<PseudoRange> collectPseudoSeedsFromAlmanac(String line) {
        List<PseudoRange> result = new ArrayList<>();
        
        String regex = "(\\d+) (\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        
        while (matcher.find()) {
            long startingSeed = Long.parseLong(matcher.group(1));
            long count = Long.parseLong(matcher.group(2));
            result.add(new PseudoRange(startingSeed, startingSeed + count));
        }
        
        return result;
    }
    
    public static Long calculateLowestDistanceFromAllSeeds(List<PseudoRange> seeds, List<LinkedHashMap<PseudoRange, Long>> almanacSteps) {
        List<Long> lowestList = new ArrayList<>();
        
        for (PseudoRange seed: seeds){
            lowestList.add(calculateLowestDistanceForPseudoRangeSeed(seed, almanacSteps));
        }
        
        return lowestList.stream().min(Long::compare).orElse(null);
    }
    
    public static Long calculateLowestDistanceForPseudoRangeSeed(PseudoRange seed, List<LinkedHashMap<PseudoRange, Long>> almanacSteps) {
        
        Queue<PseudoRange> inputs = new ArrayDeque<>();
        
        Queue<PseudoRange> outputs = new ArrayDeque<>();
        
        inputs.add(seed);
        
        for (LinkedHashMap<PseudoRange, Long> map : almanacSteps) {
            while (!inputs.isEmpty()) {
                PseudoRange input = inputs.poll();
                
                for (PseudoRange output : calculateStepFromSingleSeedRange(input, map)) {
                    outputs.offer(output);
                }
            }
            
            inputs = outputs;
            outputs = new ArrayDeque<>();
        }
        
        return inputs.stream()
                .min(Comparator.comparing(PseudoRange::getFirst)).map(PseudoRange::getFirst).orElse(null);
        
    }
    
    public static List<PseudoRange> calculateStepFromSingleSeedRange(PseudoRange seedRange, LinkedHashMap<PseudoRange, Long> singleAlmanacStep) {
        
        Queue<PseudoRange> inputs = new ArrayDeque<>();
        
        Queue<PseudoRange> outputs = new ArrayDeque<>();
        
        inputs.add(seedRange);
        
        for (PseudoRange filterRange : singleAlmanacStep.keySet()) {
            
            PseudoRange seed = inputs.poll();
            
            if (seed == null) {
                break;
            }
            long delta = singleAlmanacStep.get(filterRange);
            
            if (filterRange.fullyContains(seed)) { // if a range encapsulates the full range, translate and output
                seed.translateRange(delta);
                outputs.offer(seed);
            } else if (seed.fullyContains(filterRange)) { // if a seed fully encapsulates the range, split off ends to re-check and translate filter to output
                inputs.offer(new PseudoRange(seed.getFirst(), filterRange.getFirst()));
                inputs.offer(new PseudoRange(filterRange.getLast(), seed.getLast()));
                outputs.offer(new PseudoRange(filterRange.first + delta, filterRange.last + delta));
            } else if (seed.first < filterRange.first && seed.last >= filterRange.first) { // partial overlap case 1
                inputs.offer(new PseudoRange(seed.first, filterRange.first - 1));
                outputs.offer(new PseudoRange(filterRange.first + delta, seed.last + delta));
            } else if (seed.first < filterRange.last && seed.last > filterRange.last) { // partial overlap case 2
                inputs.offer(new PseudoRange(filterRange.last, seed.last));
                outputs.offer(new PseudoRange(seed.first + delta, filterRange.last - 1 + delta));
            } else { // if none of these the full seed has no interaction, re-add ot input to check against remaining
                inputs.offer(seed);
            }
            
        }
        
        outputs.addAll(inputs); // all unmodified partial ranges are output for this step
        
        return new ArrayList<>(outputs);
    }
    
    public static List<LinkedHashMap<PseudoRange, Long>> parseAlmanacSteps(List<String> almanac) {
        List<List<String>> almanacMaps = getAlmanacGroups(almanac);
        
        List<LinkedHashMap<PseudoRange, Long>> result = new ArrayList<>();
        
        for (List<String> subSet : almanacMaps) {
            result.add(createStepMap(subSet));
        }
        
        return result;
    }
    
    public static LinkedHashMap<PseudoRange, Long> createStepMap(List<String> subSet) {
        LinkedHashMap<PseudoRange, Long> aggregateMap = new LinkedHashMap<>();
        
        for (String line : subSet) {
            String[] inputs = line.split(" ");
            aggregateMap.putAll(createSingleMap(Long.parseLong(inputs[0]), Long.parseLong(inputs[1]), Long.parseLong(inputs[2])));
        }
        
        return aggregateMap;
    }
    
    public static LinkedHashMap<PseudoRange, Long> createSingleMap(Long value, Long key, Long count) {
        LinkedHashMap<PseudoRange, Long> result = new LinkedHashMap<>();
        
        PseudoRange pseudoRange = new PseudoRange(key, key + count);
        long delta = value - key;
        
        result.put(pseudoRange, delta);
        
        return result;
    }
    
    public static List<List<String>> getAlmanacGroups(List<String> almanac) {
        List<List<String>> mapResult = new ArrayList<>();
        
        boolean isCapturing = false;
        
        List<String> subList = new ArrayList<>();
        for (String line : almanac) {
            if (!isCapturing && line.contains("map:")) {
                isCapturing = true;
                subList = new ArrayList<>();
            } else if (isCapturing && line.isEmpty()) {
                isCapturing = false;
                mapResult.add(subList);
                subList = new ArrayList<>();
            } else if (isCapturing) {
                subList.add(line);
            }
        }
        
        return mapResult;
    }
    
    
    public static class PseudoRange {
        private Long first;
        private Long last;
        
        PseudoRange(Long first, Long last) {
            this.first = first;
            this.last = last;
        }
        
        public Long getFirst() {
            return first;
        }
        
        public void setFirst(Long first) {
            this.first = first;
        }
        
        public Long getLast() {
            return last;
        }
        
        public void setLast(Long last) {
            this.last = last;
        }
        
        public void translateRange(Long delta) {
            first += delta;
            last += delta;
        }
        
        public boolean fullyContains(PseudoRange other) {
            return first <= other.first && other.last <= last;
        }
        
        @Override
        public String toString() {
            return "PseudoRange: " + first + "-" + last;
        }
    }
}

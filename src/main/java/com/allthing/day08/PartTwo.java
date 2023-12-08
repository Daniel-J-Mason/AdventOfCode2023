package com.allthing.day08;

import com.allthing.parser.FileParser;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PartTwo {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        
        List<String> lines = fileParser.toStringArray("08_DayEight_PartTwo.txt");
        
        System.out.println(BigDecimal.valueOf(lowestStepsToCoincide(lines)));
    }
    
    public static double lowestStepsToCoincide(List<String> lines) {
        
        List<String> steps = lines.subList(2, lines.size());
        
        List<String> firstNodes = new ArrayList<>();
        for (String line: steps){
            String nodeString = parseElements(line).getFirst();
            if (nodeString.charAt(2) == 'A') {
                firstNodes.add(nodeString);
            }
        }
        
        List<Integer> stepCounts = firstNodes.stream().map(node -> stepsToComplete(lines, node)).toList();
        
        stepCounts.forEach(System.out::println);
        
        return leastCommonMultipleFromList(stepCounts);
    }
    
    public static Integer stepsToComplete(List<String> lines, String startingNode){
        String instructions = lines.getFirst();
        
        List<String> linesAfterInstructions = lines.subList(2, lines.size());
        
        TreeNode firstNode = treeFromLines(linesAfterInstructions, startingNode);
        
        Deque<Character> instructionsQueue = instructions.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toCollection(ArrayDeque::new));
        
        int count = 0;
        
        while (firstNode.value.charAt(2) != 'Z'){
            
            char direction = instructionsQueue.pollFirst();
            instructionsQueue.offerLast(direction);
            
            firstNode = direction == 'L' ? firstNode.left : firstNode.right;
            
            count++;
        }
        
        return count;
    }
    
    public static TreeNode treeFromLines(List<String> linesAfterInstructions, String startingNode){
        Map<String, TreeNode> nodes = new LinkedHashMap<>();
        
        for (String line: linesAfterInstructions){
            List<String> elements = parseElements(line);
            nodes.put(elements.getFirst(), new TreeNode(elements.getFirst()));
        }
        
        for (String line: linesAfterInstructions){
            List<String> elements = parseElements(line);
            TreeNode node = nodes.get(elements.getFirst());
            TreeNode left = nodes.get(elements.get(1));
            TreeNode right = nodes.get(elements.get(2));
            node.left = left;
            node.right = right;
        }
        
        return nodes.get(startingNode);
    }
    
    public static List<String> parseElements(String line){
        String regex = "\\w+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        
        List<String> result = new ArrayList<>();
        while (matcher.find()){
            result.add(matcher.group());
        }
        
        return result;
    }
    
    private static double greatestCommonDenominator(double a, double b){
        if (b == 0){
            return a;
        }
        return greatestCommonDenominator(b, a % b);
    }
    
    private static double leastCommonMultiple(double a, double b){
        return Math.abs(a * (b / greatestCommonDenominator(a, b)));
    }
    
    private static double leastCommonMultipleFromList(List<Integer> list){
        double result = 1;
        for (double i: list){
            result = leastCommonMultiple(result, i);
        }
        
        return result;
    }
}

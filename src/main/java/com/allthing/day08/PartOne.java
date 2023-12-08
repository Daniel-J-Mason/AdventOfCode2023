package com.allthing.day08;

import com.allthing.parser.FileParser;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PartOne {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        
        List<String> lines = fileParser.toStringArray("08_DayEight_PartOne.txt");
        System.out.println(lines.get(0));
        
        System.out.println(stepsToComplete(lines));
    }
    
    public static int stepsToComplete(List<String> lines){
        String instructions = lines.getFirst();
        
        List<String> linesAfterInstructions = lines.subList(2, lines.size());
        
        TreeNode firstNode = treeFromLines(linesAfterInstructions);
        
        Deque<Character> instructionsQueue = instructions.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toCollection(ArrayDeque::new));
        
        int count = 0;
        
        
        while (!firstNode.value.equals("ZZZ")){
            
            char direction = instructionsQueue.pollFirst();
            instructionsQueue.offerLast(direction);
 
            firstNode = direction == 'L' ? firstNode.left : firstNode.right;
            
            count++;
        }
        
        return count;
    }
    
    public static TreeNode treeFromLines(List<String> linesAfterInstructions){
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
        
        System.out.println("First node: " + nodes.keySet().iterator().next());
        return nodes.get("AAA");
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
}

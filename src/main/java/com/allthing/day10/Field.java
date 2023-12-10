package com.allthing.day10;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Field {
    
    private Map<Pair<Integer, Integer>, Node> allNodes;
    private int height;
    private int width;
    
    public Field(List<String> inputLines){
        height = inputLines.size();
        width = inputLines.getFirst().length();
        allNodes = getAllNodes(inputLines);
    }
    
    public int getMaxSteps(){
        Node sNode = getStartingNode();
        Node nextNode = getNeighbors(sNode).getLeft();
        Node lastNode = sNode;
        
        int count = 0;
        
        while (nextNode != sNode){
            Pair<Node, Node> options = getNeighbors(nextNode);
            if (options.getLeft().equals(lastNode)){
                nextNode.next = options.getRight();
            } else {
                nextNode.next = options.getLeft();
            }
            lastNode = nextNode;
            nextNode = nextNode.next;
            
            count++;
        }
        
        return count;
    }
    
    public List<Pair<Integer, Integer>> getPathNodeCoords(){
        List<Pair<Integer, Integer>> coords = new ArrayList<>();
        
        Field.Node sNode = getStartingNode();
        Field.Node nextNode = getNeighbors(sNode).getLeft();
        Field.Node lastNode = sNode;
        
        coords.add(Pair.of(sNode.x, sNode.y));
        coords.add(Pair.of(nextNode.x, nextNode.y));
        
        while (nextNode != sNode){;
            
            Pair<Field.Node, Field.Node> options = getNeighbors(nextNode);
            if (options.getLeft().equals(lastNode)){
                nextNode.next = options.getRight();
            } else {
                nextNode.next = options.getLeft();
            }
            lastNode = nextNode;
            nextNode = nextNode.next;
            
            coords.add(Pair.of(nextNode.x, nextNode.y));
        }
        
        return coords;
    }
    
    public Map<Pair<Integer, Integer>, Node> getPathNodes(){
        List<Pair<Integer, Integer>> pathCoords = getPathNodeCoords();
        
        return allNodes.entrySet().stream()
                .filter(map -> pathCoords.contains(map.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    
    public int getHeight(){
        return height;
    }
    
    public int getWidth(){
        return width;
    }
    
    private Node getStartingNode(){
        Node sNode = null;
        
        for (Node node: allNodes.values()){
            if (node.pipeType == 'S'){
                sNode = node;
            }
        }
        
        return sNode;
    }
    
    private Pair<Node, Node> getNeighbors(Node currentNode){
        return switch (currentNode.pipeType){
            case '|' -> Pair.of(getAbove(currentNode), getBelow(currentNode));
            case '-' -> Pair.of(getLeft(currentNode), getRight(currentNode));
            case 'L' -> Pair.of(getAbove(currentNode), getRight(currentNode));
            case 'J' -> Pair.of(getAbove(currentNode), getLeft(currentNode));
            case '7' -> Pair.of(getLeft(currentNode), getBelow(currentNode));
            case 'F' -> Pair.of(getRight(currentNode), getBelow(currentNode));
            case 'S' -> getStartingPair(currentNode);
            default -> null;
        };
    }
    
    private Pair<Field.Node, Field.Node> getStartingPair(Field.Node s){
        List<Field.Node> startingNodes = new ArrayList<>();
        
        if (getAbove(s) != null && (getAbove(s).pipeType == '7' || getAbove(s).pipeType == 'F' || getAbove(s).pipeType == '|')){
            startingNodes.add(getAbove(s));
        }
        if (getBelow(s) != null && (getBelow(s).pipeType == 'J' || getBelow(s).pipeType == '|' || getBelow(s).pipeType == 'L')){
            startingNodes.add(getBelow(s));
        }
        if (getLeft(s) != null && (getLeft(s).pipeType == 'F' || getLeft(s).pipeType == 'L' || getLeft(s).pipeType == '-')){
            startingNodes.add(getLeft(s));
        }
        if (getRight(s) != null && (getRight(s).pipeType == '7' || getRight(s).pipeType == 'J' || getRight(s).pipeType == '-')){
            startingNodes.add(getRight(s));
        }
        
        
        
        startingNodes.forEach(node -> System.out.println("Node " + node.pipeType));
        
        return Pair.of(startingNodes.get(0), startingNodes.get(1));
    }
    
    private Node getAbove(Node node){
        return allNodes.get(Pair.of(node.x , node.y - 1));
    }
    
    private Node getBelow(Node node){
        return allNodes.get(Pair.of(node.x , node.y + 1));
    }
    
    private Node getLeft(Node node){
        return allNodes.get(Pair.of(node.x - 1 , node.y));
    }
    
    private Node getRight(Node node){
        return allNodes.get(Pair.of(node.x + 1 , node.y));
    }
    
    private  Map<Pair<Integer, Integer>, Node> getAllNodes(List<String> array){
        List<Node> nodes = new LinkedList<>();
        
        for (int y = 0; y < array.size(); y++){
            String line = array.get(y);
            for (int x = 0; x < line.length(); x++){
                char pipe = line.charAt(x);
                if (pipe != '.'){
                    nodes.add(new Node(pipe, x, y));
                }
            }
        }
        
        return nodes.stream().collect(Collectors.toMap(
                node -> Pair.of(node.x, node.y), node -> node
        ));
    }
    
    public static class Node{
        char pipeType;
        int x;
        int y;
        Node next;
        
        public Node(char pipeType, int x, int y){
            this.pipeType = pipeType;
            this.x = x;
            this.y = y;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node node)) return false;
            return pipeType == node.pipeType && x == node.x && y == node.y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(pipeType, x, y);
        }
    }
}

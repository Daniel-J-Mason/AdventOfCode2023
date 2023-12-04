package com.allthing.day02;

public class Game {
    private final int id;
    private int redCount;
    private int greenCount;
    private int blueCount;
    
    public Game(int id){
        this.id = id;
        redCount = 0;
        greenCount = 0;
        blueCount = 0;
    }
    
    public int getId(){
        return id;
    }
    
    public void addRed(int count){
        redCount += count;
    }
    
    public void addGreen(int count){
        greenCount += count;
    }
    public void addBlue(int count){
        blueCount += count;
    }
    
    public int getPower(){
        return redCount * greenCount * blueCount;
    }
    
    public boolean isValidGame(int redMax, int greenMax, int blueMax){
        return redCount <= redMax && greenCount <= greenMax && blueCount <= blueMax;
    }
    
    @Override
    public String toString() {
        return String.format("Game %s: RedCount %s, BlueCount %s, GreenCount %s", id, redCount, blueCount, greenCount);
    }
}

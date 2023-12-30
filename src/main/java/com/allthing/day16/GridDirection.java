package com.allthing.day16;

public enum GridDirection {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);
    
    private final int x;
    private final int y;
    
    GridDirection(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Pair<Integer, Integer> toPair() {
        return new Pair<>(x,  y);
    }
}

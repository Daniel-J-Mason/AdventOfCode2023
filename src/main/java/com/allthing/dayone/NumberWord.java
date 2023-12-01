package com.allthing.dayone;

public enum NumberWord {
    ONE("one", 1),
    TWO("two", 2),
    THREE("three", 3),
    FOUR("four", 4),
    FIVE("five", 5),
    SIX("six", 6),
    SEVEN("seven", 7),
    EIGHT("eight", 8),
    NINE("nine", 9);
    
    private final String word;
    private final int number;
    
    NumberWord(String word, int number) {
        this.word = word;
        this.number = number;
    }
    
    public String getWord() {
        return this.word;
    }
    
    public int getNumber() {
        return this.number;
    }
    
    public static int convertToNumber(String word) {
        for (NumberWord numberWord : NumberWord.values()) {
            if (numberWord.getWord().equalsIgnoreCase(word)) {
                return numberWord.getNumber();
            }
        }
        return -1;  // or throw an exception if word not found
    }
}

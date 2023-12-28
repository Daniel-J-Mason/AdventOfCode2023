package com.allthing.parser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileParser {

    public char[] toCharArray(String filename) {
        return readFileAsString(filename).toCharArray();
    }
    
    //Left for backwards compatibility
    public List<String> toStringArray(String filename) {
        String[] lines = readFileAsString(filename).split("\n");
        return new ArrayList<>(List.of(lines));
    }
    
    public List<String> toStringArray(String filename, String separator) {
        String[] lines = readFileAsString(filename).split(separator);
        return new ArrayList<>(List.of(lines));
    }
    
    private String readFileAsString(String filename) {
        try {
            URL resource = this.getClass().getResource("/inputs/" + filename);
            if (resource != null) {
                Path path = Paths.get(resource.toURI());
                return new String(Files.readAllBytes(path));
            }
        } catch (IOException | URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return ""; // return empty string if exception is caught
    }
}
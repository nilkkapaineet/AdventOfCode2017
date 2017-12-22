package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // write your code here
        // The name of the file to open.
        String fileName = "file.txt";

        // This will reference one line at a time
        String line = "";

        ArrayList<String> rules2 = new ArrayList<>();
        ArrayList<String> rules3 = new ArrayList<>();

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {

                // read rules line by line based on their length
                if (line.length() < 30) {
                    rules2.add(line);
                } else {
                    rules3.add(line);
                }

            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }


        boolean[][] startingPixels = new boolean[3][3];
        startingPixels[0][0] = false;
        startingPixels[0][1] = true;
        startingPixels[0][2] = false;
        startingPixels[1][0] = false;
        startingPixels[1][1] = false;
        startingPixels[1][2] = true;
        startingPixels[2][0] = true;
        startingPixels[2][1] = true;
        startingPixels[2][2] = true;
        Grid3 startingGrid = new Grid3(startingPixels);

        String[] rule1 = {"../.# => ##./#../..."};
        String[] rule2 = {".#./..#/### => #..#/..../..../#..#"};
        ArrayList<Grid2> g2l = new ArrayList<>();
        ArrayList<Grid3> g3l = new ArrayList<>();
        g3l.add(startingGrid);
        int totalPixels = 0;
        for (int i = 0; i < 5; i++) { // iterations
            if (i%2 == 1) {
                Grid3 tempResult3 = new Grid3();
                while (!g2l.isEmpty()) {
                    Grid2 g2old = g2l.get(0);
                    g2l.remove(0);
                    // repeat the comparison for each rule until the right is matched
                    for (String rule : rules2) {
                        Grid2 compG2 = ruleToGrid2(rule);
                        if (compG2.match(g2old)) {
                            tempResult3 = ruleToGrid3Target(rule);
                            g3l.add(tempResult3);
                            break;
                        }
                    }
                }
            } else {
                while (!g3l.isEmpty()) {
                    Grid3 g3old = g3l.get(0);
                    g3l.remove(0);
                    // repeat the comparison for each rule until the right is matched
                    for (String rule : rules3) {
                        Grid3 compG3 = ruleToGrid3(rule);
                        if (compG3.match(g3old)) {
                            Grid4 g4 = ruleToGrid4(rule);
                            g2l.add(g4.getTopLeft());
                            g2l.add(g4.getTopRight());
                            g2l.add(g4.getBottomLeft());
                            g2l.add(g4.getBottomRight());
                            break;
                        }
                    }
                }
            }
        }

        // count pixels ON
        int pixelsOn = 0;
        while (!g3l.isEmpty()) {
            Grid3 g3 = g3l.get(0);
            g3l.remove(0);
            pixelsOn += g3.totalPixelsOn();
            totalPixels += g3.totalPixels();
        }
        while (!g2l.isEmpty()) {
            Grid2 g2 = g2l.get(0);
            g2l.remove(0);
            pixelsOn += g2.totalPixelsOn();
            totalPixels += g2.totalPixels();
        }
        System.out.println("Total pixels on: " + pixelsOn + " of total: " + totalPixels);
    }

    public static Grid2 ruleToGrid2(String rule) {
        // ../.# => ##./#../...
        boolean[][] pixels = new boolean[2][2];
        if (rule.charAt(0) == '#') {
            pixels[0][0] = true;
        } else {
            pixels[0][0] = false;
        }
        if (rule.charAt(1) == '#') {
            pixels[0][1] = true;
        } else {
            pixels[0][1] = false;
        }
        if (rule.charAt(3) == '#') {
            pixels[1][0] = true;
        } else {
            pixels[1][0] = false;
        }
        if (rule.charAt(4) == '#') {
            pixels[1][1] = true;
        } else {
            pixels[1][1] = false;
        }
        Grid2 g = new Grid2(pixels);
        return g;
    }

    public static Grid3 ruleToGrid3Target(String rule) {
        boolean[][] pixels = new boolean[3][3];
        int[] cat = {9,10,11,13,14,15,17,18,19};
        int catIndex=0;
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (rule.charAt(cat[catIndex]) == '#') {
                    pixels[i][j] = true;
                } else {
                    pixels[i][j] = false;
                }
                catIndex++;
            }
        }
        return (new Grid3(pixels));
    }

    public static Grid3 ruleToGrid3(String rule) {
//        .#./..#/### => #..#/..../..../#..#
        boolean[][] pixels = new boolean[3][3];
        int[] cat = {0,1,2,4,5,6,8,9,10};
        int catIndex=0;
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (rule.charAt(cat[catIndex]) == '#') {
                    pixels[i][j] = true;
                } else {
                    pixels[i][j] = false;
                }
                catIndex++;
            }
        }
        return (new Grid3(pixels));
    }
    public static Grid4 ruleToGrid4(String rule) {
//        .#./..#/### => #..#/..../..../#..#
        boolean[][] pixels = new boolean[4][4];
        int[] cat = {15,16,17,18,20,21,22,23,25,26,27,28,30,31,32,33};
        int catIndex=0;
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                if (rule.charAt(cat[catIndex]) == '#') {
                    pixels[i][j] = true;
                } else {
                    pixels[i][j] = false;
                }
                catIndex++;
            }
        }
        return (new Grid4(pixels));
    }

}

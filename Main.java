package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

            while ((line = bufferedReader.readLine()) != null) {

                // read rules line by line based on their length
                if (line.length() < 30) {
                    rules2.add(line);
                } else {
                    rules3.add(line);
                }

            }

            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        } catch (IOException ex) {
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
        int gridsize = 3;
        startingGrid.printGrid3();

        for (int i = 0; i < 18; i++) { // iterations
            // define size of current grid
            System.out.println("Grid size = " + gridsize);

            boolean[][] gridUnited = new boolean[gridsize][gridsize];
            // reassemble gridunited
            int row = 0;
            int col = 0;
            while (!g2l.isEmpty()) {
                Grid2 g2old = g2l.get(0);
                g2l.remove(0);
                boolean[][] g2o = g2old.getPixels();
                for (int x = 0; x < 2; x++) {
                    for (int y = 0; y < 2; y++) {
                        gridUnited[row + x][col + y] = g2o[x][y];
                    }
                }
                col += 2;
                if (col > (gridsize - 1)) {
                    row += 2;
                    col = 0;
                }
            }
            row = 0;
            col = 0;
            while (!g3l.isEmpty()) {
                Grid3 g3old = g3l.get(0);
                g3l.remove(0);
                boolean[][] g3o = g3old.getPixels();
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        gridUnited[row+x][col+y] = g3o[x][y];
                    }
                }
                col += 3;
                if (col > (gridsize - 1)) {
                    row += 3;
                    col = 0;
                }            }

            // reassembly ready
            if (gridsize % 2 == 0) {
                // chop united grid into pieces of 2
                for (int utdRow=0; utdRow<gridsize; utdRow += 2) {
                    for (int utdCol = 0; utdCol < gridsize; utdCol += 2) {
                        boolean[][] tempGrid = new boolean[2][2];
                        int gridParts = gridsize / 2;
                        for (int x = 0; x < 2; x++) {
                            for (int y = 0; y < 2; y++) {
                                tempGrid[x][y] = gridUnited[utdRow + x][utdCol + y];
                            }
                        }
                        Grid2 tg2 = new Grid2(tempGrid);
                        g2l.add(tg2);
                    }
                }
            } else if (gridsize % 3 == 0) {
                // chop united grid into pieces of 3
                for (int utdRow=0; utdRow<gridsize; utdRow += 3) {
                    for (int utdCol = 0; utdCol < gridsize; utdCol += 3) {
                        boolean[][] tempGrid = new boolean[3][3];
                        int gridParts = gridsize / 3;
                        for (int x = 0; x < 3; x++) {
                            for (int y = 0; y < 3; y++) {
                                tempGrid[x][y] = gridUnited[utdRow + x][utdCol + y];
                            }
                        }
                        Grid3 tg3 = new Grid3(tempGrid);
                        g3l.add(tg3);
                    }
                }
            } else {
                // too big grid to munch
            }
            // once you have chopped the united grid, apply rules to small grids
            // redefine united grid size
            // only minor changes to the code below (you handle only one of the gridarrays, the one that is not empty at first

            if (gridsize % 2 == 0) {
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
                int gridParts = gridsize / 2;
                gridsize = gridParts * 3;
            } else {
                // temporal array for proper ordering of grid
                List<Grid2> tempArrOrder = new ArrayList<>();
                int orderIndex = 0;
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
                            tempArrOrder.add(g4.getBottomLeft());
                            tempArrOrder.add(g4.getBottomRight());
                            orderIndex++;
                            break;
                        }
                    }
                    if (orderIndex == gridsize/3) {
                        while (!tempArrOrder.isEmpty()) {
                            g2l.add(tempArrOrder.get(0));
                            tempArrOrder.remove(0);
                        }
                        orderIndex = 0;
                    }
                }
                int gridParts = gridsize / 3;
                gridsize = gridParts * 4;
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

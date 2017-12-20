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
        int numberOfLines = 0;
        ArrayList<String> lines = new ArrayList<>();

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
                numberOfLines++;
            }

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

        // parse input lines
        int[][] nums = regexLines(lines, numberOfLines);

        // get positions in a long run
        nums = longrun(nums, numberOfLines);

        // manhattan distances
        int numberOfParticleNearest = 0;
        int minManhattan = 100000000;
        int[] tempManhattan = manhattanDistance(numberOfLines, nums, minManhattan, numberOfParticleNearest);
        minManhattan = tempManhattan[0];
        numberOfParticleNearest = tempManhattan[1];

        System.out.println("Closest particle is number " + numberOfParticleNearest + " with distance of " + minManhattan);

    }

    public static int[] manhattanDistance(int numberOfLines, int[][] nums, int minManhattan, int numberOfParticleNearest) {
        for (int p=0; p<numberOfLines; p++) {
            int tempManhattan = Math.abs(nums[p][0]) + Math.abs(nums[p][1]) + Math.abs(nums[p][2]);
            if (tempManhattan < minManhattan) {
                minManhattan = tempManhattan;
                numberOfParticleNearest = p;
            }
        }
        int[] retval = new int[2];
        retval[0] = minManhattan;
        retval[1] = numberOfParticleNearest;
        return retval;
    }

    public static int[][] longrun(int[][] nums, int numberOfLines) {
        // positions of particles in the long run
        for (int loops=0; loops<300; loops++) {
            for (int p=0; p<numberOfLines; p++) {
                nums[p][0] += nums[p][3];
                nums[p][1] += nums[p][4];
                nums[p][2] += nums[p][5];
                nums[p][0] += nums[p][6];
                nums[p][1] += nums[p][7];
                nums[p][2] += nums[p][8];
                nums[p][3] += nums[p][6];
                nums[p][4] += nums[p][7];
                nums[p][5] += nums[p][8];
            }
        }
        return nums;
    }

    public static int[][] regexLines(ArrayList<String> lines, int numberOfLines) {
        int[][] nums = new int[numberOfLines][9];
        int i=0;
        for (String tempLine : lines) {
            // find numbers, this can be applied without first split? 0-2 will hold positions, 3-5 velocities, 6-8 accelerations
            String numsplit = tempLine.replaceAll("[^\\d-]+", ";");
            String[] finalSplit = numsplit.split(";");
            int j=0;
            for (String fs : finalSplit) {
                try {
                    nums[i][j] = Integer.parseInt(fs);
                    j++;
                } catch (NumberFormatException e) {
                    // something nonparsable, do nothing
                }
            }
            i++;
        }
        return nums;
    }
}
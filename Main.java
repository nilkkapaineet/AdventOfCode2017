package com.company;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String [] args) {

        // The name of the file to open.
        String fileName = "file.txt";

        // This will reference one line at a time
        String line = "";

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            int sum = 0;

            int lines = 0;
            List<int[]> x = new ArrayList<int[]>();

            while((line = bufferedReader.readLine()) != null) {

                // more code
                line = line.replaceAll("[^-?0-9]+", " ");
                String[] str = line.trim().split(" ");
                int[] ints = new int[str.length];
                for (int i = 0; i < str.length; i++) {
                    ints[i] = Integer.parseInt(str[i]);
                    System.out.println(ints[i]);
                }
                x.add(ints);
            }

            // find greatest index
            // divide value for each index
            // add new array of numbers to list
            // check if there´s already similar array of ints

            // Always close files.
            bufferedReader.close();

            for (int i=0; i<x.size(); i++) {
                int maxValue = 0;
                int maxIndex = 0;
                for (int j=0; j<x.get(i).length; j++) {
                    if (x.get(i)[j] > maxValue) {
                        maxValue = x.get(i)[j];
                        maxIndex = j;
                    }
                }
                int divide = x.get(i)[maxIndex];
                x.get(i)[maxIndex] = 0;
                for (int j=divide; j>=0; j--) {
                    if (x.get(i)[maxIndex+1] <= x.get(i).length) {
                        int[] temp = x.get(i);

                    }
                }
            }

            int[] temp = x.get(0);
            for (int i=0; i<temp.length; i++) {
                System.out.println("Sum: " + temp[i]);
            }

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
    }
}
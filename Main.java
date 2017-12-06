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
            List<Integer[]> x = new ArrayList<Integer[]>();

            while((line = bufferedReader.readLine()) != null) {

                // more code
                line = line.replaceAll("[^-?0-9]+", " ");
                String[] str = line.trim().split(" ");
                Integer[] ints = new Integer[str.length];
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

            System.out.println("Sum: " + line);

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
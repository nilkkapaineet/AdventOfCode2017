package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);

                // more code

                int sum = 0;
                // set first int
                int firstInt = Character.getNumericValue(line.charAt(0));
                int previous = Character.getNumericValue(line.charAt(0));

                for (int i = 1; i < line.length(); i++) {
                    // current int
                    int j = Character.getNumericValue(line.charAt(i));

                    // if previous is same as current, add to sum
                    if (j == previous) {
                        sum += j;
                    }

                    // set previous int for the next round
                    previous = j;
                }
                if (Character.getNumericValue(line.charAt(line.length()-1)) == firstInt) {
                    sum += firstInt;
                }

                System.out.println("Sum is: " + sum);

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

    }
}
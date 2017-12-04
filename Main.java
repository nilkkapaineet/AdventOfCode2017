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

            int sum = 0;
            while((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);

                // more code
                line = line.replaceAll("[^a-z]+", " ");
                String[] strings = (line.trim().split(" "));

                boolean failed = false;
                for (int i=0; i<strings.length-1; i++) {
                    for (int j=i+1; j<strings.length; j++) {
                        if (strings[i].equals(strings[j])) {
                            // pass
                            failed = true;
                            break;
                        }
                    }
                }
                if (!failed) {
                    sum += 1;
                }

            }
            System.out.println("Sum: " + sum);

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
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
            List<Integer> x = new ArrayList<Integer>();

            while((line = bufferedReader.readLine()) != null) {

                // more code
/*

                line = line.replaceAll("[^-?0-9]+", " ");
                String[] str = line.trim().split(" ");

                Integer[] ints = new Integer[str.length];
                for (int i = 0; i < str.length; i++) {
                    ints[i] = Integer.parseInt(str[i]);
                    System.out.println(ints[i]);
                }

                int maxLength = ints.length;
                int index = 0;
                do {
                    int tmp = ints[index];
                    ints[index] += 1;
                    index += tmp;
                    sum += 1;
                } while (index < maxLength && index >= 0);

                System.out.println("Sum: " + sum);
*/
                x.add(Integer.parseInt(line));
            }
            // Always close files.
            bufferedReader.close();

            int maxLength = x.size();
            Integer[] myArray = x.toArray(new Integer[maxLength]);
            int index = 0;
            do {
                int tmp = myArray[index];
             //   if (tmp < 3) {
                    myArray[index] += 1;
            //    } else {
             //       myArray[index] -= 1;
             //   }
                index += tmp;
                sum += 1;
            } while (index < maxLength && index >= 0);

            System.out.println("Sum: " + sum);

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
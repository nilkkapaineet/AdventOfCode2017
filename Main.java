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

            String[] programs = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p"};
          //  String[] ps = {"a","b","c","d","e"};

            while((line = bufferedReader.readLine()) != null) {
                String[] splitted = line.split(",");
      //          for (long b=0; b<1000000000; b++) {
                    for (int i = 0; i < splitted.length; i++) {
                        if (splitted[i].charAt(0) == 'p') {
                            programs = partner(splitted[i], programs);
                        } else if (splitted[i].charAt(0) == 's') {
                            programs = spin(splitted[i], programs);
                        } else if (splitted[i].charAt(0) == 'x') {
                            programs = exchange(splitted[i], programs);
                        } else {
                            // problem
                        }
                    }
                }
        //    }

            printArr(programs);

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

    public static void printArr(String[] arr) {
        for (int i=0; i<arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println("");
    }

    public static String[] spin(String order, String[] programs) {
        order = order.substring(1);
        int spin = Integer.parseInt(order);
        // spin all characters 'spin' steps
        String[] temp = new String[programs.length];
        for (int i=0; i<programs.length; i++) {
            temp[(i+spin)%programs.length] = programs[i];
        }

        return temp;
    }

    public static String[] exchange(String order, String[] programs) {
        // remove x
        order = order.substring(1);
        String[] splitted = order.split("/");
        int first = Integer.parseInt(splitted[0]);
        int second = Integer.parseInt(splitted[1]);
        String a = programs[first];
        String b = programs[second];
        programs[second] = a;
        programs[first] = b;
        return programs;
    }

    public static String[] partner(String order, String[] programs) {
        order = order.substring(1);
        String[] splitted = order.split("/");
        // indexes for chars
        int first = 0;
        int second = 0;
        for (int i=0; i<programs.length; i++) {
            // loop through programs
            // if char is one to be found, record index
            if (programs[i].equals(splitted[0])) {
                first = i;
            }
            if (programs[i].equals(splitted[1])) {
                second = i;
            }
        }
        String a = programs[second];
        String b = programs[first];
        // swap partners
        programs[first] = a;
        programs[second] = b;
        return programs;
    }
}
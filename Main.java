package com.company;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // write your code here
        // The name of the file to open.
        String fileName = "file.txt";

        // This will reference one line at a time
        String line = "";
        ArrayList<String> lines = new ArrayList<>();

        // registers are in an array
        int[] registers = {0,0,0,0,0,0,0,0};

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
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

        int totalMuls = 0;
        for (int i=0; i<lines.size(); i++) {
            line = lines.get(i);
            // read command aka mul b 100
            String command = line.substring(0, 3);
            char letter = line.charAt(4);
            // letters to numbers
            int pos = letter - 'a';
            int number = 0;
            try {
                number = Integer.parseInt(line.substring(6));
            } catch (NumberFormatException e) {
                number = line.charAt(6);
                number = number - 'a';
                number = registers[number];
            }

            switch (command) {
                case "set":
                    registers[pos] = number;
                    break;
                case "sub":
                    registers[pos] -= number;
                    break;
                case "mul":
                    registers[pos] *= number;
                    totalMuls++;
                    break;
                case "jnz":
                    if (pos == -48) {
                        i = i+number-1;
                    } else {
                        if (registers[pos] != 0) {
                            i = i+number-1;
                        }
                    }
                    break;
            }
        }

        for (int index : registers) {
            System.out.println(index);
        }
        System.out.println("Total " + totalMuls + " muls performed.");
    }
}

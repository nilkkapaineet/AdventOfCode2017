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
        int numberOfLines = 0;
        int lineWidth = 0;
        ArrayList<String> lines = new ArrayList<>();

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                if (line.length() > lineWidth) {
                    lineWidth = line.length();
                }
                lines.add(line);
                numberOfLines++;
            }
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



        // read file and get length and width of needed char array

    // read a diagram first, then find out the route

    // every line is looped char by char
    // if char is not empty, it is either Letter, -, | or +

        char[][] diagram = new char[numberOfLines][lineWidth];
        int x=0;
        int y=0;
        String direction = "";
        ArrayList<Character> letters = new ArrayList<>();

        for (int i=0; i<numberOfLines; i++) {
            String newline = lines.get(i);
            for (int j=0; j<lineWidth; j++) {
                char temp = newline.charAt(j);
                if (temp == ' ') {
                    // do nothing
                    diagram[i][j] = '#';
                } else if (temp == '-') {
                    diagram[i][j] = '-';
                } else if (temp == '|') {
                    diagram[i][j] = '|';
                } else if (temp == '+') {
                    diagram[i][j] = '+';
                } else {
                    // this stores a letter
                    diagram[i][j] = temp;
                }
            }
        }

        // find starting point: xy, direction=d (downwards)
        for (int i=0; i<lineWidth; i++) {
            if (diagram[0][i] == '|') {
                y= i;
                direction = "d";
            }
        }

        while (true) {
            // direction of movement should be stored
            // if a letter occurs, direction remains the same
            // get next xy
            if (direction.equals("d")) {
                x = x + 1;
            } else if (direction.equals("r")) {
                y = y + 1;
            } else if (direction.equals("u")) {
                x = x - 1;
            } else if (direction.equals("l")) {
                y = y - 1;
            }

            // get next xy as above
            // direction may change when + occurs
            // find out other directions than current direction
            char next = diagram[x][y];
            if (next == '+') {
                // direction changes, find out other directions
                if (diagram[x][y+1] != '#' && !direction.equals("l")) {
                    direction = "r";
                } else if (diagram[x-1][y] != '#' && !direction.equals("d")) {
                    direction = "u";
                } else if (diagram[x+1][y] != '#' && !direction.equals("u")) {
                    direction = "d";
                } else if (diagram[x][y-1] != '#' && !direction.equals("r")) {
                    direction = "l";
                } else {
                    // problem
                }
            } else {
                // direction remains, check if letter
                if (next == '|' || next == '-') {
                    // do nothing
                } else if (next == '#') {
                    // get out
                    getout(letters);
                } else {
                        // store letter
                        letters.add(next);
                }
            }

        }

    }

    public static void getout(ArrayList<Character> letters) {
        System.out.println("Letters met: ");
        for (char l : letters) {
            System.out.print(l + " ");
        }
        System.out.println("");
        System.exit(0);
    }
}

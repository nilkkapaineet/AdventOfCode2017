
package com.company;

import java.io.*;
import java.util.*;

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

            String all = "";

            while((line = bufferedReader.readLine()) != null) {
                all = line;
            }

            // remove ! chars from a string
            int found = 0;
            while (true) {
                found = all.indexOf('!');
                if (found == -1) { break; }
                StringBuilder sb = new StringBuilder(all);
                sb.deleteCharAt(found);
                // repeat the same and the next char is removed
                sb.deleteCharAt(found);
                all = sb.toString();
            }

            // remove garbage marked within <>
            int start = 0;
            int end = 0;
            while (true) {
                start = all.indexOf('<');
                end = all.indexOf('>');
                if (start == -1 || end == -1) { break; }
                StringBuilder sb = new StringBuilder(all);
                while (start != end+1) {
                    sb.deleteCharAt(start);
                    end--;
                }
                all = sb.toString();
            }

            // count groups marked with {}
            // nested groups score incremented points aka { {} { {} } } scores 1+2+2+3=8 points
            ArrayList<Integer> starts = new ArrayList<Integer>(); // contains indexes of {'s
            ArrayList<Integer> ends = new ArrayList<Integer>(); // contains indexes of }'s

            int index = all.indexOf('{');
            while (index >= 0) {
                starts.add(index);
                index = all.indexOf('{', index + 1);
            }
            index = all.indexOf('}');
            while (index >= 0) {
                ends.add(index);
                index = all.indexOf('}', index + 1);
            }

            // count points
            int totalPoints = 0;
            for (int i=0; i<ends.size();) {
                int nestedPointsCount = 0;
                int ce = ends.get(i);
                int cs = 0;
                int startToRemoved = starts.size();
                for (int j=0; j<starts.size(); j++) {
                    cs = starts.get(j);
                    nestedPointsCount++;
                    if (cs > ce) {
                        nestedPointsCount--;
                        startToRemoved = j;
                        break;
                    }
                }
                // remove ones from starts and ends
                starts.remove(startToRemoved-1);
                ends.remove(0);
                totalPoints += nestedPointsCount;
            }

            System.out.println("Total points from groups: " + totalPoints);

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

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

            // 0: first string is a name for a register
            // 1: next whether inc or dec
            // 2: number of increment
            // 3: if
            // 4: condition register
            // 5: condition > < = != >= <=
            // 6: condition number
            // all separated by white space

            // stores register and its value
            HashMap<String, Integer> hmap = new HashMap<String, Integer>();
            // temporally maximum value while processing
            int tempMaxValue = 0;

            while((line = bufferedReader.readLine()) != null) {
                String[] splited = line.split("\\s+");
                // search right registers from hmap
                // if not found, register is at 0
                Integer reg = hmap.get(splited[0]);
                Integer regCond = hmap.get(splited[4]);
                if (regCond == null) {
                    regCond = 0;
                }
                if (reg == null) {
                    reg = 0;
                }
                // check out possible conditions
                switch (splited[5]) {
                    case "<" :
                        if (regCond < Integer.parseInt(splited[6])) {
                            // modify reg
                            if (splited[1].equals("inc")) {
                                hmap.put(splited[0], reg + Integer.parseInt(splited[2]));
                                // check if this is a new temporally maximum value
                                if (tempMaxValue < (reg + Integer.parseInt(splited[2]))) {
                                    tempMaxValue = reg + Integer.parseInt(splited[2]);
                                }
                            } else {
                                hmap.put(splited[0], reg - Integer.parseInt(splited[2]));
                                if (tempMaxValue < (reg - Integer.parseInt(splited[2]))) {
                                    tempMaxValue = reg - Integer.parseInt(splited[2]);
                                }
                            }
                        } // else leave reg unmodified
                        break;
                    case ">" :
                        if (regCond > Integer.parseInt(splited[6])) {
                            // modify reg
                            if (splited[1].equals("inc")) {
                                hmap.put(splited[0], reg + Integer.parseInt(splited[2]));
                                if (tempMaxValue < (reg + Integer.parseInt(splited[2]))) {
                                    tempMaxValue = reg + Integer.parseInt(splited[2]);
                                }
                            } else {
                                hmap.put(splited[0], reg - Integer.parseInt(splited[2]));
                                if (tempMaxValue < (reg - Integer.parseInt(splited[2]))) {
                                    tempMaxValue = reg - Integer.parseInt(splited[2]);
                                }
                            }
                        } // else leave reg unmodified
                        break;
                    case "==" :
                        if (regCond == Integer.parseInt(splited[6])) {
                            // modify reg
                            if (splited[1].equals("inc")) {
                                hmap.put(splited[0], reg + Integer.parseInt(splited[2]));
                                if (tempMaxValue < (reg + Integer.parseInt(splited[2]))) {
                                    tempMaxValue = reg + Integer.parseInt(splited[2]);
                                }
                            } else {
                                hmap.put(splited[0], reg - Integer.parseInt(splited[2]));
                                if (tempMaxValue < (reg - Integer.parseInt(splited[2]))) {
                                    tempMaxValue = reg - Integer.parseInt(splited[2]);
                                }
                            }
                        } // else leave reg unmodified
                        break;
                    case "!=" :
                        if (regCond != Integer.parseInt(splited[6])) {
                            // modify reg
                            if (splited[1].equals("inc")) {
                                hmap.put(splited[0], reg + Integer.parseInt(splited[2]));
                                if (tempMaxValue < (reg + Integer.parseInt(splited[2]))) {
                                    tempMaxValue = reg + Integer.parseInt(splited[2]);
                                }
                            } else {
                                hmap.put(splited[0], reg - Integer.parseInt(splited[2]));
                                if (tempMaxValue < (reg - Integer.parseInt(splited[2]))) {
                                    tempMaxValue = reg - Integer.parseInt(splited[2]);
                                }
                            }
                        } // else leave reg unmodified
                        break;
                    case "<=" :
                        if (regCond <= Integer.parseInt(splited[6])) {
                            // modify reg
                            if (splited[1].equals("inc")) {
                                hmap.put(splited[0], reg + Integer.parseInt(splited[2]));
                                if (tempMaxValue < (reg + Integer.parseInt(splited[2]))) {
                                    tempMaxValue = reg + Integer.parseInt(splited[2]);
                                }
                            } else {
                                hmap.put(splited[0], reg - Integer.parseInt(splited[2]));
                                if (tempMaxValue < (reg - Integer.parseInt(splited[2]))) {
                                    tempMaxValue = reg - Integer.parseInt(splited[2]);
                                }
                            }
                        } // else leave reg unmodified
                        break;
                    case ">=" :
                        if (regCond >= Integer.parseInt(splited[6])) {
                            // modify reg
                            if (splited[1].equals("inc")) {
                                hmap.put(splited[0], reg + Integer.parseInt(splited[2]));
                                if (tempMaxValue < (reg + Integer.parseInt(splited[2]))) {
                                    tempMaxValue = reg + Integer.parseInt(splited[2]);
                                }
                            } else {
                                hmap.put(splited[0], reg - Integer.parseInt(splited[2]));
                                if (tempMaxValue < (reg - Integer.parseInt(splited[2]))) {
                                    tempMaxValue = reg - Integer.parseInt(splited[2]);
                                }
                            }
                        } // else leave reg unmodified
                        break;
                    default:
                        System.out.println("error with condition");
                }
            }

            int maxValue = Collections.max(hmap.values());

            System.out.println("Maximum value: " + maxValue +  " and temporal max Value: " + tempMaxValue);

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
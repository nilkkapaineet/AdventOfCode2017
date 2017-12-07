package com.company;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Seven {
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


            List<String> program1 = new ArrayList<String>();
            List<String> program2 = new ArrayList<String>();

            int nOLines=0;

            while((line = bufferedReader.readLine()) != null) {

                // more code

                Pattern re = Pattern.compile("(?<=\\s|^)[a-zA-Z]*(?=[.,;:]?\\s|$)");
                Matcher m = re.matcher(line);
                int mIdx = 0;
                String temp = "";
                boolean added = false;
                while (m.find()){
                    for( int groupIdx = 0; groupIdx < m.groupCount()+1; groupIdx++ ){
                        if (mIdx == 0) {
                            //program1.add(m.group(groupIdx));
                            temp = m.group(groupIdx);
                        } else {
                            program2.add(m.group(groupIdx));
                            if (!added) {
                                program1.add(temp);
                                added = true;
                            }
                        }
                    }
                    mIdx++;
                }
                nOLines++;

                //program.add();

                //System.out.println(line);

            }
            // Always close files.
            bufferedReader.close();

            // check if program1 is not included into program2, you have found the root
            for (int i=0; i<program1.size(); i++) {
                String temp1 = program1.get(i);
                boolean found = false;
                for (int j=0; j<program2.size(); j++) {
                    String temp2 = program2.get(j);
                    if (temp1.equals(temp2)) {
                        // found match, break out
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Root is: " + program1.get(i));
                    break;
                }
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
package com.company;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

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

            // ArrayList<Program> programs= new ArrayList<>();
            HashMap<Integer, Integer> hmap = new HashMap<>();

            int counter = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(":");
                int depth = Integer.parseInt(parts[0].trim());
                int range = Integer.parseInt(parts[1].trim());
                counter = depth;
                hmap.put(depth, range);
            }
            bufferedReader.close();

            // memory storage, init to zero because some depth are not mention (with range 0)
            int[] layers = new int[counter+1];
            for (int i : layers) {
                layers[i] = 0;
            }

            Set set = hmap.entrySet();
            Iterator iterator = set.iterator();
            while(iterator.hasNext()) {
                Map.Entry mentry = (Map.Entry)iterator.next();
                int depth = (Integer) mentry.getKey();
                int range = (Integer) mentry.getValue();
                layers[depth] = range;
            }

            // layers has them

            // direction for each layer is determined by sek/(range-1)
            // odd number goes downwards, even upwards
            int severity = 0;
            // problem b means that you must delay (start i from 10+) some seconds
            // and first delay to pass with severity = 0 is a winner
            // there may be a problem with those first layers, but we will see...

            int delay = 10;
            do {
                severity = 0;
                for (int i = 0; i < counter + 1; i++) {
                    // don't do nothing if depth is less than 2
                    if (layers[i] > 1) {
                        // if scanner is going upwards and sec%(range-1) = 0 you're caught
                        // i represents seconds
                        if (((i+delay) / (layers[i] - 1)) % 2 == 0) {
                            if ((i+delay) % (layers[i] - 1) == 0) {
                                // you're caught
                                severity += 1 * layers[i];
                                // modified, because you must pass the first layer too, even though it doesn't cost you anything
                            }
                        }
                        // else downwards, do nothing
                    }
                }
                delay++;
            } while (severity != 0);

            System.out.println("Delay: " + delay);

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

    }
}
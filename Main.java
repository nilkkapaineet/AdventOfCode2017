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

            ArrayList<String> directions = new ArrayList<>();

            while((line = bufferedReader.readLine()) != null) {

                // more code
                line = line.replaceAll("[^a-z]+", " ");
                String[] strings = line.trim().split(" ");

                for (int i=0; i<strings.length; i++) {
                    directions.add(strings[i]);
                }

            }

            Hex h = new Hex();
            int maxDistance = 0;
            // arraylist directions contains now directions from start Hex to goal Hex
            for (String d : directions) {
                int[] coords = getDirectionByCoords(d);
                h.incrementCoords(coords[0], coords[1]);
                int tempDistance = determineDistance(h);
                if (tempDistance > maxDistance) {
                    maxDistance = tempDistance;
                }
            }

            int[] coords = h.getCoordinates();
            System.out.println("Destination is: " + coords[0] + "," + coords[1]);

            int distance = determineDistance(h);
            System.out.println("Distance is: " + distance);

            System.out.println("Maximum distance is: " + maxDistance);

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

    public static int determineDistance(Hex h) {
        int distance = 0;
        int[] coords = h.getCoordinates();
        if (coords[0] > 0 && coords[1] < 0) {
            // move nw
            while (coords[0] > 0 || coords[1] < 0) {
                coords[0]--;
                coords[1]++;
                distance++;
            }
            // check wheter x=0 or y=0 is reached and move accordingly
            if (coords[0] == 0) {
                while (coords[1] < 0) {
                    coords[1]++;
                    distance++;
                }
            } else {
                while (coords[0] > 0) {
                    coords[0]--;
                    distance++;
                }
            }
        } else if (coords[0] < 0 && coords[1] < 0) {
            // move ne
            while (coords[1] < 0) {
                coords[1]++;
                distance++;
            }
            while (coords[0] < 0) {
                coords[0]++;
                distance++;
            }
        } else if (coords[0] < 0 && coords[1] > 0) {
            // move se
            while (coords[0] < 0 || coords[1] > 0) {
                coords[0]++;
                coords[1]--;
                distance++;
            }
            // check wheter x=0 or y=0 is reached and move accordingly
            if (coords[0] == 0) {
                while (coords[1] > 0) {
                    coords[1]--;
                    distance++;
                }
            } else {
                while (coords[0] < 0) {
                    coords[0]++;
                    distance++;
                }
            }
        } else if (coords[0] > 0 && coords[0] > 0) {
            // move sw
            // decrement y until it hits zero
            while (coords[1] > 0) {
                coords[1]--;
                distance++;
            }
            // move w
            while (coords[0] > 0) {
                coords[0]--;
                distance++;
            }
            // and finally x/y-axes
        } else if (coords[0] == 0) {
            if (coords[1] < 0) {
                // move n
                while (coords[1] < 0) {
                    coords[1]++;
                    distance++;
                }
            } else {
                // move s
                while (coords[1] > 0) {
                    coords[1]--;
                    distance++;
                }
            }
        } else if (coords[1] == 0){
            if (coords[0] < 0) {
                // move ne
                while (coords[0] < 0) {
                    coords[0]++;
                    distance++;
                }
            } else {
                // move sw
                while (coords[0] > 0) {
                    coords[0]--;
                    distance++;
                }
            }
        }

        return distance;
    }

    public static int[] getDirectionByCoords(String direction) {
        int x = 0;
        int y = 0;
        int[] retval = {x , y};
        switch (direction) {
            case "ne" :
                x = 1;
                break;
            case "n" :
                y = 1;
                break;
            case "se" :
                x = 1;
                y = -1;
                break;
            case "sw" :
                x = -1;
                break;
            case "s" :
                y = -1;
                break;
            case "nw" :
                x = -1;
                y = 1;
                break;
            default:
                return retval;
        }
        retval[0] = x;
        retval[1] = y;
        return retval;
    }

}
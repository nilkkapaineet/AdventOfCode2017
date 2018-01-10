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
        ArrayList<Node> nodes = new ArrayList<>();
        // lines must be reread to get node status and position
        ArrayList<String> lines = new ArrayList<>();

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                // get maximum width of initial grid
                if (line.length() > lineWidth) {
                    lineWidth = line.length();
                }
                numberOfLines++;
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

        // nodes are in the list
        // read list (again) to great initial grid
        // calculate position of a node based on initial grid size (origo is in the middle)
        for (int i=0; i<lines.size(); i++) {
            line = lines.get(i);
            // read line char by char and create a node if status is infected and put into a list
            for (int index = 0; index < line.length(); index++) {
                char aChar = line.charAt(index);
                // node coordinates
                int xpos = 0;
                int ypos = 0;
                if (aChar == '#') {
                    // create an infected node
                    // count node coordinates based on its position in lines and achar
                    xpos = -((lineWidth/2)-index);
                    ypos = numberOfLines/2-i;
                  //  System.out.println("found infected node at: " + xpos + "," + ypos);
                    Node node = new Node(xpos, ypos, true);
                    nodes.add(node);
                }
            }
        }

        // infection steps begin
        Carrier carrier = new Carrier();
        int[] coords = carrier.getPosition();
        Node tempNode;
        int totalInfections = 0;
        for (int i=0; i<10000; i++) {
            // check if current node carrier's in is infected
            coords = carrier.getPosition();
          //  System.out.println("Carrier position: " + coords[0] + "," + coords[1]);
            boolean infection = false;
            for (int j=0; j<nodes.size(); j++) {
                tempNode = nodes.get(j);
                if (tempNode.doCoordsMatch(coords[0], coords[1])) {
                    // match found
                    // is it infected
                    if (tempNode.getStatus()) {
                        infection = true;
                        tempNode.changeStatus(false);
                        nodes.remove(j);
        //                System.out.println("node " + tempNode.getCoordinates()[0] + "," + tempNode.getCoordinates()[1] + " is cleaned");
                        break;
                    } else {
                        // node clean
                        break;
                    }
                }
            }
      //      System.out.println("node status was: " + infection);
            if (infection) {
                // turn right
                coords = carrier.takeStep(true);
            } else {
                // turn left
                // node not in a list
                totalInfections++;
                Node node = new Node(coords[0], coords[1], true);
    //            System.out.println("node " + node.getCoordinates()[0] + "," + node.getCoordinates()[1] + " is infected");
                nodes.add(node);
                coords = carrier.takeStep(false);
            }
        }

        // check how many infected nodes there are
        int infected = 0;
        Node finalNode;
        for (int i=0; i<nodes.size(); i++) {
            finalNode = nodes.get(i);
            if (finalNode.getStatus()) {
                infected++;
            //    System.out.println("coords of infected: " + finalNode.getCoordinates()[0] + "," + finalNode.getCoordinates()[1]);
            }
        }
        System.out.println("There are " + infected + " infected nodes.");
        System.out.println("and " + totalInfections + " infections took place");

    }
}

/*
- Read given map
- find out size
- mark infected nodes
- nodes are in a list
- if given node is not in a list, node is not infected
*/




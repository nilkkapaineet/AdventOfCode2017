package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int stepSize = 301;
        int[] insertions = new int[2018];

        // first step must be outside of the loop because of division by zero
        insertions[0] = 0;
        insertions[1] = 1;
        int currentPosition = 1;

        for (int i=2; i<2018; i++) {
            // step forward
            List<Integer> temp = new ArrayList<>();
            currentPosition = ((currentPosition+stepSize)%i)+1;
            // get elements after cp
            for (int cp=i-1; cp>=currentPosition; cp--) {
                temp.add(insertions[cp]);
            }
            insertions[currentPosition] = i;
            int tempsize = temp.size();
            for (int cp=tempsize-1; cp>=0; cp--) {
                insertions[currentPosition+1+cp] = temp.get(0);
                temp.remove(0);
            }
        }
        System.out.println("Value after 9 rounds: " + insertions[currentPosition+1]);
    }

    public static void printArr(int[] arr) {
        for (int i=0; i<arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }

    public static void printList(List<Integer> printable) {
        // prints list in reverse
        for (int i=0; i<printable.size(); i++) {
            System.out.print(i + " ");
        }
        System.out.println("");
    }
}

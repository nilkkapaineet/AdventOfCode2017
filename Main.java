package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String lengths = "206,63,255,131,65,80,238,157,254,24,133,2,16,0,1,3";
        ArrayList<Integer> listLengths = new ArrayList<>();

        // create initial array
        int[] knotHash = new int[256];
        for (int i=0; i<knotHash.length; i++) {
            knotHash[i] = i;
        }

        // lengths are added into a list
        for (String retval: lengths.split(",", 0)) {
            retval = retval.trim();
            int i = Integer.parseInt(retval);
            listLengths.add(i);
        }

        int currentPosition = 0;
        int skipSize = 0;

        // read length of reversion and do it
        // repeat until end of list reached
        while (listLengths.size() > 0) {
            int length = listLengths.get(0);
            listLengths.remove(0);

//            System.out.println("length: " + length);

            // read part of knotHash
            // knotHash is circular...
            // starting from current position
            int[] reverse = new int[length];
            int tempCurrentPos = currentPosition;
            for (int i=0; i<length; i++) {
                if (tempCurrentPos == knotHash.length) {
                    // set to start from beginning
                    tempCurrentPos = 0;
           //         System.out.println("pos set 0: " + i);
                }
                reverse[i] = knotHash[tempCurrentPos];
        //        System.out.println("rkh: " + knotHash[tempCurrentPos] + " from tcp: " + tempCurrentPos);
                tempCurrentPos++;
            }

       //     printArr(reverse, "0");
            // reverse temp array
            reverse = reverseArray(reverse);

//            printArr(reverse, "1");

  //          printArr(knotHash, "1b");
            // rewrite knotHash
            // again, please note circular nature of an array...
            knotHash = rewriteKnotHash(knotHash, currentPosition, reverse);

//            printArr(knotHash, "2");


            // current position modified
  //          System.out.println("ocp: " + currentPosition + " length: " + length + " skipsize: " + skipSize + " khl: " + knotHash.length);
            currentPosition = (currentPosition+(length+skipSize)%(knotHash.length))%(knotHash.length);
    //        System.out.println("cp: " + currentPosition);
            skipSize++;

        }

        /*
        for (int i: knotHash) {
            System.out.print(i);
            System.out.print(", ");
        }
*/
        System.out.println("Multiplication of first two numbers: " + knotHash[0]*knotHash[1]);

    }

    private static void printArr(int[] arr, String place) {
        System.out.print(place + ": ");
        for (int i: arr) {
            System.out.print(i);
            System.out.print(", ");
        }
        System.out.println("");
    }

    private static int[] rewriteKnotHash(int[] initArr, int currentPos, int[] revArr) {
        for (int i=0; i<revArr.length; i++) {
            if (currentPos == initArr.length) {
                currentPos = 0;
            }
            initArr[currentPos] = revArr[i];
         //   System.out.println("ia: " + initArr[currentPos] + " ra: " + revArr[i]);
            currentPos++;
        }
        return initArr;
    }

    private static int[] reverseArray(int[] arr) {
        for(int i = 0; i < arr.length / 2; i++)
        {
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
        return arr;
    }
}

package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here

        // form every input
        String input = "jzgqcdpd-";
        String[] inputs = new String[128];
        int i = 0;
        for (String s : inputs) {
            String ii = Integer.toString(i);
            s = input.concat(ii);
            inputs[i] = s;
            i++;
        }

        int totalUsed = 0;
        for (int j=0; j<inputs.length; j++) {
            String knotHash = knotHash(inputs[j]);

            // take a char by a time and do the conversion
            String totbin = "";
            for (i = 0; i < knotHash.length(); i++) {
                char c = knotHash.charAt(i);
                int x = Character.getNumericValue(c);
                String bin = Integer.toBinaryString(x);
                bin = leadingZeros(bin);
                totbin = totbin.concat(bin);
            }
            totalUsed += used(totbin);
        }

        System.out.println("Used: " + totalUsed);

    }

    public static int used(String bin) {
        int used = 0;

        // check if char is 1 --> used
        for (int i=0; i<bin.length(); i++) {
            if (bin.charAt(i) == '1') {
                used++;
            }
        }
        return used;
    }

    public static String leadingZeros(String a) {
        // if length less than 32, add leading zeros
        String zeros = "";
        for (int ali=a.length(); ali<4; ali++) {
            zeros = zeros.concat("0");
        }
        a = zeros.concat(a);
        return a;
    }

    public static int hex_to_decimal(String s)
    {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        int val = 0;
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
        return val;
    }

    public static String dec_to_bin(int dec_num) {
        int i=1;
        int bin_num[] = new int[100];
        while(dec_num != 0)
        {
            bin_num[i++] = dec_num%2;
            dec_num = dec_num/2;
        }
        String retval = "";
        for(int j=i-1; j>0; j--)
        {
            retval = retval.concat(Integer.toString(bin_num[j]));
//            System.out.print(bin_num[j]);
        }
        return retval;
    }


    public static String knotHash(String lengths) {
    // write your code here
    ArrayList<Integer> listLengths = new ArrayList<>();

    // create initial array
    int[] knotHash = new int[256];
        for (int i = 0; i < knotHash.length; i++) {
        knotHash[i] = i;
    }

    // add the suffix
    String suffix = "17,31,73,47,23";

    // lengths are added into a list
        for (int i = 0; i < lengths.length(); i++) {
        char c = lengths.charAt(i);
        int temp = (int) c;
        listLengths.add(temp);
    }
        for (String retval : suffix.split(",", 0)) {
        retval = retval.trim();
        int i = Integer.parseInt(retval);
        listLengths.add(i);
    }

    int currentPosition = 0;
    int skipSize = 0;
    ArrayList<Integer> copyList = new ArrayList<Integer>(listLengths) ;
        Collections.copy(copyList,listLengths);

        for (int rounds = 0; rounds < 64; rounds++) {
        // read length of reversion and do it
        // repeat until end of list reached
        listLengths = new ArrayList<Integer>(copyList);
        Collections.copy(listLengths,copyList);

        while (listLengths.size() > 0) {
            int length = listLengths.get(0);
            listLengths.remove(0);

            // read part of knotHash
            // knotHash is circular...
            // starting from current position
            int[] reverse = new int[length];
            int tempCurrentPos = currentPosition;
            for (int i = 0; i < length; i++) {
                if (tempCurrentPos == knotHash.length) {
                    // set to start from beginning
                    tempCurrentPos = 0;
                }
                reverse[i] = knotHash[tempCurrentPos];
                tempCurrentPos++;
            }

            // reverse temp array
            reverse = reverseArray(reverse);

            // rewrite knotHash
            // again, please note circular nature of an array...
            knotHash = rewriteKnotHash(knotHash, currentPosition, reverse);

            // current position modified
            currentPosition = (currentPosition + (length + skipSize) % (knotHash.length)) % (knotHash.length);
            skipSize++;

        }

    }


    // knotHash has 255 ascii chars
    // create 16 number densehash
    // each element consists of 16 XORed members of knothash, incremented by one by each round
    List<Integer> sparseHash = new ArrayList<>();
        for (int i = 0; i < knotHash.length; i++) {
        sparseHash.add(knotHash[i]);
    }
    int[] denseHash = new int[16];
    int start = 0;
        for (int j=0; j<16; j++) {
        int xorred = 0;
        for (int i=start; i<16+start; i++) {
            xorred ^= sparseHash.get(i);
        }
        start += 16;
        denseHash[j] = xorred;
    }

    // turn into hexadecimal
    String finalized = "";
        for (int dh : denseHash) {
        finalized = finalized.concat(hexed(dh));
    }

    return finalized;

}

    private static void printArr(int[] arr, String place) {
        System.out.print(place + ": ");
        for (int i: arr) {
            System.out.print(i);
            System.out.print(", ");
        }
        System.out.println("");
    }

    private static String hexed(int n) {
        String retval = Integer.toHexString(n);
        if (retval.length() < 2) {
            retval = "0" + retval;
        }
        return retval;
    }

    private static int convert(int n) {
        return Integer.valueOf(String.valueOf(n), 16);
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


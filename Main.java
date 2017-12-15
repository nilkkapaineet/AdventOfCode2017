package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        long startingValueA = 277;
        long startingValueB = 349;
        long factorA = 16807;
        long factorB = 48271;
        long divider = 2147483647;

        int judge = 0;
        String a="";
        String b="";
        for (long j=0; j<5000000; j++) {
            while (true) {
                startingValueA = startingValueA * factorA % divider;
                if (startingValueA % 4 == 0) {
                    a = Long.toBinaryString(startingValueA);
                    // if length less than 32, add leading zeros
                    String zeros = "";
                    for (int ali=a.length(); ali<20; ali++) {
                        zeros = zeros.concat("0");
                    }
                    a = zeros.concat(a);
                    break;
                }
            }
            while (true) {
                startingValueB = startingValueB * factorB % divider;
                if (startingValueB % 8 == 0) {
                    b = Long.toBinaryString(startingValueB);
                    // if length less than 32, add leading zeros
                    String zeros = "";
                    for (int bli=b.length(); bli<20; bli++) {
                        zeros = zeros.concat("0");
                    }
                    b = zeros.concat(b);
                    break;
                }
            }
            //check if the last 16 bits match
            boolean match = true;
            for (int l=0;l<16;l++) {
                char abit = a.charAt(a.length()-1-l);
                char bbit = b.charAt(b.length()-1-l);
                if (abit != bbit) {
                    match = false;
                    break;
                }
            }
            if (match) {
                judge++;
            }
        }
        System.out.println("Judge found: " + judge);
    }
}

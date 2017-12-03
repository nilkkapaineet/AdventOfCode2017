package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        // The formation consists of squares
        // each new square is formed by the rule 2*n-1 where n is the number of the squares and the result is the size of the side of a given square

        int seed = 361527;
        System.out.println("Ceil rounded root of seed: " + Math.ceil(Math.sqrt(seed)));
        // round to the next odd number
        double odd = Math.ceil(Math.sqrt(seed));
        if (odd%2 == 0) {
            odd += 1;
        }
        System.out.println("Next odd number: " + odd);
        System.out.println("Square of root of seed: " + odd*odd);
        double distanceFromCorner = 0;
        if ((odd*odd-seed)%odd < (odd*odd/2)) {
            System.out.println("Distance1 of seed from a corner: " + (odd * odd - seed) % (odd-1));
            distanceFromCorner = (odd * odd - seed) % (odd-1);
        } else {
            System.out.println("Distance2 of seed from a corner: " + (((odd * odd - seed) % odd) - (odd/2)));
            distanceFromCorner = (((odd * odd - seed) % odd) - (odd/2));
        }
        System.out.println("Distance to half way line: " + (Math.floor(odd/2)-distanceFromCorner));
        System.out.println("Distance to center: " + (Math.floor(odd/2)-distanceFromCorner+((odd+1)/2-1)));
    }
}

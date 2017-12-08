package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        // write your code here
        // The formation consists of squares
        // each new square is formed by the rule 2*n-1 where n is the number of the squares and the result is the size of the side of a given square

        // hashmap for each register containing number and RegXY object
        HashMap<Integer, RegXY> hmap = new HashMap<Integer, RegXY>();

//        int seed = 361527;
        int seed = 12;
        System.out.println("Ceil rounded root of seed: " + Math.ceil(Math.sqrt(seed)));
        // round to the next odd number
        double odd = Math.ceil(Math.sqrt(seed));
        if (odd%2 == 0) {
            odd += 1;
        }
        System.out.println("Next odd number: " + odd);
        System.out.println("Square of root of seed: " + odd*odd);
        double distanceFromCorner = 0;
        if ((odd*odd-seed)%(odd-1) < odd/2) {
            System.out.println("Distance1 of seed from a corner: " + (odd * odd - seed) % (odd-1));
            distanceFromCorner = (odd * odd - seed) % (odd-1);
        } else {
            System.out.println("Distance2 of seed from a corner: " + (((odd * odd - seed) % (odd-1)) - Math.floor(odd/2)));
            distanceFromCorner = (((odd * odd - seed) % (odd-1)) - Math.floor(odd/2));
        }
        System.out.println("Distance to half way line: " + (Math.floor(odd/2)-distanceFromCorner));
        System.out.println("Distance to center: " + (Math.floor(odd/2)-distanceFromCorner+((odd+1)/2-1)));

        // -------------------------------------------------------------------------

        // first program constructed
        RegXY r = new RegXY(1, 1, 0, 0, true);
        hmap.put(1, r);
        int formerY = 0;
        int formerX = 0;

        System.out.println("problem b: ");

        // for loop until seed is met
        int moveCount = 0;
        int direction = 0;
        int maxReached = 361527;
        for (int i=2; i<=maxReached; i++) {
            RegXY rxy = new RegXY(i, formerX, formerY, moveCount, direction, hmap);
          //  System.out.println(rxy.getSquareSize());
            moveCount = rxy.getMovecount();
            if (moveCount >= rxy.getMaxMoves()) {
                // max moves reached, change direction
                if (direction != 3) {
                    direction++;
                    moveCount = 0;
                } else {
                    direction = 0;
                    moveCount = 0;
                }
            }
            formerX = rxy.getX();
            formerY = rxy.getY();

            hmap.put(i, rxy);

            if (rxy.getValue() >= maxReached) {
                maxReached = rxy.getValue();
                break;
            }
        }
        System.out.println("max value: " + maxReached);
    }
}

package com.company;


import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

public class RegXY {
    // coordinates
    private int x;
    private int y;
    // reg value
    private int value;
    private int number;
    private int squareSize;
    private int moveCount;
    private int maxMoves;
    public RegXY(int v, int n, int tx, int ty, boolean needles) {
        x=tx;
        y=ty;
        value=v;
        number=n;
        int index=1;
        boolean getOut=true;
        do {
            index += 2;
            if (n <= index*index) {
                getOut = false;
            }
        } while(getOut);
        squareSize = index;
    }

    public RegXY(int n, int formerX, int formerY, int mc, int direction, HashMap<Integer, RegXY> hmap) {
        // hashmap contains former registers
        // there you find neighbours for value determination
        number = n;
        moveCount = mc;
        int index=1;
        boolean getOut=true;
        do {
            index += 2;
            if (n <= index*index) {
                getOut = false;
            }
        } while(getOut);
        squareSize = index;
        maxMoves = index-1;

        // direction 0: up, 1: left, 2: down, 3: right
        // special coordinates if register starts a new square
        if (n == (index-2)*(index-2)+1) {
            y = formerY;
            x = formerX + 1;
            moveCount = 1;
        } else {
            // construct coordinates
            if (direction == 0) {
                y = formerY + 1;
                x = formerX;
            } else if (direction == 1) {
                x = formerX - 1;
                y = formerY;
            } else if (direction == 2) {
                y = formerY - 1;
                x = formerX;
            } else if (direction == 3) {
                x = formerX + 1;
                y = formerY;
            } else {
                // problem ?
            }
            moveCount++;
        }

        value = determineValues(hmap);

    }

    public int getSquareSize() { return squareSize; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getMovecount() { return moveCount; }
    public int getMaxMoves() { return maxMoves; }
    public int getValue() { return value; }
    public int[] getCoordinates() {
        int[] coord = {x, y};
        return coord;
    }

    // find neighbour values
    private int determineValues(HashMap<Integer, RegXY> hmap) {
        int retValues = 0;

        // go through hashmap
        // if register is a neighbour, add value to retvalue
        for (RegXY r : hmap.values()) {
            int[] ncoords = r.getCoordinates();
            // compare coordinates to current register
            // if coordinates within +-1 xy, then it's a neighbour
            if ((x == ncoords[0]+1 || x == ncoords[0]-1 || x == ncoords[0]) && (y == ncoords[1]+1 || y == ncoords[1]-1 || y == ncoords[1])) {
                retValues += r.getValue();
            }
        }

        return retValues;
    }
}
package com.company;

/**
 * Created by pekka on 9.1.2018.
 */
public class Node {

    // nodes have xy-position, they may be negative as well
    int x;
    int y;
    // nodes are infected or clean
    boolean infected;

    public Node(int xpos, int ypos, boolean infection) {
        x = xpos;
        y = ypos;
        infected = infection;
    }

    public void changeStatus(boolean status) {
        infected = status;
    }
    public boolean getStatus() {
        return infected;
    }
    public int[] getCoordinates() {
        int[] retval = {x, y};
        return retval;
    }
    public boolean doCoordsMatch(int xpos, int ypos) {
        if (x == xpos && y == ypos) {
            return true;
        } else {
            return false;
        }
    }

}

package com.company;

/**
 * Created by pekka on 9.1.2018.
 */

public class Carrier {
    // direction should be marked somehow
    // 0 = north 1=east 2=south 3=west
    int direction;
    // carrier has a position
    int x;
    int y;
    public Carrier() {
        direction = 0;
        x = 0;
        y = 0;
    }

    public int[] getPosition() {
        int[] retval = { x, y };
        return retval;
    }
    private void setPosition(int xpos, int ypos) {
        x = xpos;
        y = ypos;
    }

    public int[] takeStep(boolean status) {
        // returns new position and updates it

        // get new direction
        // direction depends on given node status
        // find current node status from the list
        // if it's not in the list, node is clean
        int newDir = newDirection(status);
        // set new position
        // new position depends on direction
        switch (newDir) {
            case 0:
                y += 1;
                direction = 0;
                break;
            case 1:
                x += 1;
                direction = 1;
                break;
            case 2:
                y -= 1;
                direction = 2;
                break;
            case 3:
                x -= 1;
                direction = 3;
                break;
        }
        int[] retval = getPosition();
        return retval;
    }

    // direction changes depending on current direction and given node status
    private int newDirection(boolean status) {
        int retval = 0;
        if (status) {
            // current node is infected
            switch (direction) {
                case 0:
                    retval = 1;
                    break;
                case 1:
                    retval = 2;
                    break;
                case 2:
                    retval = 3;
                    break;
                case 3:
                    retval = 0;
                    break;
            }
        } else {
            // current node is clean
            switch (direction) {
                case 0:
                    retval = 3;
                    break;
                case 1:
                    retval = 0;
                    break;
                case 2:
                    retval = 1;
                    break;
                case 3:
                    retval = 2;
                    break;
            }
        }
        return retval;
    }

}

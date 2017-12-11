package com.company;

/**
 * Created by pekka on 11.12.2017.
 */
public class Hex {
    // coordinates
    private int x;
    private int y;
    public Hex(int tx, int ty) {
        x = tx;
        y = ty;
    }
    public Hex() {
        x=0;
        y=0;
    }
    public int[] getCoordinates() {
        int[] retval = {x, y};
        return retval;
    }
    public void setX(int tx) {
        x = tx;
    }
    public void setY(int ty) {
        y = ty;
    }
    public void setCoords(int tx, int ty) {
        x = tx;
        y = ty;
    }
    public void incrementCoords(int tx, int ty) {
        x += tx;
        y += ty;
    }
    public boolean isNeighbor(Hex h) {
        boolean retval = false;
        int[] coords = h.getCoordinates();
        if ((coords[0] <= x+1 && coords[0] >= x-1) && (coords[1] <= y+1 && coords[1] >= y-1) ) {
            retval = true;
        }
        return retval;
    }
}

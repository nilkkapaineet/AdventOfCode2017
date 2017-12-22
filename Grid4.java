package com.company;

public class Grid4 {
    private boolean[][] pixel = new boolean[4][4];
    public Grid4(boolean[][] p) {
        pixel = p;
    }

    public void printGrid4() {
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                if (pixel[i][j]) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("");
        }
    }

    public Grid2 getTopLeft() {
        boolean[][] topLeft = new boolean[2][2];
        topLeft[0][0] = pixel[0][0];
        topLeft[0][1] = pixel[0][1];
        topLeft[1][0] = pixel[1][0];
        topLeft[1][1] = pixel[1][1];
        Grid2 g = new Grid2(topLeft);
        return g;
    }
    public Grid2 getTopRight() {
        boolean[][] topRight = new boolean[2][2];
        topRight[0][0] = pixel[0][2];
        topRight[0][1] = pixel[0][3];
        topRight[1][0] = pixel[1][2];
        topRight[1][1] = pixel[1][3];
        Grid2 g = new Grid2(topRight);
        return g;
    }
    public Grid2 getBottomLeft() {
        boolean[][] bottomLeft = new boolean[2][2];
        bottomLeft[0][0] = pixel[2][0];
        bottomLeft[0][1] = pixel[2][1];
        bottomLeft[1][0] = pixel[3][0];
        bottomLeft[1][1] = pixel[3][1];
        Grid2 g = new Grid2(bottomLeft);
        return g;
    }
    public Grid2 getBottomRight() {
        boolean[][] bottomRight = new boolean[2][2];
        bottomRight[0][0] = pixel[2][2];
        bottomRight[0][1] = pixel[2][3];
        bottomRight[1][0] = pixel[3][2];
        bottomRight[1][1] = pixel[3][3];
        Grid2 g = new Grid2(bottomRight);
        return g;
    }

    public void setPixels(boolean[][] p) {
        pixel = p;
    }

    public boolean[][] getPixels() {
        return pixel;
    }
}

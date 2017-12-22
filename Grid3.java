package com.company;

public class Grid3 {
    // true if pixel is ON
    private boolean[][] pixel = new boolean[3][3];
    public Grid3(boolean[][] p) {
        pixel = p;
    }

    public Grid3() {

    }

    public void setPixels(boolean[][] p) {
        pixel = p;
    }

    public boolean[][] getPixels() {
        return pixel;
    }

    public boolean match(Grid3 g) {
        // check if this grid matches to given grid (after flipping and rotating)
        for (int i=0; i<4; i++) {
            if (pixelMatch(pixel, g.getPixels())) {
                return true;
            }
            g.flipHorizontal();
            if (pixelMatch(pixel, g.getPixels())) {
                return true;
            }
            g.flipHorizontal();
            g.flipVertical();
            if (pixelMatch(pixel, g.getPixels())) {
                return true;
            }
            g.flipVertical();
            g.rotate();
        }
        return false;
    }

    private boolean pixelMatch(boolean[][] p1, boolean[][] p2) {
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (p1[i][j] != p2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printGrid3() {
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (pixel[i][j]) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("");
        }
    }

    public void rotate() {
        // rotates (private) pixels
        // rotates clockwise
        boolean temp00 = pixel[0][0];
        boolean temp01 = pixel[0][1];
        boolean temp02 = pixel[0][2];
        boolean temp10 = pixel[1][0];
        boolean temp12 = pixel[1][2];
        boolean temp20 = pixel[2][0];
        boolean temp21 = pixel[2][1];
        boolean temp22 = pixel[2][2];
        pixel[0][0] = temp20;
        pixel[0][1] = temp10;
        pixel[0][2] = temp00;
        pixel[1][0] = temp21;
        pixel[1][2] = temp01;
        pixel[2][0] = temp22;
        pixel[2][1] = temp12;
        pixel[2][2] = temp02;
    }

    public void flipHorizontal() {
        // flips (private) pixels
        // 0 <--> 1
        boolean temp00 = pixel[0][0];
        boolean temp02 = pixel[0][2];
        pixel[0][2] = temp00;
        pixel[0][0] = temp02;
        boolean temp10 = pixel[1][0];
        boolean temp12 = pixel[1][2];
        pixel[1][2] = temp10;
        pixel[1][0] = temp12;
        boolean temp20 = pixel[2][0];
        boolean temp22 = pixel[2][2];
        pixel[2][2] = temp20;
        pixel[2][0] = temp22;
    }

    public void flipVertical() {
        // flips (private) pixels
        boolean temp00 = pixel[0][0];
        boolean temp02 = pixel[0][2];
        boolean temp01 = pixel[0][1];
        boolean temp21 = pixel[2][1];
        boolean temp20 = pixel[2][0];
        boolean temp22 = pixel[2][2];
        pixel[2][2] = temp02;
        pixel[2][0] = temp00;
        pixel[2][1] = temp01;
        pixel[0][0] = temp20;
        pixel[0][2] = temp22;
        pixel[0][1] = temp21;
    }

    public int totalPixelsOn() {
        int retval = 0;
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (pixel[i][j]) {
                    retval++;
                }
            }
        }
        return retval;
    }

    public int totalPixels() {
        int retval = 0;
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                    retval++;
            }
        }
        return retval;
    }
}

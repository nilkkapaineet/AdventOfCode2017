package com.company;

public class Grid2 {
    // true if pixel is ON
    private boolean[][] pixel = new boolean[2][2];
    public Grid2(boolean[][] p) {
        pixel = p;
    }
    public Grid2() {
    }

    public boolean match(Grid2 g) {
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

    public boolean matchPrint(Grid2 g, String rule) {
        // check if this grid matches to given grid (after flipping and rotating)
        for (int i=0; i<4; i++) {
            if (pixelMatch(pixel, g.getPixels())) {
                System.out.println("iter " + (1+i) + " rule: " + rule);
                g.printGrid2();
                return true;
            }
            g.flipHorizontal();
            if (pixelMatch(pixel, g.getPixels())) {
                System.out.println("iter " + (1+i) + " rule: " + rule);
                g.printGrid2();
                return true;
            }
            g.flipHorizontal();
            g.flipVertical();
            if (pixelMatch(pixel, g.getPixels())) {
                System.out.println("iter " + (1+i) + " rule: " + rule);
                g.printGrid2();
                return true;
            }
            g.flipVertical();
            g.rotate();
        }
        return false;
    }

    private boolean pixelMatch(boolean[][] p1, boolean[][] p2) {
        for (int i=0; i<2; i++) {
            for (int j=0; j<2; j++) {
                if (p1[i][j] != p2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void setPixels(boolean[][] p) {
        pixel = p;
    }

    public boolean[][] getPixels() {
        return pixel;
    }
    public void printGrid2() {
        for (int i=0; i<2; i++) {
            for (int j=0; j<2; j++) {
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
        boolean temp10 = pixel[1][0];
        boolean temp11 = pixel[1][1];
        pixel[0][0] = temp10;
        pixel[0][1] = temp00;
        pixel[1][0] = temp11;
        pixel[1][1] = temp01;
    }

    public void flipHorizontal() {
        // flips (private) pixels
        // 0 <--> 1
        boolean temp00 = pixel[0][0];
        boolean temp01 = pixel[0][1];
        pixel[0][1] = temp00;
        pixel[0][0] = temp01;
        boolean temp10 = pixel[1][0];
        boolean temp11 = pixel[1][1];
        pixel[1][1] = temp10;
        pixel[1][0] = temp11;
    }

    public void flipVertical() {
        // flips (private) pixels
        // 0 <--> 1
        boolean temp00 = pixel[0][0];
        boolean temp01 = pixel[0][1];
        boolean temp10 = pixel[1][0];
        boolean temp11 = pixel[1][1];
        pixel[0][0] = temp10;
        pixel[0][1] = temp11;
        pixel[1][0] = temp00;
        pixel[1][1] = temp01;
    }

    public int totalPixelsOn() {
        int retval = 0;
        for (int i=0; i<2; i++) {
            for (int j=0; j<2; j++) {
                if (pixel[i][j]) {
                    retval++;
                }
            }
        }
        return retval;
    }

    public int totalPixels() {
        int retval = 0;
        for (int i=0; i<2; i++) {
            for (int j=0; j<2; j++) {
                retval++;
            }
        }
        return retval;
    }

}

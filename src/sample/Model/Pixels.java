package sample.Model;

import java.awt.*;
import java.util.ArrayList;

public class Pixels {

    private int width;
    private int height;

    Pixels(int[][] pixels, int width, int height) {
        this.pixels = pixels;
        this.width = width;
        this.height = height;
    }

    private static int[][] pixels;

    private int getGreen(int column, int row) {
        return ((pixels[column][row] & 0xff00) >> 8);
    }

    private int getBlue(int column, int row) {
        return (pixels[column][row] & 0xff);
    }

    private int getRed(int column, int row) {
        return ((pixels[column][row] & 0xff0000) >> 16);
    }


    public void setNewRGB() {

        System.out.println("PRZED ZAMIANA");
        printPixels();
        ArrayList<String> packages = divideIntoPackages();
        int listElement = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (listElement < packages.size()) {
                    String together = packages.get(listElement);
                    String first, second, third;
                    if (together.length() == 3) {
                        first = together.substring(0, 1);
                        second = together.substring(1, 2);
                        third = together.substring(2, 3);
                    } else if (together.length() == 2) {
                        first = together.substring(0, 1);
                        second = together.substring(1, 2);
                        third = "0";
                    } else {
                        first = together.substring(0, 1);
                        second = "0";
                        third = "0";
                    }
                    int newRed = binaryToInteger(setRed(i, j, first));
                    int newGreen = binaryToInteger(setGreen(i, j, second));
                    int newBlue = binaryToInteger(setBlue(i, j, third));

                    Color color = new Color(newRed, newGreen, newBlue);
                    pixels[i][j] = color.getRGB();
                    listElement++;
                }
            }
        }
        System.out.println("PO ZAMIANIE:");
        printPixels();
        System.out.println("PACZKI");
        for (String s : packages) {
            System.out.println(s);

        }
    }

    private String setGreen(int column, int row, String last) {
        String first = getFirstSeven(intToBinary(getGreen(column, row)));
        first += last;
        return first;
    }

    private String setBlue(int column, int row, String last) {
        String first = getFirstSeven(intToBinary(getBlue(column, row)));
        first += last;
        return first;
    }

    private String setRed(int column, int row, String last) {
        String first = getFirstSeven(intToBinary(getRed(column, row)));
        first += last;
        return first;
    }

    public static String intToBinary(int value) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < 256; i = i << 1)
            stringBuilder.append(((value & i) > 0 ? 1 : 0));
        return stringBuilder.toString();
    }

    private static String getFirstSeven(String binary) {
        return binary.substring(0, 7);
    }


    public static ArrayList<String> divideIntoPackages() {
        ArrayList<String> packages = new ArrayList<String>();
        String message = SecretMessage.toBinary();

        int i = 0;
        while (i < message.length()) {
            packages.add(message.substring(i, i + 3));
            i = i + 3;
        }
        if (i - 3 != message.length() - 1) {
            packages.add(message.substring(i - 3, message.length() - 1));
        }
        return packages;
    }

    public void printPixels() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                System.out.println("Red: " + intToBinary(getRed(i, j)) + " Green: " + intToBinary(getGreen(i, j)) + " Blue: " + intToBinary(getBlue(i, j)));
                //  System.out.print(pixels[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public int binaryToInteger(String binary) {
        char[] numbers = binary.toCharArray();
        int result = 0;
        for (int i = numbers.length - 1; i >= 0; i--)
            if (numbers[i] == '1')
                result += Math.pow(2, (numbers.length - i - 1));
        return result;
    }
}

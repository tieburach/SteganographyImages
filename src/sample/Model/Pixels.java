package sample.Model;

import java.awt.*;
import java.util.ArrayList;

class Pixels {

    private int width;
    private int height;

    Pixels(int[][] pixels, int width, int height) {
        Pixels.pixels = pixels;
        this.width = width;
        this.height = height;
    }

    private static int[][] pixels;

    private int getGreen(int column, int row) {
        return ((pixels[column][row] >> 8) & 0xff);
    }

    private int getBlue(int column, int row) {
        return ((pixels[column][row]) & 0xff);
    }

    private int getRed(int column, int row) {
        return ((pixels[column][row] >> 16) & 0xff);
    }

    private void printPixels(){
        System.out.println("STARE");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print("" + pixels[i][j]);
                }
            System.out.println("\n");
            }
    }

    void setNewRGB(int howMany) {
        ArrayList<String> packages = divideIntoPackages(howMany);
        int listElement = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (listElement < packages.size()) {
                    String together = packages.get(listElement);
                    String first = "", second = "", third = "";

                    if (together.length() == 3 * howMany) {
                        first = together.substring(0, howMany);
                        second = together.substring(howMany, (2 * howMany));
                        third = (together.substring(2 * howMany, 3 * howMany));
                    } else if (together.length() == 2 * howMany) {
                        first = together.substring(0, howMany);
                        second = together.substring(howMany, 2 * howMany);
                        for (int k = 0; k < howMany; k++) {
                            third += "0";
                        }
                    } else {
                        try {
                            first = together.substring(0, howMany);
                        } catch (Exception ignored) {

                        }
                        for (int k = 0; k < howMany; k++) {
                            second += "0";
                            third += "0";
                        }
                    }
                    int newRed = binaryToInteger(setRed(i, j, first, howMany));
                    int newGreen = binaryToInteger(setGreen(i, j, second, howMany));
                    int newBlue = binaryToInteger(setBlue(i, j, third, howMany));
                    Color color = new Color(newRed, newGreen, newBlue);
                    pixels[i][j] = color.getRGB();
                    listElement++;
                }
            }
        }
        printPixels();
    }

    private String setGreen(int column, int row, String last, int howMany) {
        String first = getFirstElements(Pixels.toBinaryString(getGreen(column, row)), howMany);
        first += last;
        return first;
    }

    private String setBlue(int column, int row, String last, int howMany) {
        String first = getFirstElements(Pixels.toBinaryString(getBlue(column, row)),howMany);
        first += last;
        return first;
    }

    private String setRed(int column, int row, String last, int howMany) {
        String first = getFirstElements(Pixels.toBinaryString(getRed(column, row)),howMany);
        first += last;
        return first;
    }

    private static String getFirstElements(String binary, int x) {
        if (binary.length() < 8) {
            String zero = "0";
            int howManyInserts = 8 - binary.length();
            for (int i = 0; i < howManyInserts; i++) {
                zero= zero + binary;
            }
            return zero.substring(0, 8-x);
        }
        return binary.substring(0, 8-x);
    }


    private static ArrayList<String> divideIntoPackages(int howMany) {
        ArrayList<String> packages = new ArrayList<>();
        String message = SecretMessage.toBinary();

        int i = 0;
        while (i + (3*howMany) <= message.length()) {
            packages.add(message.substring(i, i + (3*howMany)));
            i = i + (3*howMany);
        }
        String rest = message.substring(i, message.length());
        if (rest.length()<= (3*howMany)){
            if(!rest.isEmpty()) {
                packages.add(rest);
            }
        } else {
            packages.add(rest.substring(0, (3*howMany)));
            packages.add(rest.substring((3*howMany), rest.length()));
        }
        return packages;
    }

    private int binaryToInteger(String binary) {
        char[] numbers = binary.toCharArray();
        int result = 0;
        for (int i = numbers.length - 1; i >= 0; i--)
            if (numbers[i] == '1')
                result += Math.pow(2, (numbers.length - i - 1));
        return result;
    }

    String getLast(int howMany) {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                    String wholeRed, wholeGreen, wholeBlue;
                    wholeRed = (Pixels.toBinaryString(getRed(i, j)));
                    wholeGreen = (Pixels.toBinaryString(getGreen(i, j)));
                    wholeBlue = (Pixels.toBinaryString(getBlue(i, j)));
                    message.append(wholeRed.substring(wholeRed.length() - howMany, wholeRed.length()));
                    message.append(wholeGreen.substring(wholeGreen.length() - howMany, wholeGreen.length()));
                    message.append(wholeBlue.substring(wholeBlue.length() - howMany, wholeBlue.length()));
            }
        }
        return message.toString();
    }


    private static String toBinaryString(int value){
        String result;
        result = Integer.toBinaryString(value);
        while (result.length()<8){
            result = "0" + result;
        }
        return result;
    }
}

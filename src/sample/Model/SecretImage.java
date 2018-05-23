package sample.Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SecretImage {

    private int[][] pixels;
    private String filepath;
    private BufferedImage bufferedImage = null;
    private static boolean encrypting = true;

    public SecretImage(String filepath) {
        this.filepath = filepath;
    }

    public void loadImage() {
        try {
            bufferedImage = ImageIO.read(new File(filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadPixels(int width, int height) {
        pixels = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixels[i][j] = bufferedImage.getRGB(i, j);
            }
        }
    }

    public static boolean isEncrypting() {
        return encrypting;
    }

    public static void setEncrypting(boolean encrypting) {
        SecretImage.encrypting = encrypting;
    }
}

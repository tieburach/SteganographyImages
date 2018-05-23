package sample.Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SecretImage {

    private int[][] pixels;
    private String filepath;

    public static BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    private static BufferedImage bufferedImage = null;
    private static boolean encrypting = true;
    private int width;
    private int height;
    private Pixels pxls;

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

    public void loadPixels() {
        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();
        pixels = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int color = bufferedImage.getRGB(i, j);
                pixels[i][j] = color;
            }
        }
        pxls = new Pixels(pixels, width, height);
    }

    public void encryptPhoto(){
        pxls.setNewRGB();
    }

    public void saveChangedImage(){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                bufferedImage.setRGB(i, j, pixels[i][j]);

            }
        }
        File outputfile = new File("ZAMIENIONE.jpg");
        try {
            ImageIO.write(bufferedImage, "jpg", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static boolean isEncrypting() {
        return encrypting;
    }

    public static void setEncrypting(boolean encrypting) {
        SecretImage.encrypting = encrypting;
    }
}

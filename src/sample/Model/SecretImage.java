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
    private static int width;
    private static int height;
    private Pixels pxls;

    public SecretImage(String filepath) {
        this.filepath = filepath;
    }

    public void loadImage() {
        try {
            bufferedImage = ImageIO.read(new File(filepath));
            BufferedImage convertedImg = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            convertedImg.getGraphics().drawImage(bufferedImage, 0, 0, null);
            convertedImg.getGraphics().dispose();
            bufferedImage = convertedImg;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadPixels() {
        width = bufferedImage.getWidth();
        bufferedImage.getType();
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

    public void encryptPhoto(int howMany) {
        pxls.setNewRGB(howMany);
    }

    public String decryptPhoto(int howMany) {
        String binary = pxls.getLast(howMany);
        StringBuilder output = new StringBuilder();
        String temporary = "";
        for (int i = 1; i <= binary.length(); i++) {
            temporary += (binary.charAt(i - 1));
            if (i % 8 == 0) {
                output.append((char) Integer.parseInt(temporary, 2));
                temporary = "";
            }
        }
        String[] parts = output.toString().split("%");
        return parts[0];
    }

    public void saveChangedImage() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                bufferedImage.setRGB(i, j, pixels[i][j]);
            }
        }
        File outputfile = new File(new File(filepath).getParent() + "\\encryptedImage.png");
        try {
            ImageIO.write(bufferedImage,"png" , outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

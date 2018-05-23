package sample.Model;

import javafx.scene.control.Alert;

import java.io.*;

public class SecretMessage {

    public static String getMessage() {
        return message;
    }

    private static String message;
    private String filePath;

    public SecretMessage(String filePath) {
        this.filePath = filePath;
    }

    public void readFile()  {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

            String textLine;
            StringBuilder sb = new StringBuilder();

            while ((textLine = bufferedReader.readLine()) != null) {
                sb.append(textLine);
            }
            bufferedReader.close();
            message = sb.toString();
        } catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Cos poszlo nie tak z odczytem pliku tekstowego.");
        }
    }


    public void saveFile() {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filePath + "decryptedMessage.txt"), "utf-8"))) {
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String toBinary() {
        System.out.println(message);
        byte[] bytes = message.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        System.out.println("'" + message + "' to binary: " + binary.toString());
        return binary.toString();
    }


}

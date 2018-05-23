package sample.Model;

import javafx.scene.control.Alert;

import java.io.*;

public class SecretMessage {
    private String message;
    private String filePath;

    public SecretMessage(String filePath) {
        this.filePath = filePath;
    }

    public void readFile()  {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

            String textLine = null;
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

    public void toBinary() {
        System.out.println(message);
        byte[] bytes = message.getBytes();
        StringBuilder binary = new StringBuilder();

        int j = 0;
        for (byte b : bytes) {
            j++;
            char c = (char) (b & 0xFF);
            System.out.println("ktory element: " + j);
            System.out.println("jego wartosc: " + c);
            System.out.println("a bitowo: " + b);
        }
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            binary.append(' ');
        }
        System.out.println("'" + message + "' to binary: " + binary.toString());
    }
}

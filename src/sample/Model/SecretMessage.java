package sample.Model;

import java.io.*;
import java.util.Scanner;

public class SecretMessage {
    private String message;
    private String filePath;

    public SecretMessage(String filePath){
        this.filePath = filePath;
    }

    public String readFile() {
        Scanner in = null;
        try {
            in = new Scanner(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        while(in.hasNext()) {
            sb.append(in.next());
        }
        in.close();
        message = sb.toString();
        return message;
    }


    public void saveFile(){
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filePath + "decryptedMessage.txt"), "utf-8"))) {
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

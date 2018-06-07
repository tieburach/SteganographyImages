package sample.Controller;

import sample.Model.SecretImage;
import sample.Model.SecretMessage;

class Decrypter {
    private SecretImage secretImage;
    private SecretMessage secretMessage;

    Decrypter(SecretImage secretImage, SecretMessage secretMessage){
        this.secretImage = secretImage;
        this.secretMessage = secretMessage;
    }

    void run(int howMany){
        //zaladowanie obrazu
        secretImage.loadImage();
        //wypelnienie struktury pixels
        secretImage.loadPixels();
        //zwrocenie wartosci
        String message = secretImage.decryptPhoto(howMany);
        //ustawienie wiadomosci
        SecretMessage.setMessage(message);
        //zapisanie w folderze gdzie jest
        secretMessage.saveFile();

    }

}

package sample.Controller;

import sample.Model.SecretImage;
import sample.Model.SecretMessage;

class Encrypter {
    private SecretImage secretImage;
    private SecretMessage secretMessage;

    Encrypter(SecretImage secretImage, SecretMessage secretMessage){
        this.secretImage = secretImage;
        this.secretMessage = secretMessage;
    }

    void run(int howMany){
        //wczytanie pliku tekstowego
        secretMessage.readFile();
        //wypisanie ala ma kota
        SecretMessage.toBinary();

        //wczytanie pliku graficznego
        secretImage.loadImage();
        //zaladowanie pixelkow
        secretImage.loadPixels();
        //
        secretImage.encryptPhoto(howMany);
        secretImage.saveChangedImage();
    }
}

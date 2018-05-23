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

    void run(){
        //wczytanie pliku tekstowego
        secretMessage.readFile();
        //wypisanie ala ma kota
        secretMessage.toBinary();

        //wczytanie pliku graficznego
        secretImage.loadImage();
        //zaladowanie pixelkow
        secretImage.loadPixels();
        //
        secretImage.encryptPhoto();
        secretMessage.saveFile();
        secretImage.saveChangedImage();
    }
}

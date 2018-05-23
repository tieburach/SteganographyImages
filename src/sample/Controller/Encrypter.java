package sample.Controller;

import sample.Model.SecretImage;
import sample.Model.SecretMessage;

import java.io.IOException;

class Encrypter {
    private SecretImage secretImage;
    private SecretMessage secretMessage;

    Encrypter(SecretImage secretImage, SecretMessage secretMessage){
        this.secretImage = secretImage;
        this.secretMessage = secretMessage;
    }

    void run(){
        secretMessage.readFile();
        secretMessage.toBinary();
    }
}

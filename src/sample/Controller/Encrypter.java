package sample.Controller;

import sample.Model.SecretImage;
import sample.Model.SecretMessage;

public class Encrypter {
    private SecretImage secretImage;
    private SecretMessage secretMessage;

    public Encrypter(SecretImage secretImage, SecretMessage secretMessage){
        this.secretImage = secretImage;
        this.secretMessage = secretMessage;
    }
}

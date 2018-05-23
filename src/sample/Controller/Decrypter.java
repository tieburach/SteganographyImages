package sample.Controller;

import sample.Model.SecretImage;
import sample.Model.SecretMessage;

public class Decrypter {
    private SecretImage secretImage;
    private SecretMessage secretMessage;

    public Decrypter(SecretImage secretImage, SecretMessage secretMessage){
        this.secretImage = secretImage;
        this.secretMessage = secretMessage;
    }
}

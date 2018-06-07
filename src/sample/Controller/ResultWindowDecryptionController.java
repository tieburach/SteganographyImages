package sample.Controller;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import sample.Model.SecretMessage;

public class ResultWindowDecryptionController {

    public TextArea encryptedMessageTextArea;
    public Button finishButton;

    public void initialize(){
        encryptedMessageTextArea.setText(SecretMessage.getMessage());
    }

    public void finishButtonAction() {
        System.exit(0);
    }
}

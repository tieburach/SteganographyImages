package sample.Controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import sample.Model.SecretImage;
import sample.Model.SecretMessage;

public class ResultWindowController {
    public Button finishButton;
    public TextArea encryptedMessageTextArea;
    public ImageView encryptedPhotoImage;

    public void finishButtonAction() {
        System.exit(0);
    }

    public void initialize(){
        encryptedMessageTextArea.setText(SecretMessage.getMessage());
        encryptedPhotoImage.setImage(SwingFXUtils.toFXImage(SecretImage.getBufferedImage(), null));
    }
}

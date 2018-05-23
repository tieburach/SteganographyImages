package sample.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import sample.Model.MainWindow;
import sample.Model.SecretImage;
import sample.Model.SecretMessage;

import java.io.File;

public class MainWindowController {

    public Button browseForImageButton;
    public Button helpButton;
    public CheckBox encryptCheckBox;
    public CheckBox decryptCheckBox;
    public TextField selectedImageTextField;
    public TextField selectedDataTextField;
    public Button browseForText;
    public ImageView logo;
    public ImageView miniatureOfSelected;
    public Button submitButton;
    private File image;
    private File message;

    public void browseForImageButtonAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Znajdz plik z obrazem");
        image = fileChooser.showOpenDialog(MainWindow.getMainWindowStage());
        selectedImageTextField.setText(image.getAbsolutePath());
    }

    public void helpButtonAction() {
    }

    public void encryptCheckBoxAction() {
        decryptCheckBox.setSelected(false);
    }

    public void decryptCheckBoxAction() {
        encryptCheckBox.setSelected(false);
    }

    public void browseForTextAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Znajdz plik z komunikatem");
        message = fileChooser.showOpenDialog(MainWindow.getMainWindowStage());
        selectedDataTextField.setText(message.getAbsolutePath());
    }

    public void submitButtonAction() {
        SecretImage secretImage = new SecretImage(image.getAbsolutePath());
        if (encryptCheckBox.isSelected()){
            SecretMessage secretMessage = new SecretMessage(message.getAbsolutePath());
            Encrypter encrypter = new Encrypter(secretImage,secretMessage);
        } else {
            SecretMessage secretMessage = new SecretMessage(image.getParent());
            Decrypter decrypter = new Decrypter(secretImage, secretMessage);
        }
    }
}

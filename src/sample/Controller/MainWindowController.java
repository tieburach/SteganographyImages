package sample.Controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import sample.Model.MainWindow;
import sample.Model.SecretImage;
import sample.Model.SecretMessage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

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
    public Label wybierzPlikLabel;
    public Label podgladLabel;
    public Label wybierzObrazLabel;
    private File image;
    private File message;

    public void browseForImageButtonAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Znajdz plik z obrazem");

        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Image files (*.jpg)", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);

        image = fileChooser.showOpenDialog(MainWindow.getMainWindowStage());
        selectedImageTextField.setText(image.getAbsolutePath());


        MainWindow.getMainWindowStage().setWidth(720);
        MainWindow.getMainWindowStage().setHeight(640);

        wybierzObrazLabel.setText("Twój obraz to:");
        browseForImageButton.setVisible(false);
        browseForText.setVisible(true);
        wybierzPlikLabel.setVisible(true);
        encryptCheckBox.setVisible(true);
        decryptCheckBox.setVisible(true);
        miniatureOfSelected.setVisible(true);
        submitButton.setVisible(true);
        podgladLabel.setVisible(true);
        selectedDataTextField.setVisible(true);

        Image miniature = new Image(image.toURI().toString());
        miniatureOfSelected.setImage(miniature);

    }

    public void helpButtonAction() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pomoc");
        alert.setHeaderText("HEADER");
        alert.setContentText("Content Content Content Content Content Content ContentContent Content Content");
        alert.showAndWait();
    }

    public void encryptCheckBoxAction() {
        decryptCheckBox.setSelected(false);
        browseForText.setVisible(true);
        wybierzPlikLabel.setVisible(true);
        selectedDataTextField.setText("Twój plik z wiadomością:");
    }

    public void decryptCheckBoxAction() {
        encryptCheckBox.setSelected(false);
        browseForText.setVisible(false);
        wybierzPlikLabel.setVisible(false);
        selectedDataTextField.setText("Plik zostanie zapisany w folderze z obrazem");
    }

    public void browseForTextAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Znajdz plik z komunikatem");
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        message = fileChooser.showOpenDialog(MainWindow.getMainWindowStage());
        selectedDataTextField.setText(message.getAbsolutePath());
    }

    public void submitButtonAction() {
        SecretImage secretImage = new SecretImage(image.getAbsolutePath());
        if (encryptCheckBox.isSelected()){
            SecretMessage secretMessage = new SecretMessage(message.getAbsolutePath());
            Encrypter encrypter = new Encrypter(secretImage,secretMessage);
            encrypter.run();














        } else {
            SecretMessage secretMessage = new SecretMessage(image.getParent());
            Decrypter decrypter = new Decrypter(secretImage, secretMessage);
        }
    }
}

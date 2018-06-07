package sample.Controller;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import sample.Model.*;

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
    public Label wybierzPlikLabel;
    public Label podgladLabel;
    public Label wybierzObrazLabel;
    public ComboBox comboBox;
    public TextField howMuchCanWeSave;
    public TextField howMuchWeSave;
    private File image;
    private File message;
    private Image miniature;

    public void browseForImageButtonAction() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Znajdz plik z obrazem");
            FileChooser.ExtensionFilter extFilter =
                    new FileChooser.ExtensionFilter("Image files (*.png)", "*.png");
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
            miniature = new Image(image.toURI().toString());
            miniatureOfSelected.setImage(miniature);
        } catch (Exception ignored) {
        }

    }

    public void helpButtonAction() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pomoc");
        alert.setHeaderText("Pomoc do programu");
        alert.setContentText("To jest tajny kryptograficzny program, który może ukrywać informację w obrazach.\n Wybierz obraz, ilość ostatnich bitów i plik tekstowy z wiadomością,\n aby odkodować lub rozkodować obraz.");
        alert.showAndWait();
    }

    public void encryptCheckBoxAction() {
        if (!encryptCheckBox.isSelected()) decryptCheckBoxAction();
        decryptCheckBox.setSelected(false);
        browseForText.setVisible(true);
        wybierzPlikLabel.setVisible(true);
        selectedDataTextField.setText("Twój plik z wiadomością:");
        if (!encryptCheckBox.isSelected()) {
            decryptCheckBox.setSelected(true);
            decryptCheckBoxAction();
        }
    }

    public void decryptCheckBoxAction() {
        encryptCheckBox.setSelected(false);
        browseForText.setVisible(false);
        wybierzPlikLabel.setVisible(false);
        selectedDataTextField.setText("Plik zostanie zapisany w folderze z obrazem");
        if (!decryptCheckBox.isSelected()) {
            encryptCheckBox.setSelected(true);
            encryptCheckBoxAction();
        }
    }

    public void browseForTextAction() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Znajdz plik z komunikatem");
            FileChooser.ExtensionFilter extFilter =
                    new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            message = fileChooser.showOpenDialog(MainWindow.getMainWindowStage());
            selectedDataTextField.setText(message.getAbsolutePath());
            howMuchWeSave.setText(message.length() + " B");
        } catch (Exception ignored) {
        }
    }

    public void submitButtonAction() {
        String partsCan[] = howMuchCanWeSave.getText().split(" ");
        String parts[] = howMuchWeSave.getText().split(" ");
        if (Integer.parseInt(partsCan[0]) < Integer.parseInt(parts[0])) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Nie możesz przejść dalej");
            alert.setContentText("Twój plik jest za duży żeby zmieścić go w obrazie. Spróbuj znaleźć większy obraz.");
            alert.showAndWait();
            return;
        }
        SecretImage secretImage = new SecretImage(image.getAbsolutePath());
        if (encryptCheckBox.isSelected()) {
            SecretMessage secretMessage = new SecretMessage(message.getAbsolutePath());
            Encrypter encrypter = new Encrypter(secretImage, secretMessage);
            encrypter.run(Integer.parseInt(comboBox.getValue().toString()));
            ResultWindow resultWindow = new ResultWindow(MainWindow.getMainWindowStage());
            resultWindow.start();
        } else {
            SecretMessage secretMessage = new SecretMessage(image.getParent());
            Decrypter decrypter = new Decrypter(secretImage, secretMessage);
            decrypter.run(Integer.parseInt(comboBox.getValue().toString()));
            ResultWindowDecryption resultWindowDecryption = new ResultWindowDecryption(MainWindow.getMainWindowStage());
            resultWindowDecryption.start();
        }
    }

    public void comboBoxAction() {
        int howMuchWeCan = (int) miniature.getHeight() * (int) miniature.getWidth() * Integer.parseInt(comboBox.getValue().toString());
        howMuchCanWeSave.setText(howMuchWeCan + " B");
    }
}

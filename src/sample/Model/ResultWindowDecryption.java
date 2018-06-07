package sample.Model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

public class ResultWindowDecryption {
    private static Stage resultWindowDecryptionStage;

    private static Scene scene;

    public ResultWindowDecryption(Stage resultWindowDecryptionStage){
        ResultWindowDecryption.resultWindowDecryptionStage = resultWindowDecryptionStage;
    }

    public void start(){
        MainWindow.getMainWindowStage().setWidth(560);
        MainWindow.getMainWindowStage().setHeight(440);
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../View/ResultWindowDecryptionView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        scene = new Scene(root, 550, 400);
        String css = Main.class.getResource("css/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        resultWindowDecryptionStage.setScene(scene);
        resultWindowDecryptionStage.show();
    }
}


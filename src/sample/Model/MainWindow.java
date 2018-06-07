package sample.Model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

public class MainWindow {

    private static Stage mainWindowStage;

    public static Scene getScene() {
        return scene;
    }

    private static Scene scene;

    public MainWindow(Stage mainWindowStage){
        MainWindow.mainWindowStage = mainWindowStage;
    }

    public void start(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../View/MainWindowView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        scene = new Scene(root, 700, 250);
        String css = Main.class.getResource("css/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        mainWindowStage.setScene(scene);
        mainWindowStage.show();
    }
    public static Stage getMainWindowStage() {
        return mainWindowStage;
    }
}

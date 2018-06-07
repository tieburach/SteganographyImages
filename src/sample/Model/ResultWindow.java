package sample.Model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

public class ResultWindow {

    private static Stage resultWindowStage;

    private static Scene scene;

    public ResultWindow(Stage resultWindowStage){
        ResultWindow.resultWindowStage = resultWindowStage;
    }

    public void start(){
        MainWindow.getMainWindowStage().setWidth(620);
        MainWindow.getMainWindowStage().setHeight(440);
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../View/ResultWindowView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        scene = new Scene(root, 550, 400);
        String css = Main.class.getResource("css/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        resultWindowStage.setScene(scene);
        resultWindowStage.show();
    }
    public static Stage getResultWindowStage() {
        return resultWindowStage;
    }
}

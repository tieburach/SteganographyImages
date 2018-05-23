package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.Model.MainWindow;
import sample.Model.Pixels;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        MainWindow mainWindow = new MainWindow(primaryStage);
        mainWindow.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

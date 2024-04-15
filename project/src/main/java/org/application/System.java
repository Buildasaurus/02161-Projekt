package org.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.application.Controllers.IController;
import org.application.Controllers.MainController;
import org.application.Views.MainView;

import java.io.IOException;

public class System extends Application {
    private static IController controller;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        MainView view = new MainView();
        MainController controller = new MainController(view);
        view.setController(controller);
        scene = new Scene(controller.getView(), 100, 100);
        stage.setScene(scene);
        stage.show();
        java.lang.System.out.println("Highscore at start of game is: ");
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch();
    }
}
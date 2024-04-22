package org.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.application.Controllers.IController;
import org.application.Controllers.MainController;
import org.application.Models.SystemModel;
import org.application.Views.MainView;

import java.io.IOException;

public class App extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        MainView view = new MainView();
        MainController controller = new MainController(view);
        view.setController(controller);
        scene = new Scene(controller.getView(), 300, 300);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void setRoot(IController newController) {
        scene.setRoot(newController.getView());
    }
}

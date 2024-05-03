package org.application;

import javafx.application.Application;
import javafx.scene.Parent;
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
        SystemModel.createDefaultEmployees();
        goToMainMenu();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void setRoot(IController newController) {
        scene.setRoot(newController.getView());
    }
    public static void setRoot(Parent newView) {
        scene.setRoot(newView);
    }

    public static void goToMainMenu()
    {
        MainView view = new MainView();
        MainController controller = new MainController(view);
        view.setController(controller);
        if (scene == null)
        {
            scene = new Scene(controller.getView(), 400, 500);
        }
        else {
            scene.setRoot(controller.getView());
        }
    }
}

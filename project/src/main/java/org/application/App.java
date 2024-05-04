package org.application;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.application.Controllers.IController;
import org.application.Controllers.MainController;
import org.application.Models.SystemModel;
import org.application.Views.IRefreshable;
import org.application.Views.MainView;

import java.io.IOException;

public class App extends Application {
    private static Scene scene;
    private static IController lastController;
    private static IController activeController;

    public static void main(String[] args) {
        launch();
    }

    public static void setRoot(IController newController) {
        if (newController.getView() instanceof IRefreshable) {
            ((IRefreshable) newController.getView()).refreshView();
        }
        scene.setRoot(newController.getView());
        setNewActiveController(newController);
    }

    public static void setRoot(Parent newView) {
        if (newView instanceof IRefreshable) {
            ((IRefreshable) newView).refreshView();
        }
        scene.setRoot(newView);
    }

    public static void goToMainMenu() {
        MainView view = new MainView();
        MainController controller = new MainController(view);
        view.setController(controller);
        setNewActiveController(controller);
        if (scene == null) {
            scene = new Scene(controller.getView(), 500, 500);
        }
        else {
            scene.setRoot(controller.getView());
        }
    }

    private static void setNewActiveController(IController newController) {
        lastController = activeController;
        activeController = newController;
    }

    /**
     * Will return to the last view, if there was such one.
     */
    public static void goToLastView() {
        setRoot(lastController);
    }

    @Override
    public void start(Stage stage) throws IOException {
        SystemModel.createDefaultEmployees();
        goToMainMenu();
        stage.setScene(scene);
        stage.show();
    }
}

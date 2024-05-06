// Everyone
package org.application;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.application.Controllers.IController;
import org.application.Controllers.MainController;
import org.application.Models.*;
import org.application.Views.IRefreshable;
import org.application.Views.MainView;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

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

    public static void createDefaultEmployees() {
        // Loading projects
        Project project = new Project("The Project", new GregorianCalendar(1, 1, 1), new GregorianCalendar(1, 10, 2));

        // Loading employees
        Employee e = new Employee("maju");
        new ReservedActivity(new GregorianCalendar(1, 10, 1),
                new GregorianCalendar(1, 10, 1), "Holiday", e);
        ProjectActivity projectActivity = new ProjectActivity(new GregorianCalendar(1, 1, 1),
                new GregorianCalendar(1, 2, 1), 6, "Testing", project);

        projectActivity.assignEmployee(e);

        GregorianCalendar currentTime = new GregorianCalendar();
        e.createTimeBlock(projectActivity, new GregorianCalendar(currentTime.get(Calendar.YEAR),
                        currentTime.get(Calendar.MONTH), currentTime.get(Calendar.DAY_OF_MONTH), 8, 0),
                new GregorianCalendar(currentTime.get(Calendar.YEAR), currentTime.get(Calendar.MONTH),
                        currentTime.get(Calendar.DAY_OF_MONTH), 10, 0));

        projectActivity = new ProjectActivity(new GregorianCalendar(1, 3, 1), new GregorianCalendar(1, 3, 1), 4,
                "codeRefactor", project);

        projectActivity.assignEmployee(e);

    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Time management application");
        createDefaultEmployees();
        goToMainMenu();
        stage.setScene(scene);
        stage.show();
    }
}


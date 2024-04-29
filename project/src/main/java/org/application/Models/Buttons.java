package org.application.Models;

import javafx.scene.control.Button;
import org.application.App;
import org.application.Controllers.MainController;
import org.application.Views.MainView;

/**
 * Static class for commonly used buttons, to avoid code redundancy
 */
public class Buttons
{
    public static Button returnButton()
    {
        Button returnButton = new Button("Main menu");
        returnButton.setOnAction(e -> {
            MainView view = new MainView();
            MainController controller = new MainController(view);
            view.setController(controller);
            App.setRoot(controller);});
        return  returnButton;
    }
}

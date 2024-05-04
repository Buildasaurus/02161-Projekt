package org.application.Views;

import javafx.scene.control.Button;
import org.application.App;
import org.application.Controllers.MainController;
import org.application.Views.MainView;

/**
 * Static class for commonly used buttons, to avoid code redundancy
 */
public class Buttons
{
    /**
     * A button that returns to the main menu.
     * @return An instance of a button that when clicked returns to the main menu
     */
    public static Button returnButton()
    {
        Button returnButton = new Button("Main menu");
        returnButton.setOnAction(e -> App.goToMainMenu());
        return  returnButton;
    }
}

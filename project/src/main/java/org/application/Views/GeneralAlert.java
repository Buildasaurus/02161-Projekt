package org.application.Views;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class GeneralAlert {
    public static void sendWarning(String warningText) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setContentText(warningText);
        alert.show();
    }
}

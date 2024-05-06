package org.application.Views;

import javafx.scene.control.Alert;

public class GeneralAlert extends Alert {
    public GeneralAlert(String alertText) {
        super(AlertType.WARNING);
        this.setContentText(alertText);
        this.show();
    }
}

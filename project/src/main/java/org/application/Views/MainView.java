package org.application.Views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.application.Controllers.MainController;

import java.awt.color.ICC_ColorSpace;

public class MainView extends StackPane {
    MainController controller;

    public MainView()
    {
    }

    public void setController(MainController controller) {
        this.controller = controller;
        initialize();
    }

    private void initialize()
    {

        // title
        Text title = new Text("!AMaZing ApPliCation!");
        title.setFill(Color.BLACK);
        setAlignment(title, Pos.TOP_CENTER);
        getChildren().add(title);

        // buttons
        Button playButton = new Button("Login");
        playButton.setOnAction(controller::handleButton);

        // Alignment
        VBox vbox = new VBox(0.05);
        vbox.getChildren().addAll(playButton);
        vbox.setStyle("-fx-alignment: center");
        getChildren().add(vbox);

    }

}

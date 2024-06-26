// written by Marinus

package org.application.Views;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import org.application.Controllers.ActivityController;
import org.application.Models.Activity;
import org.application.Models.ProjectActivity;
import org.application.Models.ReservedActivity;

/**
 * A display for the given view.
 *
 * @param activity The given activity to display
 **/
public abstract class ActivityView extends StackPane {
    protected ActivityController controller;
    protected double VBoxSpacing = 5.0;
    protected double HBoxSpacing = 5.0;
    protected Font titleFont = Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 16);

    public static ActivityView createActivityView(Activity activity) {
        if (activity instanceof ProjectActivity) {
            return new ProjectActivityView((ProjectActivity) activity);
        }
        return new ReservedActivityView((ReservedActivity) activity);
    }

    protected void defaultInitialize() {
        this.setPadding(new Insets(5.0));
        Background bg = new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(5.0), null));
        this.setBackground(bg);
        Border border = new Border(
                new BorderStroke(Color.DIMGREY, BorderStrokeStyle.SOLID, new CornerRadii(5.0), new BorderWidths(2.0)));
        this.setBorder(border);
    }
}

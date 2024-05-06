//Jonathan
package org.application.Controllers;

import javafx.scene.Parent;
import org.application.Models.Activity;

public class ActivityController implements IController {
    Parent view;
    Activity activity;

    public ActivityController(Parent view, Activity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Override
    public Parent getView() {
        return view;
    }

    public Activity getActivity() {
        return activity;
    }
}

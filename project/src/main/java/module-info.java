module org.application {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.application.Controllers to javafx.fxml;
    exports org.application;
}
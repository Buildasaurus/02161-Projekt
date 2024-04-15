module org.application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens org.application.Controllers to javafx.fxml;
    exports org.application;
}
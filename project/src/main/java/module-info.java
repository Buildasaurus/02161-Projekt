module org.application {
    requires transitive javafx.controls;
    requires java.desktop;

    opens org.application.Controllers to javafx.fxml;
    exports org.application;
}
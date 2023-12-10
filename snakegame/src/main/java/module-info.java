/**
 * The "com.psymk6" module defines a JavaFX Snake Game application.
 *
 * <p>This module requires JavaFX modules for controls, FXML, and media, as well as
 * the "java.desktop" module for desktop-related features.</p>
 *
 * <p>The module opens specific packages for reflective access by JavaFX FXML and
 * exports packages to make them accessible to other modules.</p>
 *
 * @author Mateusz Klocek
 */
module com.psymk6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;

    opens com.psymk6.models to javafx.fxml;
    opens com.psymk6.controllers to javafx.fxml;

    exports com.psymk6;
}
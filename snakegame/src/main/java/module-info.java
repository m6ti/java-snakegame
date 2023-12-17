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

<<<<<<< Updated upstream
    opens com.psymk6.models to javafx.fxml;
<<<<<<< Updated upstream
=======
<<<<<<< HEAD
    opens com.psymk6 to javafx.fxml;
=======
>>>>>>> Stashed changes
=======
>>>>>>> ed7b0269537f4f19c1f6900666cad23ec8b33336
>>>>>>> Stashed changes
    opens com.psymk6.controllers to javafx.fxml;

    exports com.psymk6;
}
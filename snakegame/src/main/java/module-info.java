module com.COMP2013.javafxmultiscenes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jlayer;

    opens com.psymk6 to javafx.fxml;
    exports com.psymk6;
    exports com.psymk6.controllers;
    opens com.psymk6.controllers to javafx.fxml;
}
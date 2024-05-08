module dictionary {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires javafx.web;
    requires json.simple;
    requires jlayer;

    opens org.controller to javafx.fxml, javafx.controls, javafx.web;
    exports org.controller;
    exports org.app;
    opens org.app to javafx.controls, javafx.fxml, javafx.web;
}
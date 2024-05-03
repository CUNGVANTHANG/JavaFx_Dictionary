module dictionary {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires javafx.web;

    opens org.app to javafx.fxml, javafx.controls, javafx.web;
    exports org.app;

}

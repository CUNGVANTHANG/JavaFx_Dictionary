module dictionary {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;

    opens org.app to javafx.fxml, javafx.controls;
    exports org.app;
}
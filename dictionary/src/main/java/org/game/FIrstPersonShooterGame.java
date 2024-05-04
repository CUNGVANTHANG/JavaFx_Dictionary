package org.game;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class FIrstPersonShooterGame extends Game implements Initializable {
    private double cursorX;
    private double cursorY;

    @FXML
    private AnchorPane rootAnchor;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rootAnchor.setOnMouseMoved(mouseEvent -> {
            cursorX = mouseEvent.getX();
            cursorY = mouseEvent.getY();

        });
    }
}

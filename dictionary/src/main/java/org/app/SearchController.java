package org.app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SearchController {
    @FXML public TextField searchBox; // input

    @FXML public TextArea resultBox; // output

    @FXML public Button buttonBox; // handle


    public void handleButtonBox(MouseEvent e) {
        System.out.println("Ok");
    }

}

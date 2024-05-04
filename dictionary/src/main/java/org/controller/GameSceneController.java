package org.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameSceneController extends Controller {

    @FXML
    private Button closeButton;

    @FXML
    private AnchorPane rootAnchor;

    @FXML
    void handleCloseButtonAction(ActionEvent event) {

    }

    @FXML
    void switchToChooseItemGameScene(ActionEvent event) {

    }

    @FXML
    void switchToMultipleChoiceScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/multipleChoiceScene.fxml"));
        root = loader.load();
        MultipleChoiceController multipleChoiceController = loader.getController();
        multipleChoiceController.setQuestion(event);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}



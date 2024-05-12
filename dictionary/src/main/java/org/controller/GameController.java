package org.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class GameController extends GeneralController {
    @FXML
    private Button quizGameBtn;
    @FXML
    private Button spellingGameBtn;
    protected static int numberQuestions;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        handleEvent();
    }

    @Override
    public void handleEvent() {
        quizGameBtn.setOnMouseClicked(event -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Số lượng câu hỏi");
            dialog.setHeaderText("Bạn muốn thử sức với bao nhiêu câu hỏi?");
            dialog.setContentText("Số lượng:");

            TextField textField = dialog.getEditor();
            textField.setPrefWidth(300);
            textField.setPrefHeight(20);

            Optional<String> result = dialog.showAndWait();

            result.ifPresent(number -> {
                numberQuestions = Integer.parseInt(number);
                System.out.println(numberQuestions);
                if (numberQuestions > 0) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/quizGame.fxml"));
                        gamePane = loader.load();
                        gamePane.getStylesheets().add(getClass().getResource("/css/quizGame.css").toExternalForm());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mainPane.getChildren().setAll(gamePane);
                }
            });
        });
    }

    public void resetGame() {

    }

    public void exitGame() {

    }
}

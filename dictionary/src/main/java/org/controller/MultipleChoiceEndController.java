package org.controller;
import org.game.MultipleChoice;
import org.game.MultipleChoiceQuestion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class MultipleChoiceEndController extends  Controller{

    @FXML
    private Label scoreBox;
    @FXML
    private Label wrong;
    @FXML
    private Label yourScore;

    private final MultipleChoice multipleChoice = MultipleChoice.getMultipleChoice();

    public void switchToGameScene(ActionEvent event) throws IOException {
        FXMLLoader gameScene = new FXMLLoader(getClass().getResource("/fxml/gameScene.fxml"));
        root = gameScene.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void replay(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MultipleChoiceScene.fxml"));
        root = loader.load();
        MultipleChoiceController multipleChoiceController = loader.getController();
        multipleChoiceController.setQuestion(event);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    public void result() {
        int score = multipleChoice.getScore();
        int totalQuestions = multipleChoice.getNumberOfQuestions();
        int wrongAnswers = totalQuestions - score;

        yourScore.setText(String.valueOf(score));
        wrong.setText(String.valueOf(wrongAnswers));
    }


    @FXML
    public void initialize() {
        result();
    }

}


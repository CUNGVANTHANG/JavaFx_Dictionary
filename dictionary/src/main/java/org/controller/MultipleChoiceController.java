package org.controller;
import org.game.MultipleChoice;
import org.game.MultipleChoiceQuestion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.io.IOException;

public class MultipleChoiceController extends Controller {
    private final MultipleChoice multipleChoice = MultipleChoice.getMultipleChoice();
    private int numberOfQuestionsUsed = 0;
    private String correctAnswer;

    @FXML
    private Button AButton;

    @FXML
    private Button BButton;

    @FXML
    private Button CButton;

    @FXML
    private Button DButton;

    @FXML
    private ImageView imageView;
    @FXML
    private Label CorrectAnswer;
    @FXML
    private Label questionBox;

    @FXML
    private Label resultBox;
    @FXML
    private Button nextQuestion;
    @FXML
    private Label scoreBox;


    public void setQuestion(ActionEvent event) throws IOException {
        if (numberOfQuestionsUsed == multipleChoice.getNumberOfQuestions()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MultipleChoiceEnd.fxml"));
            root = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            return;
        }
        resultBox.setText("Choose your answer!");
        CorrectAnswer.setText("");
        MultipleChoiceQuestion currentQuestion = multipleChoice.returnRandomQuestion();
        questionBox.setText(currentQuestion.getQuestion());
        AButton.setText(currentQuestion.getAnswerA());
        BButton.setText(currentQuestion.getAnswerB());
        CButton.setText(currentQuestion.getAnswerC());
        DButton.setText(currentQuestion.getAnswerD());
        correctAnswer = currentQuestion.getCorrectAnswer();
        numberOfQuestionsUsed++;
    }

    public void correct() {
        resultBox.setText("Correct!");
        multipleChoice.increaseHighscore();//tăng điểm kỷ lục
        scoreBox.setText(multipleChoice.getScore() + " ");//tăng điểm
    }

    @FXML
    void choseA(ActionEvent event) {
        if (AButton.getText().equals(correctAnswer)) {
            correct();
            CorrectAnswer.setText(correctAnswer);
        } else {
            resultBox.setText("Wrong!");
            CorrectAnswer.setText(correctAnswer);
        }
    }


    @FXML
    void choseB(ActionEvent event) {
        if (BButton.getText().equals(correctAnswer)) {
            correct();
            CorrectAnswer.setText(correctAnswer);
        } else {
            resultBox.setText("Wrong!");
            CorrectAnswer.setText(correctAnswer);
        }
    }

    @FXML
    void choseC(ActionEvent event) {
        if (CButton.getText().equals(correctAnswer)) {
            correct();
            CorrectAnswer.setText(correctAnswer);
        } else {
            resultBox.setText("Wrong!");
            CorrectAnswer.setText(correctAnswer);
        }
    }

    @FXML
    void choseD(ActionEvent event) {
        if (DButton.getText().equals(correctAnswer)) {
            correct();
            CorrectAnswer.setText(correctAnswer);
        } else {
            resultBox.setText("Wrong!");
            CorrectAnswer.setText(correctAnswer);
        }
    }

    @FXML
    void switchBackToGameScene(ActionEvent event) {

    }

}


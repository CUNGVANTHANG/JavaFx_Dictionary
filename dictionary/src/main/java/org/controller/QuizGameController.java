package org.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import org.base.QuizGame;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class QuizGameController extends GameController {
    @FXML
    protected TextArea question;
    @FXML
    protected Button answerA;
    @FXML
    protected Button answerB;
    @FXML
    protected Button answerC;
    @FXML
    protected Button answerD;
    @FXML
    protected Button nextQuestion;
    @FXML
    protected Text score;

    private String selectAnswer;
    private String correctAnswer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        QuizGame.insertFromFile();
        setQuestion();
        handleEvent();
        handleStyle();
    }

    public void setQuestion() {
        Random random = new Random();
        int length = QuizGame.getGame().size();
        int i = random.nextInt(length - 1);
        question.setText(QuizGame.getGame().get(i).getQuestion());
        answerA.setText(QuizGame.getGame().get(i).getAnswer().get(0).toString());
        answerB.setText(QuizGame.getGame().get(i).getAnswer().get(1).toString());
        answerC.setText(QuizGame.getGame().get(i).getAnswer().get(2).toString());
        answerD.setText(QuizGame.getGame().get(i).getAnswer().get(3).toString());
        correctAnswer = QuizGame.getGame().get(i).getCorrectAnswer();
    }

    public void checkCorrect() {
        System.out.println(correctAnswer.trim().equals(selectAnswer.trim()));
        System.out.println(selectAnswer.trim());
        System.out.println(correctAnswer.trim());

        if (correctAnswer.contains(selectAnswer)) {
            System.out.println("Bạn đúng rùi");
            int calculateScore = Integer.parseInt(score.getText().substring(0 , score.getText().indexOf("/")));
            calculateScore++;
            score.setText(calculateScore + "/20");
        } else {
            System.out.println("Bạn sai rùi");
        }
    }

    @Override
    public void handleEvent() {
        answerA.setOnAction(event -> selectAnswer = String.valueOf(answerA.getText().charAt(0)));
        answerB.setOnAction(event -> selectAnswer = String.valueOf(answerB.getText().charAt(0)));
        answerC.setOnAction(event -> selectAnswer = String.valueOf(answerB.getText().charAt(0)));
        answerD.setOnAction(event -> selectAnswer = String.valueOf(answerC.getText().charAt(0)));

        nextQuestion.setOnAction(event -> {
            checkCorrect();
            setQuestion();
        });
    }

    @Override
    public void handleStyle() {
        question.addEventFilter(javafx.scene.input.MouseEvent.ANY, javafx.event.Event::consume);
        question.setWrapText(true);
    }
}

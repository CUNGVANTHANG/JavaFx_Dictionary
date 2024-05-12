package org.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import org.base.QuizGame;

import java.net.URL;
import java.util.Optional;
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

    private int numberQuestion = 1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        handleStyle();
        QuizGame.insertFromFile();
        setQuestion();
        handleEvent();
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
        if (numberQuestion == numberQuestions) {
            Alert scoreAlert = new Alert(Alert.AlertType.INFORMATION);
            scoreAlert.setTitle("Kết quả");
            scoreAlert.setHeaderText(null);
            scoreAlert.setContentText("Số điểm của bạn: " + score.getText());
            scoreAlert.showAndWait();

            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Chơi lại?");
            confirmAlert.setHeaderText(null);
            confirmAlert.setContentText("Bạn muốn chơi lại không?");

            ButtonType buttonOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            confirmAlert.getButtonTypes().setAll(buttonOK, buttonCancel);

            Optional<ButtonType> result = confirmAlert.showAndWait();

            if (result.isPresent() && result.get() == buttonOK) {
                resetGame();
            } else {
                exitGame();
            }
        }

        System.out.println(correctAnswer.equals(selectAnswer));
        System.out.println(selectAnswer);
        System.out.println(correctAnswer);

        if (correctAnswer.contains(selectAnswer)) {
            System.out.println("Bạn đúng rùi");
            int calculateScore = Integer.parseInt(score.getText().substring(0 , score.getText().indexOf("/")));
            calculateScore++;
            score.setText(calculateScore + "/" + numberQuestions);
        } else {
            System.out.println("Bạn sai rùi");
        }
        numberQuestion++;
    }

    @Override
    public void handleEvent() {
        answerA.setOnAction(event -> selectAnswer = String.valueOf(answerA.getText().charAt(0)));
        answerB.setOnAction(event -> selectAnswer = String.valueOf(answerB.getText().charAt(0)));
        answerC.setOnAction(event -> selectAnswer = String.valueOf(answerB.getText().charAt(0)));
        answerD.setOnAction(event -> selectAnswer = String.valueOf(answerC.getText().charAt(0)));

        nextQuestion.setOnAction(event -> {
            if (selectAnswer != null) {
                checkCorrect();
                setQuestion();
            }
        });

        pronunciationBtn.setOnAction(event -> {
            handlePronunciation("en", question.getText());
        });
    }

    @Override
    public void handleStyle() {
        question.addEventFilter(javafx.scene.input.MouseEvent.ANY, javafx.event.Event::consume);
        question.setWrapText(true);

        System.out.println(numberQuestions);

        score.setText(0 + "/" + numberQuestions);
    }


}

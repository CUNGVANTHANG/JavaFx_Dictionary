package org.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import org.base.Game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class QuizController extends GameController {
    @FXML
    protected TextArea questionArea;
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


    protected static ArrayList<Game> game = new ArrayList<>();
    protected static String answer;
    protected static String correctAnswer = "are";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FileReader fileReader = new FileReader("C:\\Users\\PC\\Desktop\\HK2\\GROUP11-UETOASIS\\dictionary\\src\\main\\resources\\databases\\quiz.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        handleEvent();
    }

    @Override
    public void handleEvent() {
        answerA.setOnAction(event -> {
            answer = answerA.getText();
        });
        answerB.setOnAction(event -> {
            answer = answerB.getText();
        });
        answerC.setOnAction(event -> {
            answer = answerC.getText();

        });
        answerD.setOnAction(event -> {
            answer = answerD.getText();

        });
        nextQuestion.setOnAction(event -> {
            if (answer.equals(correctAnswer)) {
                 int point = Integer.parseInt(score.getText().substring(0, score.getText().indexOf("/")));
                 point++;
                 score.setText(point + "/20" );
            }
        });
    }
}

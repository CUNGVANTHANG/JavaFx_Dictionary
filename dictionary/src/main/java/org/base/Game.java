package org.base;

import java.util.ArrayList;

public class Game {
    private String question;
    private ArrayList answer = new ArrayList();
    private String correctAnswer;

    public ArrayList getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}

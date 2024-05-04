package org.game;

public abstract class Game {
    protected int score;
    public Game() {
        this.score = 0;
    }
    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void increaseHighscore() {
        this.score++;
    }
}

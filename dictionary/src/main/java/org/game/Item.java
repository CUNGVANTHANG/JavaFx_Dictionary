package org.game;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Item {
    private String name;
    private Image image;
    private final String question;
    private boolean isChosen;

    public Item(String name, String imagePath) throws FileNotFoundException {
        this.name = name;
        FileInputStream fileInputStream = new FileInputStream(imagePath);
        this.image = new Image(fileInputStream);
        question = "Chose the correct item: " + name;
        isChosen = false;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getQuestion() {
        return question;
    }

    public boolean isChosen() {
        return isChosen;
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
    }
}

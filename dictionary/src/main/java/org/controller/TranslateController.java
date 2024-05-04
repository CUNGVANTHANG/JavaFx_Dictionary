package org.controller;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import org.base.TranslateAPI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TranslateController extends GeneralController {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        handleEvent();


    }

    @Override
    public void handleEvent() {
        pronunciationBtn.setOnAction(event -> handlePronunciation());

        translateBtn.setOnAction(event -> handleTranslate());
    }

    public void handleTranslate() {
        String target = targetArea.getText();

        try {
            String explain = TranslateAPI.googleTranslate("", "vi", target);
            explainArea.setText(explain);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

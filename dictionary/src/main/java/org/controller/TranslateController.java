package org.controller;

import org.base.Translate;

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
        pronunciationBtn.setOnAction(event -> {
            if (targetArea.getText() != "") {
                handlePronunciation(targetArea.getText());
            }
        });

        translateBtn.setOnAction(event -> handleTranslate());
    }

    public void handleTranslate() {
        String target = targetArea.getText();

        try {
            String explain = Translate.googleTranslate("", "vi", target);
            explainArea.setText(explain);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package org.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import org.base.GoogleTranslate;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TranslateController extends GeneralController {
    @FXML
    private Button pronunciationLangTo;
    @FXML
    private Button pronunciationLangFrom;
    @FXML
    private TextArea langToArea;
    @FXML
    private TextArea langFromArea;
    @FXML
    private ChoiceBox<String> langToBox;
    @FXML
    private ChoiceBox<String> langFromBox;
    private String langTo = "en";
    private String langFrom = "vi";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        handleStyle();
        handleEvent();
    }

    @Override
    public void handleEvent() {
        pronunciationLangTo.setOnAction(event -> {
            if (langToArea.getText() != "") {
                handlePronunciation(langTo,langToArea.getText());
            }
        });

        pronunciationLangFrom.setOnAction(event -> {
            if (langFromArea.getText() != "") {
                handlePronunciation(langFrom, langFromArea.getText());
            }
        });

        translateBtn.setOnAction(event -> handleTranslate());

        langToBox.setOnAction(event -> {
            if (langToBox.getValue().equals("English")) {
                langTo = "en";
            } else if (langToBox.getValue().equals("Vietnamese")) {
                langTo = "vi";
            } else if (langToBox.getValue().equals("French")) {
                langTo = "fr";
            } else if (langToBox.getValue().equals("Japanese")) {
                langTo = "ja";
            } else if (langToBox.getValue().equals("Korean")) {
                langTo = "ko";
            }
        });

        langFromBox.setOnAction(event -> {
            if (langFromBox.getValue().equals("Vietnamese")) {
                langFrom = "vi";
            } else if (langFromBox.getValue().equals("English")) {
                langFrom = "en";
            } else if (langFromBox.getValue().equals("French")) {
                langFrom = "fr";
            } else if (langFromBox.getValue().equals("Japanese")) {
                langFrom = "ja";
            } else if (langFromBox.getValue().equals("Korean")) {
                langFrom = "ko";
            }
        });
    }

    public void handleStyle() {
        langToArea.setWrapText(true);
        langFromArea.setWrapText(true);
        langFromArea.setEditable(false);

        langToBox.setValue("English");
        langToBox.getItems().add("English");
        langToBox.getItems().add("Vietnamese");
        langToBox.getItems().add("French");
        langToBox.getItems().add("Japanese");
        langToBox.getItems().add("Korean");

        langFromBox.setValue("Vietnamese");
        langFromBox.getItems().add("English");
        langFromBox.getItems().add("Vietnamese");
        langFromBox.getItems().add("French");
        langFromBox.getItems().add("Japanese");
        langFromBox.getItems().add("Korean");
    }

    public void handleTranslate()  {
        String word_target = langToArea.getText();

        try {
            String explain = GoogleTranslate.translate(langTo, langFrom, word_target);
            langFromArea.setText(explain);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

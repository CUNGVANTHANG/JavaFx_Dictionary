package org.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private Button langToBtn;
    @FXML
    private Button langFromBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        handleStyle();
        handleEvent();
    }

    @Override
    public void handleEvent() {
        pronunciationLangTo.setOnAction(event -> {
            if (langToArea.getText() != "") {
                handlePronunciation("en",langToArea.getText());
            }
        });

        pronunciationLangFrom.setOnAction(event -> {
            if (langFromArea.getText() != "") {
                handlePronunciation("vi", langFromArea.getText());
            }
        });

        translateBtn.setOnAction(event -> handleTranslate());
    }

    public void handleStyle() {
        langToArea.setWrapText(true);
        langFromArea.setWrapText(true);
        langFromArea.setEditable(false);
    }

    public void handleTranslate()  {
        String word_target = langToArea.getText();

        try {
            String explain = GoogleTranslate.translate("en", "vi", word_target);
            langFromArea.setText(explain);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

package org.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import org.base.Voice;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

// Dùng để xử lý tac vụ
public class GeneralController extends MainController {
    // search.fxml
    @FXML
    protected WebView searchView;
    @FXML
    protected ListView searchList;
    @FXML
    protected TextField searchField;

    @FXML
    protected HTMLEditor modifyEditor;

    @FXML
    protected Button clearBtn;
    @FXML
    protected Button addBtn;
    @FXML
    protected Button modifyBtn;
    @FXML
    protected Button removeBtn;
    @FXML
    protected Button pronunciationBtn;


    // History
//    protected HashMap<>

    // Settings

    // Translate
    @FXML
    protected TextArea targetArea;

    @FXML
    protected TextArea explainArea;

    @FXML
    protected Button langToBtn;

    @FXML
    protected Button langFromBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void handleEvent() {

    }

    public void handlePronunciation(String text) {
        try {
            Voice.speakWord(text);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

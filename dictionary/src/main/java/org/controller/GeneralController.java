package org.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.base.DictionaryCommandLine;
import org.base.DictionaryManagement;
import org.base.Voice;

import java.net.URL;
import java.util.Optional;
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

    @FXML
    protected Button save;

    @FXML
    protected Button cancel;

    protected static boolean isModify = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        handleEvent();
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

    public void handleLookup() {
        String result = DictionaryManagement.dictionaryLookup(searchField.getText());
        searchView.setVisible(true);
        WebEngine webEngine = searchView.getEngine();
        if (result != null) {
            webEngine.loadContent(result);
        } else {
            webEngine.loadContent("<html><body>Từ không được tìm thấy trong từ điển.</body></html>");
        }

        searchField.positionCaret(searchField.getText().length());
    }

    public void handleSearch() {
        String searchText = searchField.getText();
        if (searchText != null && !searchText.isEmpty()) {
            ObservableList<String> searchHistory = FXCollections.observableArrayList(DictionaryCommandLine.dictionarySearcher(searchText));

            int maxItemsToShow = 50;
            ObservableList<String> limitedSearchHistory = FXCollections.observableArrayList();
            for (int i = 0; i < Math.min(searchHistory.size(), maxItemsToShow); i++) {
                limitedSearchHistory.add(searchHistory.get(i));
            }

            searchList.getItems().clear();
            searchList.setItems(limitedSearchHistory);
        } else {
            searchList.getItems().clear();
        }
    }

    public void handleAddWord() {
        System.out.println("Đang thực hiện add");
        isModify = false;
        System.out.println(isModify);
        if (!modifyEditor.isVisible()) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Thêm từ mới");
            dialog.setHeaderText("Nhập từ muốn thêm vào:");
            dialog.setContentText("Từ:");

            TextField textField = dialog.getEditor();
            textField.setPrefWidth(300);
            textField.setPrefHeight(20);

            Optional<String> result = dialog.showAndWait();

            result.ifPresent(word_target -> {
                if (DictionaryManagement.dictionaryLookup(word_target) != null) {
                    ButtonType yes = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Từ đã tồn tại trong từ điển. Bạn có muốn sửa từ này không?", yes, no);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.showAndWait();

                    if (alert.getResult() == yes) {
                        searchField.setText(word_target);
                        handleLookup();
                        handleModifyWord();
                    } else {
                        handleAddWord();
                    }
                } else {
                    save.setVisible(true);
                    cancel.setVisible(true);
                    addBtn.setVisible(false);
                    modifyBtn.setVisible(false);
                    removeBtn.setVisible(false);
                    pronunciationBtn.setVisible(false);
                    searchField.setText(word_target);
                    modifyEditor.setHtmlText("<h1>" + word_target + "</h1>");
                    modifyEditor.setVisible(true);
                }
            });
        } else {
            save.setVisible(false);
            cancel.setVisible(false);
            addBtn.setVisible(true);
            modifyBtn.setVisible(true);
            removeBtn.setVisible(true);
            pronunciationBtn.setVisible(true);
            DictionaryManagement.addWord(searchField.getText(), modifyEditor.getHtmlText());
            handleLookup();
            modifyEditor.setVisible(false);
        }
    }

    public void handleModifyWord() {
        System.out.println("Đang thực hiện modify");
        isModify = true;
        System.out.println(isModify);

        boolean isEditing = modifyEditor.isVisible();
        String result = DictionaryManagement.dictionaryLookup(searchField.getText());
        String word_target = searchField.getText();

        if (!isEditing) {
            modifyEditor.setHtmlText(result);
            if (searchView.isVisible()) {
                modifyEditor.setVisible(true);
                save.setVisible(true);
                cancel.setVisible(true);
                addBtn.setVisible(false);
                modifyBtn.setVisible(false);
                removeBtn.setVisible(false);
                pronunciationBtn.setVisible(false);
            }
            searchView.setVisible(false);
        } else {
            if (modifyEditor.isVisible()) {
                String word_target_modify = modifyEditor.getHtmlText();
                word_target_modify = word_target_modify.substring(word_target_modify.indexOf("<h1>") + 4, word_target_modify.indexOf("</h1>"));

                if (word_target_modify.equals("<br>")) {
                    ButtonType yes = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Vui lòng nhập từ tìm kiếm", yes, no);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.showAndWait();

                    if (alert.getResult() == yes) {
                        alert.close();
                    } else {
                        modifyEditor.setVisible(false);
                    }
                }

                if (word_target_modify.contains("</span>")) {
                    word_target_modify = word_target_modify.substring(word_target_modify.indexOf(">") + 1, word_target_modify.indexOf("</span>"));
                }

                if (word_target_modify.contains("&nbsp;")) {
                    word_target_modify = word_target_modify.substring(0, word_target_modify.indexOf("&nbsp;"))
                            + word_target_modify.substring(word_target_modify.indexOf("&nbsp;") + 6);
                }

                System.out.println(word_target);

                System.out.println(word_target_modify);

                if (modifyEditor.isVisible()) {
                    DictionaryManagement.modifyWord(word_target, word_target_modify, modifyEditor.getHtmlText());
                    WebEngine webEngine = searchView.getEngine();
                    webEngine.loadContent(modifyEditor.getHtmlText());
                }

                searchField.setText(word_target_modify);

                save.setVisible(false);
                cancel.setVisible(false);
                addBtn.setVisible(true);
                modifyBtn.setVisible(true);
                removeBtn.setVisible(true);
                pronunciationBtn.setVisible(true);
            }
            DictionaryManagement.dictionaryUpdate();
            searchList.getItems().clear();
            modifyEditor.setVisible(false);
            searchView.setVisible(true);
        }
    }

    public void handleRemoveWord() {
        if (searchView.isVisible()) {
            ButtonType yes = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có chắc chắn muốn xoá từ này không?", yes, no);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.showAndWait();

            if (alert.getResult() == yes) {
                String searchText = searchField.getText();
                WebEngine webEngine = searchView.getEngine();
                webEngine.loadContent("");
                DictionaryManagement.removeWord(searchText);
                searchField.setText("");
                searchList.getItems().clear();
            }
        }
    }
}

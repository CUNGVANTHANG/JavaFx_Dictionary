package org.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.web.WebEngine;
import org.base.DictionaryCommandLine;
import org.base.DictionaryManagement;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController extends GeneralController {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DictionaryManagement.insertFromFile();
        handleEvent();
    }

    // handleEvent: search.fxml
    @Override
    public void handleEvent() {
        // Event:
        searchField.textProperty().addListener(event -> {
            handleSearch();
        });

        // Event: Mouse Click -> handleLookup()
        searchList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                String selectedWord = (String) searchList.getSelectionModel().getSelectedItem();
                if (selectedWord != null) {
                    searchField.setText(selectedWord);
                    handleLookup();
                }
            }
        });

        // Event: Key UP, Key DOWN -> Get data
        // Event: Key ENTER -> handleLookup()
        searchField.setOnKeyPressed(event -> {
            int currentIndex = searchList.getSelectionModel().getSelectedIndex();
            String selectedWord = (String) searchList.getSelectionModel().getSelectedItem();
            switch (event.getCode()) {
                case UP:
                    if (currentIndex > 0) {
                        searchList.getSelectionModel().select(currentIndex - 1);
                    }
                    break;
                case DOWN:
                    if (currentIndex < searchList.getItems().size() - 1) {
                        searchList.getSelectionModel().select(currentIndex + 1);
                    }
                    break;
                case ENTER:
                    if (currentIndex >= 0 && currentIndex <= searchList.getItems().size() - 1) {
                        searchField.setText(selectedWord);
                            handleLookup();
                    }
                default:
                    break;
            }
        });

        // Event:
        searchField.setOnAction(event -> handleLookup());

        // Event: Click Clear Button -> Clear
        clearBtn.setOnAction(event -> {
            searchField.setText("");
        });

        // Event: Click Search Button -> handleLookup()
        searchBtn.setOnAction(event -> handleLookup());

        addBtn.setOnAction(event -> handleAddWord());

        modifyBtn.setOnAction(event -> handleModifyWord());

        removeBtn.setOnAction(event -> handleRemoveWord());

        pronunciationBtn.setOnAction(event -> handlePronunciation());

    }

    public void handleLookup() {
        String result = DictionaryManagement.dictionaryLookup(searchField.getText());

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
            searchList.getItems().clear();
            searchList.setItems(searchHistory);
        } else {
            searchList.getItems().clear();
        }
    }

    public void handleAddWord() {

    }

    public void handleModifyWord() {
        String searchText = searchField.getText();
    }

    public void handleRemoveWord() {
        ButtonType yes = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
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
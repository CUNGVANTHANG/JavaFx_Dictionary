package org.controller;

import org.base.DictionaryManagement;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController extends GeneralController {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DictionaryManagement.insertFromFile();
        handleEvent();
    }

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
            searchView.setVisible(false);
        });

        // Event: Click Search Button -> handleLookup()
        searchBtn.setOnAction(event -> handleLookup());

        pronunciationBtn.setOnAction(event -> {
            if (searchView.isVisible()) {
                handlePronunciation(searchField.getText());
            }
        });

        addBtn.setOnAction(event -> {
            handleAddWord();
        });

        modifyBtn.setOnAction(event -> {
            handleModifyWord();
        });

        removeBtn.setOnAction(event -> {
            handleRemoveWord();
            isModify = false;
        });

        save.setOnAction(event -> {
            if (isModify) {
                handleModifyWord();
            } else {
                handleAddWord();
            }
        });

        cancel.setOnAction(event -> {
            modifyEditor.setVisible(false);
            searchView.setVisible(true);
            save.setVisible(false);
            cancel.setVisible(false);
            addBtn.setVisible(true);
            modifyBtn.setVisible(true);
            removeBtn.setVisible(true);
            pronunciationBtn.setVisible(true);
        });
    }
}
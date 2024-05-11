package org.controller;

import org.base.Bookmark;
import org.base.DictionaryManagement;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController extends GeneralController {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.handleStyle();
        super.handleEvent();
        handleEvent();
    }

    @Override
    public void handleEvent() {
        addBtn.setOnAction(event -> {
            handleAddWord();
        });

        modifyBtn.setOnAction(event -> {
            handleModifyWord();
        });

        removeBtn.setOnAction(event -> {
            handleRemoveWord();

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
            bookmarkBtn.setVisible(true);
            save.setVisible(false);
            cancel.setVisible(false);
            addBtn.setVisible(true);
            modifyBtn.setVisible(true);
            removeBtn.setVisible(true);
            pronunciationBtn.setVisible(true);
        });

        bookmarkBtn.setOnAction(event -> {
            String result = DictionaryManagement.dictionaryLookup(searchField.getText());
            Bookmark.addWord(searchField.getText(), result);
        });
    }
}
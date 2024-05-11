package org.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.web.WebEngine;
import org.base.Bookmark;

import java.net.URL;
import java.util.ResourceBundle;

public class BookmarkController extends GeneralController{
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.handleStyle();
        super.handleEvent();
        handleEvent();

        ObservableList<String> bookmark = FXCollections.observableArrayList(Bookmark.showBookmark());
        searchList.getItems().clear();
        searchList.setItems(bookmark);
    }

    @Override
    public void handleEvent() {
        removeBtn.setOnAction(event -> {
            handleRemoveWord();
        });

        searchField.textProperty().addListener(event -> {
            if (searchField.getText().isEmpty()) {
                ObservableList<String> bookmark = FXCollections.observableArrayList(Bookmark.showBookmark());
                searchList.getItems().clear();
                searchList.setItems(bookmark);
            }
        });

        searchField.setOnMouseClicked(event -> {
            ObservableList<String> bookmark = FXCollections.observableArrayList(Bookmark.showBookmark());
            searchList.getItems().clear();
            searchList.setItems(bookmark);
        });
    }

    @Override
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
                Bookmark.removeWord(searchText);
                searchField.setText("");
                searchList.getItems().clear();
            }
        }
    }
}

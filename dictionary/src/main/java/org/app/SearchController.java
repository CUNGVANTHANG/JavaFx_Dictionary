package org.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import org.base.DictionaryCommandLine;
import org.base.DictionaryManagement;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController extends GeneralController implements Initializable {
    private String result;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DictionaryManagement.insertFromFile();
        searchBox.textProperty().addListener(event -> {
            handleSearch();
        });

        searchList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                String selectedWord = (String) searchList.getSelectionModel().getSelectedItem();
                if (selectedWord != null) {
                    searchBox.setText(selectedWord);
                    handleLookup();
                }
            }
        });

        searchBox.setOnAction(event -> handleLookup());
        searchButton.setOnAction(event -> handleLookup());
    }

    public void handleLookup() {
        result = DictionaryManagement.dictionaryLookup(searchBox.getText());

        WebEngine webEngine = searchResult.getEngine();
        if (result != null) {
            webEngine.loadContent(result);
        } else {
            webEngine.loadContent("<html><body>Từ không được tìm thấy trong từ điển.</body></html>");
        }
    }

    public void handleSearch () {
        ObservableList<String> searchHistory = FXCollections.observableArrayList(DictionaryCommandLine.dictionarySearcher(searchBox.getText()));
        searchList.setItems(searchHistory);
    }
}

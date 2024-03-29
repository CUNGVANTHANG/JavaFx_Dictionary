package org.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.web.WebEngine;
import org.base.DictionaryCommandLine;
import org.base.DictionaryManagement;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController extends GeneralController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DictionaryManagement.insertFromSQLite();
        handleEvent();
    }

    public void handleEvent() {

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

        searchBox.setOnKeyPressed(event -> {
            int currentIndex = searchList.getSelectionModel().getSelectedIndex();
            String selectedWord = (String) searchList.getSelectionModel().getSelectedItem();
            switch (event.getCode()) {
                case UP:
                    if (currentIndex > 0) {
                        searchList.getSelectionModel().select(currentIndex - 1);
//                        ListView listView = new ListView();
//                        listView.scrollTo(currentIndex - 1);
                    }
                    break;
                case DOWN:
                    if (currentIndex < searchList.getItems().size() - 1) {
                        searchList.getSelectionModel().select(currentIndex + 1);
                    }
                    break;
                case ENTER:
                    if (currentIndex >= 0 && currentIndex <= searchList.getItems().size() - 1) {
                        searchBox.setText(selectedWord);
                        handleLookup();
                    }
                default:
                    break;
            }
        });

        searchBox.setOnAction(event -> handleLookup());

        searchButton.setOnAction(event -> handleLookup());
    }

    public void handleLookup() {
        String result = DictionaryManagement.dictionaryLookup(searchBox.getText());

        WebEngine webEngine = searchResult.getEngine();
        if (result != null) {
            webEngine.loadContent(result);
        } else {
            webEngine.loadContent("<html><body>Từ không được tìm thấy trong từ điển.</body></html>");
        }

        searchBox.positionCaret(searchBox.getText().length());
    }

    public void handleSearch() {
        String searchText = searchBox.getText();
        if (searchText != null && !searchText.isEmpty()) {
            ObservableList<String> searchHistory = FXCollections.observableArrayList(DictionaryCommandLine.dictionarySearcher(searchText));
            searchList.getItems().clear();
            searchList.setItems(searchHistory);
        } else {
            searchList.getItems().clear();
        }
    }
}

package org.app;

import javafx.fxml.Initializable;
import org.base.Word;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HistoryController extends GeneralController implements Initializable  {
    private ArrayList<Word> searchHistory = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public ArrayList<Word> getSearchHistory() {
        return searchHistory;
    }

    public void setSearchHistory(ArrayList<Word> searchHistory) {
        this.searchHistory = searchHistory;
    }
}

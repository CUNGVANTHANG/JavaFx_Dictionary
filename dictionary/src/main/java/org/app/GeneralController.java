package org.app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import org.base.Colors;
import org.base.DictionaryCommandLine;
import org.base.DictionaryManagement;

import java.util.HashSet;

public class GeneralController extends MainController {
    //
    protected DictionaryManagement dictionaryManagement;
    protected DictionaryCommandLine dictionaryCommandLine;
    protected Colors colors;

    //
    protected static final String FILE_HISTORY_PATH = "/src/main/resources/databases/history.txt";
    protected HashSet<String> searchHistory = new HashSet<>();


    // Search
    @FXML
    protected WebView searchResult;
    @FXML
    protected ListView searchList;
    @FXML
    protected TextField searchBox;
    @FXML
    protected Button searchButton;

    // History

    // Settings

}

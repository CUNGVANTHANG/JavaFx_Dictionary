package org.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
//    public void handleSearch(ActionEvent event) {
//        String searchBoxValue = searchBox.getText();
//        DictionaryManagement.insertFromFile();
//        String result = DictionaryManagement.lookup(searchBoxValue);
//        ArrayList<String> searchListValue = DictionaryCommandLine.dictionarySearcher(searchBoxValue);
//
//        // Hiển thị kết quả tìm kiếm trên WebView
//        displayResultOnWebView(result);
//
//        // Hiển thị kết quả tìm kiếm trong ListView
//        displayResultOnListView(searchListValue);
//    }
//
//    private void displayResultOnWebView(String result) {
//        WebEngine webEngine = searchResult.getEngine();
//        webEngine.loadContent("<html><body>" + result + "</body></html>");
//    }
//
//    private void displayResultOnListView(ArrayList<String> searchListValue) {
//        ObservableList<String> items = FXCollections.observableArrayList(searchListValue);
//        searchList.setItems(items);
//    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("search.fxml"));
        primaryStage.setTitle("App");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
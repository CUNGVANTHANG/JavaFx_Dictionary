package org.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    // main.fxml
    @FXML
    private Button searchController;
    @FXML
    private Button translateController;
    @FXML
    private Button gameController;
    @FXML
    private Button storageController;
    @FXML
    private Button historyController;
    @FXML
    private Button settingController;


    @FXML
    private AnchorPane mainContent;
    @FXML
    private AnchorPane searchPane;
    @FXML
    private AnchorPane translatePane;
    @FXML
    private AnchorPane bookmarkPane;
    @FXML
    private AnchorPane historyPane;
    @FXML
    private AnchorPane settingPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // loader file fxml
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/search.fxml"));
            searchPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Khởi tạo mặc định hiển thị search.fxml khi khởi động chương trình
        mainContent.getChildren().setAll(searchPane);
    }

}
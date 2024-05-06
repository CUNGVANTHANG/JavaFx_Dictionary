package org.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// Dùng de dieu hướng
public class MainController implements Initializable {
    // main.fxml
    @FXML
    protected Button searchBtn;

    @FXML
    protected Button translateBtn;
    @FXML
    private Button gameBtn;
    @FXML
    private Button bookmarkBtn;
    @FXML
    private Button historyBtn;
    @FXML
    private Button settingBtn;
    @FXML
    private AnchorPane mainPane;

    private AnchorPane searchPane;
    private AnchorPane translatePane;
    private AnchorPane gamePane;
    private AnchorPane bookmarkPane;
    private AnchorPane historyPane;
    private AnchorPane settingPane;

    // Hàm khởi tạo
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Loader file fxml
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/search.fxml"));
            searchPane = loader.load();
            searchPane.getStylesheets().add(getClass().getResource("/css/search.css").toExternalForm());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/translate.fxml"));
            translatePane = loader.load();
            translatePane.getStylesheets().add(getClass().getResource("/css/translate.css").toExternalForm());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));
            gamePane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/bookmark.fxml"));
            bookmarkPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/history.fxml"));
            historyPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/setting.fxml"));
            settingPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Khởi tạo mặc định hiển thị search.fxml khi khởi động chương trình
        mainPane.getChildren().setAll(searchPane);

        handleEvent();
    }

    public void handleEvent() {
        searchBtn.setOnMouseClicked(event -> {
            mainPane.getChildren().setAll(searchPane);
        });

        translateBtn.setOnMouseClicked(event -> {
            mainPane.getChildren().setAll(translatePane);
        });

        gameBtn.setOnMouseClicked(event -> {
            mainPane.getChildren().setAll(gamePane);
        });

        bookmarkBtn.setOnMouseClicked(event -> {
            mainPane.getChildren().setAll(bookmarkPane);
        });

        historyBtn.setOnMouseClicked(event -> {
            mainPane.getChildren().setAll(historyPane);
        });

        settingBtn.setOnMouseClicked(event -> {
            mainPane.getChildren().setAll(settingPane);
        });
    }
}
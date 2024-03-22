package org.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Main extends Application{
    Stage window;
    Scene scene1, scene2;
    @Override
    public void start(Stage primaryStage)  {
        window = primaryStage;
        // scene1
        Label label = new Label("Welcome");
        Button button1 = new Button("Go to");
        // Tạo sự kiện bấm vào button sẽ chuyển sang scene2
        button1.setOnAction(event -> {
            window.setScene(scene2);
        });
        VBox layout1 = new VBox();
        layout1.getChildren().addAll(label, button1);
        scene1 = new Scene(layout1, 300, 200);

        // scene2
        Button button2 = new Button("Go back");
        // Tạo sự kiện bấm vào button sẽ chuyển sang scene1
        button2.setOnAction(event -> {
            window.setScene(scene1);
        });
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout2, 200, 300);

        window.setScene(scene1);
        // Hiển thị ứng dụng
        window.show();
    }

    public static void main(String[] args) {
        launch();
    }

}

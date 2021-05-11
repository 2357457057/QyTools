package com.qy;

import com.qy.view.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main_AS extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        new MainView().mainView(root);

        root.setBackground(
                new Background(
                        new BackgroundImage(
                                new Image("com/qy/img/IMG_20200417_015014.jpg"),
                                BackgroundRepeat.REPEAT,
                                BackgroundRepeat.REPEAT,
                                BackgroundPosition.DEFAULT,
                                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true)
                        )));

        Scene scene = new Scene(root, 1024, 576);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setMinHeight(288);
        primaryStage.setMinWidth(512);
        primaryStage.getIcons().add(new Image("com/qy/img/qy.jpg"));
        primaryStage.setTitle("轻语AutoSend5.0");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

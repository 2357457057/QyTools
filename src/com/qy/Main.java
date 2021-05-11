package com.qy;

import com.qy.layout.CreateNode;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Tab;

import java.util.concurrent.atomic.AtomicReference;

public class Main extends Application {
    final static public TextArea GLOBAL_INFO_STATION = new TextArea("日志信息台");
    static public Stage primaryStage = null;

    static {
        //不可编辑
        GLOBAL_INFO_STATION.setEditable(false);
        //自动换行
        GLOBAL_INFO_STATION.setWrapText(true);
        GLOBAL_INFO_STATION.setMinSize(526.0, 120.0);
        //GLOBAL_INFO_STATION.setMaxHeight();
        GLOBAL_INFO_STATION.setPrefSize(526.0, 120.0);
    }

    @Override
    public void start(Stage primaryStage) {

        Main.primaryStage = primaryStage;
        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();

        borderPane.setCenter(gridPane);

        VBox btnVb = new VBox();
        borderPane.setLeft(btnVb);
        btnVb.setId("btnVb");
        btnVb.setPrefSize(112, 400);
        btnVb.setAlignment(Pos.TOP_LEFT);
        Button btn1 = createMenuBtn("文件拓展名修改");
        Button btn2 = createMenuBtn("demo2");

        btnVb.getChildren().addAll(btn1, btn2);
        TabPane tabPane = new TabPane();
        tabPane.setTabMinHeight(6.0);
        tabPane.setTabMinWidth(100.0);

        //网格权重
        RowConstraints rowConstraints1 = new RowConstraints();
        rowConstraints1.setPercentHeight(70);
        RowConstraints rowConstraints2 = new RowConstraints();
        rowConstraints2.setPercentHeight(40);
        gridPane.getRowConstraints().addAll(rowConstraints1, rowConstraints2);


        gridPane.addRow(0, tabPane);
        gridPane.add(GLOBAL_INFO_STATION, 0, 1);


        //长宽自增长
        GridPane.setVgrow(tabPane, Priority.ALWAYS);
        GridPane.setVgrow(GLOBAL_INFO_STATION, Priority.ALWAYS);
        GridPane.setHgrow(tabPane, Priority.ALWAYS);
        GridPane.setHgrow(GLOBAL_INFO_STATION, Priority.ALWAYS);

        Tab tabA = createTab("拓展名修改", tabPane, btn1, true, new CreateNode().createGridPane("路经"));
        Tab tabB = createTab("bbb", tabPane, btn2, true, new CreateNode().createGridPane("测试2"));
        Tab[] tabs = new Tab[]{tabA, tabB};
        btn1.setOnAction(event -> {
            tabPane.getTabs().addAll(tabs[0]);
            btn1.setDisable(true);
        });

        btn2.setOnAction(event -> {
            tabPane.getTabs().addAll(tabs[1]);
            btn2.setDisable(true);
            tabPane.getTabs().addAll(tabs[1]);
            btn2.setDisable(true);
        });

        Scene scene = new Scene(borderPane, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("轻语的小工具");
        primaryStage.setMinHeight(331);
        primaryStage.setMinWidth(664);
        scene.getStylesheets().add(Main.class.getResource("css/QyTools.css").toExternalForm());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


    private static Tab createTab(String name, TabPane tabPane, Button btn, Boolean closable, Node node) {
        AtomicReference<Tab> tab = new AtomicReference<>(new Tab(name));
        tab.get().setClosable(closable);
        tab.get().setContent(node);
        tab.get().setOnClosed(event -> {
            btn.setDisable(false);
            tabPane.getTabs().remove(tab.get());
            tab.set(new Tab(name));
        });
        return tab.get();
    }

    private static Button createMenuBtn(String name) {
        Button button = new Button(name);
        button.setMinWidth(112.0);
        return button;
    }


}

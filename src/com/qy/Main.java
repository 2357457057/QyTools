package com.qy;

import com.qy.layout.BuildNodeUtils;
import com.qy.layout.CreateNode;
import com.qy.view.MainView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

public class Main extends Application {
    static public TextArea GLOBAL_INFO_STATION;
    static public Stage primaryStage = null;
    private final BuildNodeUtils FxBuild = new BuildNodeUtils();


    @Override
    public void start(Stage primaryStage) {

        GLOBAL_INFO_STATION = new TextArea("日志信息台");
        //设置透明度
        GLOBAL_INFO_STATION.setOpacity(0.7);
        //不可编辑
        GLOBAL_INFO_STATION.setEditable(false);
        //自动换行
        GLOBAL_INFO_STATION.setWrapText(true);
        GLOBAL_INFO_STATION.setMinSize(526.0, 120.0);
        //GLOBAL_INFO_STATION.setMaxHeight();
        GLOBAL_INFO_STATION.setPrefSize(526.0, 120.0);

        BorderPane AutoSentView = new BorderPane();

        Main.primaryStage = primaryStage;
        BorderPane rootPane = new BorderPane();

        new MainView().mainView(AutoSentView);

        rootPane.setBackground(
                new Background(
                        new BackgroundImage(
                                new Image("com/qy/img/IMG_20200417_015014.jpg"),
                                BackgroundRepeat.REPEAT,
                                BackgroundRepeat.REPEAT,
                                BackgroundPosition.DEFAULT,
                                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true)
                        )));

        GridPane gridPane = new GridPane();
        MenuItem loginItem = new MenuItem("登录");
        MenuItem signItem = new MenuItem("注册");
        MenuItem official_website = new MenuItem("官网");
        official_website.setOnAction(event -> {
            Stage popStage = new Stage();
            BorderPane webBorderPane = new BorderPane();
            Scene scene = new Scene(webBorderPane, 1024, 512);
            WebView browser = new WebView();
            WebEngine webEngine = browser.getEngine();

            webEngine.load("http://yqingyu.top/");
            webBorderPane.setCenter(browser);
            popStage.setScene(scene);
            popStage.initModality(Modality.APPLICATION_MODAL);
            popStage.show();
            popStage.setTitle("轻语QYQ");
        });

        MenuBar menuBar = FxBuild.buildMenuBar("登录", loginItem, signItem, official_website);

        FxBuild.menuBarAdd(menuBar, "文件", "New", "Open", "Save", "Exit");
        FxBuild.menuBarAdd(menuBar, "编辑", "Copy", "Cut", "Paste", "Delete");
        FxBuild.menuBarAdd(menuBar, "工具", "Jdk", "Jre", "Update");
        menuBar.setPadding(new Insets(0, 0, 0, 0));

        menuBar.setOpacity(0.75);


        rootPane.setTop(menuBar);
        rootPane.setCenter(gridPane);


        VBox btnVb = new VBox();
        rootPane.setLeft(btnVb);
        btnVb.setId("btnVb");
        btnVb.setPrefSize(112, 400);
        btnVb.setAlignment(Pos.TOP_LEFT);
        Button btn1 = createMenuBtn("文件拓展名修改");
        Button btn2 = createMenuBtn("AutoSent");

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
        Tab tabB = createTab("AutoSent", tabPane, btn2, true,AutoSentView);
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

        Scene scene = new Scene(rootPane, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("轻语的小工具");
        primaryStage.setMinHeight(331);
        primaryStage.setMinWidth(664);
        primaryStage.getIcons().add(new Image("com/qy/img/qy.jpg"));
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

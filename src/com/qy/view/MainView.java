package com.qy.view;

import com.qy.Threads.AllScreamThread;
import com.qy.Threads.CvThread;
import com.qy.Threads.NormalThread;
import com.qy.layout.AddHBox;
import com.qy.layout.BuildNodeUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainView {
    public static TextArea textArea;
    public static TextField threadCount;
    public static TextField times;
    public static TextField sendCount;
    public static List<Thread> threadList;
    public static Robot robot;
/*    public static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10,
            100,
            10,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<Runnable>(2));*/
    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private final BuildNodeUtils FxBuild = new BuildNodeUtils();

    public void mainView(BorderPane root) {
        AddHBox addHBox = new AddHBox();

        //水平控件1
        HBox hBox1 = addHBox.labelHBox("请输入需要发送的文字,一行便是一句话", "context");
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setCenterShape(true);

        //水平控件2
        HBox hBox2 = addHBox.labelHBox("间隔时间ms 1 ms = 1000s", "context");
        hBox2.setAlignment(Pos.CENTER);
        hBox2.setCenterShape(true);

        //水平控件3
        HBox hBox3 = addHBox.labelHBox("时间根据弹幕间隔时间设置 建议比原时间多 200ms", "context");
        hBox3.setAlignment(Pos.CENTER);
        hBox3.setCenterShape(true);

        //水平控件4
        HBox hBox4 = addHBox.labelHBox("发送次数", "context2");
        FxBuild.boxAdd(hBox4, addHBox.buildHSendCountField("100"));
        hBox4.setAlignment(Pos.CENTER);
        hBox4.setCenterShape(true);

        //top VBOX




        // button
        Button send = new Button("             ");
        FxBuild.setButtonBg(send, "com/qy/img/button11.jpg");


        Button killThread = new Button("                     ");
        FxBuild.setButtonBg(killThread, "com/qy/img/button22.jpg");
        Button replaceKey = new Button("             ");
        FxBuild.setButtonBg(replaceKey, "com/qy/img/button33.jpg");
        HBox buttonHBox = FxBuild.buildHBox(send, killThread, replaceKey);

        buttonHBox.setAlignment(Pos.CENTER);
        buttonHBox.setCenterShape(true);
        buttonHBox.setSpacing(30);
        buttonHBox.setPadding(new Insets(10, 0, 10, 0));
        buttonHBox.setId("buttonHBox");


        // Radio
        RadioButton normalRadio = new RadioButton();
        Label radioLabel1 = addHBox.buildLabel("普通模式", "context");
        RadioButton allScreamRadio = new RadioButton();
        Label radioLabel2 = addHBox.buildLabel("视频全屏模式", "context");
        RadioButton cvRadio = new RadioButton();
        Label radioLabel3 = addHBox.buildLabel("复制粘贴模式", "context");
        normalRadio.setSelected(true);

        ToggleGroup group = new ToggleGroup();
        allScreamRadio.setToggleGroup(group);
        normalRadio.setToggleGroup(group);
        cvRadio.setToggleGroup(group);
        HBox radioHBox = FxBuild.buildHBox(normalRadio, radioLabel1, allScreamRadio, radioLabel2, cvRadio, radioLabel3);
        radioHBox.setPrefHeight(60);
        radioHBox.setPadding(new Insets(10, 20, 0, 20));
        radioHBox.setSpacing(10);

        radioHBox.setAlignment(Pos.CENTER);
        radioHBox.setCenterShape(true);

        radioHBox.setId("radioHBox");


        // Radio Thread

        RadioButton singleTh_Radio = new RadioButton();
        RadioButton multiTh_Radio = new RadioButton();
        ToggleGroup group1 = new ToggleGroup();
        singleTh_Radio.setToggleGroup(group1);
        multiTh_Radio.setToggleGroup(group1);
        singleTh_Radio.setSelected(true);


        addHBox.buildTextField("1");
        threadCount.setDisable(true);
        Label label1 = addHBox.buildLabel("单线程", "context");
        Label label2 = addHBox.buildLabel("多线程", "context");
        Label label3 = addHBox.buildLabel("线程数", "context");
        Label label4 = addHBox.buildLabel("     ", "context");
        multiTh_Radio.setOnAction(event -> threadCount.setDisable(false));
        singleTh_Radio.setOnAction(event -> threadCount.setDisable(true));
        HBox hBox5 = FxBuild.buildHBox(singleTh_Radio, label1, multiTh_Radio, label2, label4, label3, threadCount);
        hBox5.setPadding(new Insets(10, 40, 0, 40));
        hBox5.setAlignment(Pos.CENTER);


        VBox vBox = FxBuild.buildHVBox(hBox1, addHBox.buildTextArea(), hBox2, hBox3, addHBox.buildHTimesField("20200"), hBox4, hBox5, radioHBox, buttonHBox);

        // button事件
        send.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("运行成功，5秒后开始发送");
            alert.setContentText("共" + threadCount.getText() + "个线程" + "每个线程发送" + sendCount.getText() + "次\n请将光标移动到需要发送的地方");
            alert.showAndWait();

            if (threadList == null) {
                threadList = new ArrayList<Thread>();
            }
            if (normalRadio.isSelected()) {
                if (singleTh_Radio.isSelected()) {
                    NormalThread normalThread = new NormalThread();
//                    threadPool.execute(normalThread);

                    threadList.add(normalThread);
                    normalThread.start();
                } else {
                    String text = threadCount.getText();
                    for (int i = 0; i < Integer.parseInt(text); i++) {
                        Thread normalThread = new NormalThread();

//                        threadPool.execute(normalThread);

                        threadList.add(normalThread);
                        normalThread.start();
                    }
                }
            }

            if (allScreamRadio.isSelected()) {
                if (singleTh_Radio.isSelected()) {
                    Thread normalThread = new AllScreamThread();
//                    threadPool.execute(normalThread);
//
                    threadList.add(normalThread);
                    normalThread.start();
                } else {
                    String text = threadCount.getText();
                    for (int i = 0; i < Integer.parseInt(text); i++) {
                        Thread normalThread = new AllScreamThread();

                        threadList.add(normalThread);
                        normalThread.start();
                    }
                }
            }

            if (cvRadio.isSelected()) {
                if (singleTh_Radio.isSelected()) {
                    Thread normalThread = new CvThread();
//                    threadPool.execute(normalThread);
//
                    threadList.add(normalThread);
                    normalThread.start();
                } else {
                    String text = threadCount.getText();
                    for (int i = 0; i < Integer.parseInt(text); i++) {
                        Thread normalThread = new CvThread();
//                        threadPool.execute(normalThread);

                        threadList.add(normalThread);
                        normalThread.start();
                    }
                }
            }
        });

        killThread.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("");

            if (threadList != null) {
                alert.setHeaderText("Kill_Successful");

//                List<Runnable> runnables = threadPool.shutdownNow();
//                threadPool.shutdown();

                //threadList.size();

                alert.setContentText(threadList.size() + "threads was killed");

                for (Thread thread : threadList) {
                    thread.stop();
                }
                threadList = new ArrayList<>();
            } else {

//                threadPool.shutdownNow();

                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setHeaderText("Kill_Defeat");
                alert.setContentText("You don't have any threads open");
            }
            alert.showAndWait();
        });

        replaceKey.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("释放成功");
            alert.setContentText("😀");
            alert.showAndWait();
            robot.keyRelease(KeyEvent.VK_CONTROL);
        });


        root.setCenter(vBox);

    }

    public void rebuildMainView(BorderPane borderPane) {

        VBox vBox = FxBuild.buildHVBox();

    }
}

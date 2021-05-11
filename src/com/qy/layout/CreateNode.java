package com.qy.layout;

import com.qy.Main;
import com.qy.tools.FileExtensionModify;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

public class CreateNode {

    private static String String_File;


    public GridPane createGridPane(String name) {

        GridPane gridPane = new GridPane();

        Label pathLb = new Label(name);
        pathLb.setId("pathLb");
        TextField pathTF = new TextField();
        pathTF.setId("pathTF");
        pathLb.autosize();

        gridPane.add(pathTF, 1, 0);
        gridPane.add(pathLb, 0, 0);


        Label newExtensionLb = new Label("新拓展名");
        TextField newExtensionTF = new TextField();
        newExtensionLb.setId("newExtensionLb");
        newExtensionTF.setId("newExtensionTF");
        newExtensionLb.autosize();
        newExtensionTF.autosize();

        gridPane.add(newExtensionLb, 0, 2);
        gridPane.add(newExtensionTF, 1, 2);

        Label oldExtensionLb = new Label("旧拓展名");
        oldExtensionLb.setId("oldExtensionLb");
        oldExtensionLb.autosize();
        TextField oldExtensionTF = new TextField();
        oldExtensionTF.setId("oldExtensionTF");
        oldExtensionTF.autosize();

        gridPane.add(oldExtensionLb, 0, 1);
        gridPane.add(oldExtensionTF, 1, 1);

        gridPane.setPadding(new Insets(0));
        gridPane.setVgap(10.0);
        gridPane.setHgap(10.0);

        Button button = new Button("submit");
        button.setDefaultButton(true);
        button.setId("submit");
        ToggleGroup group = new ToggleGroup();
        RadioButton rBtn1 = new RadioButton("单一文件修改");
        RadioButton rBtn2 = new RadioButton("当前路径多文件修改");
        RadioButton rBtn3 = new RadioButton("当前路径递归修改");
        rBtn1.setToggleGroup(group);
        rBtn2.setToggleGroup(group);
        rBtn3.setToggleGroup(group);


        HBox hBox = new HBox();
        hBox.setId("radioHBox");
        hBox.setSpacing(20);
        hBox.getChildren().addAll(rBtn1, rBtn2, rBtn3);
        rBtn2.setSelected(true);


        Button pathBtn = new Button();
        pathBtn.setOnAction(event -> {
            Main.GLOBAL_INFO_STATION.appendText("\n[info]" + "选择文件");
            FileChooser filechooser = new FileChooser();
            File file = filechooser.showOpenDialog(Main.primaryStage);
            kkk(pathBtn, file);
        });

        pathBtn.setId("pathBtn");
        //判断是否执行了rBtn1 false 未执行
        AtomicReference<Boolean> flag = new AtomicReference<>(false);
        //添加一个监听器监听RadioButton修改
        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            String_File = null;
            if (rBtn1.isSelected()) {
                oldExtensionTF.setDisable(true);
                Main.GLOBAL_INFO_STATION.appendText("\n[info]" + "当前功能:[单一文件修改]");
                FileChooser filechooser = new FileChooser();
                File file = filechooser.showOpenDialog(Main.primaryStage);
                gridPane.getChildren().remove(pathTF);
                kkk(pathBtn, file);
                if (!flag.get())
                    gridPane.add(pathBtn, 1, 0);
                flag.set(true);
            }

            if (rBtn2.isSelected()) {
                oldExtensionTF.setDisable(false);
                Main.GLOBAL_INFO_STATION.appendText("\n[info]" + "当前功能:[当前路径多文件修改]");
                if (flag.get()) {
                    gridPane.getChildren().remove(pathBtn);
                    gridPane.add(pathTF, 1, 0);
                    flag.set(false);
                }

            }
            if (rBtn3.isSelected()) {
                oldExtensionTF.setDisable(false);
                Main.GLOBAL_INFO_STATION.appendText("\n[info]" + "当前功能:[当前路径递归修改]");
                if (flag.get()) {
                    gridPane.getChildren().remove(pathBtn);
                    gridPane.add(pathTF, 1, 0);
                    flag.set(false);
                }
            }
        });

        FileExtensionModify fileExtensionModify = new FileExtensionModify();

        button.setOnAction(event -> {
            if (rBtn1.isSelected()) {
                if (String_File!=null){
                    Main.GLOBAL_INFO_STATION.appendText("\n[info]" + "当前功能:[单一文件修改]");
                    fileExtensionModify.singleModify(String_File,newExtensionTF.getText());
                }
                else {
                    Main.GLOBAL_INFO_STATION.appendText("\n[error]" + "尚未选择文件");
                }
            }
            if (rBtn2.isSelected()) {
                Main.GLOBAL_INFO_STATION.appendText("\n[info]" + "当前功能:[当前路径文件修改]");
               fileExtensionModify.currentPathModify(pathTF.getText(),oldExtensionTF.getText(),newExtensionTF.getText());
            }
            if (rBtn3.isSelected()) {
                System.out.println("当前路径多文件修改");
            }
        });

        gridPane.add(hBox, 1, 3);
        gridPane.add(button, 2, 3);

        return gridPane;
    }

    private void kkk(Button pathBtn, File file) {
        if (file != null) {
            Main.GLOBAL_INFO_STATION.appendText("\n[Info]" + "路径: " + file.getPath() + " 文件名: " + file.getName() + "已选择");
            pathBtn.setText("已选择>>" + file.getName() + "<<");
            String_File = file.getAbsolutePath();
        } else {
            Main.GLOBAL_INFO_STATION.appendText("\n[Info]" + "用户取消选择");
            pathBtn.setText("选择一个文件");
            String_File = null;
        }
    }
}

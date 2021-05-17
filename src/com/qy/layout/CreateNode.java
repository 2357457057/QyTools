package com.qy.layout;

import com.qy.Main;
import com.qy.tools.FileExtensionModify;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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


        AtomicReference<File> fileChosen = new AtomicReference<>();
        Button pathBtn = new Button();
        pathBtn.setOnAction(event -> {
            Main.GLOBAL_INFO_STATION.appendText("\n[info]" + "选择文件");
            FileChooser filechooser = new FileChooser();
            fileChosen.set(filechooser.showOpenDialog(Main.primaryStage));
            kkk(pathBtn, fileChosen.get());
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
                fileChosen.set(file);
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
               fileExtensionModify.currentPathModify(new File(pathTF.getText().trim()) ,oldExtensionTF.getText(),newExtensionTF.getText());
            }
            if (rBtn3.isSelected()) {
                fileExtensionModify.currentPathRecursiveModify(new File(pathTF.getText().trim()) ,oldExtensionTF.getText(),newExtensionTF.getText());
                System.out.println("当前路径多文件修改");
            }
        });

        gridPane.add(hBox, 1, 3);
        gridPane.add(button, 1, 4,2,1);
        GridPane.setHalignment(button,HPos.RIGHT);
        GridPane.setMargin(button,new Insets(0,30,5,0));

        HBox.setHgrow(rBtn1,Priority.ALWAYS);
        HBox.setHgrow(rBtn2,Priority.ALWAYS);
        HBox.setHgrow(rBtn3,Priority.ALWAYS);
        hBox.setAlignment(Pos.CENTER);

        gridPane.setAlignment(Pos.CENTER_LEFT);

        GridPane.setHgrow(hBox,Priority.ALWAYS);

        /*        pathTF.setPadding(new Insets(0,10,0,0));
        newExtensionTF.setPadding(new Insets(0,10,0,0));
        oldExtensionTF.setPadding(new Insets(0,10,0,0));*/
        GridPane.setHgrow(pathTF,Priority.ALWAYS);
        GridPane.setHgrow(newExtensionTF,Priority.ALWAYS);
        GridPane.setHgrow(oldExtensionTF, Priority.ALWAYS);

        GridPane.setVgrow(pathTF,Priority.ALWAYS);
        GridPane.setVgrow(newExtensionTF,Priority.ALWAYS);
        GridPane.setVgrow(oldExtensionTF, Priority.ALWAYS);

        GridPane.setHgrow(pathLb,Priority.ALWAYS);
        GridPane.setHgrow(newExtensionLb,Priority.ALWAYS);
        GridPane.setHgrow(oldExtensionLb, Priority.ALWAYS);

//        GridPane.setVgrow(pathLb,Priority.NEVER);
//        GridPane.setVgrow(newExtensionLb,Priority.NEVER);
//        GridPane.setVgrow(oldExtensionLb, Priority.NEVER);

        GridPane.setMargin(pathTF,new Insets(0,20,0,0));
        GridPane.setMargin(oldExtensionTF,new Insets(0,20,0,0));
        GridPane.setMargin(newExtensionTF,new Insets(0,20,0,0));
        GridPane.setMargin(pathLb,new Insets(0,0,10,20));
        GridPane.setMargin(oldExtensionLb,new Insets(0,10,0,20));
        GridPane.setMargin(newExtensionLb,new Insets(0,10,0,20));

        GridPane.setHalignment(pathLb, HPos.LEFT);
        GridPane.setValignment(pathLb, VPos.CENTER);
        GridPane.setHalignment(oldExtensionLb, HPos.LEFT);
        GridPane.setValignment(oldExtensionLb, VPos.CENTER);
        GridPane.setHalignment(newExtensionLb, HPos.LEFT);
        GridPane.setValignment(newExtensionLb, VPos.CENTER);

        GridPane.setHalignment(pathTF, HPos.LEFT);
        GridPane.setValignment(pathTF, VPos.CENTER);
        GridPane.setHalignment(oldExtensionTF, HPos.LEFT);
        GridPane.setValignment(oldExtensionTF, VPos.CENTER);
        GridPane.setHalignment(newExtensionTF, HPos.LEFT);
        GridPane.setValignment(newExtensionTF, VPos.CENTER);



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

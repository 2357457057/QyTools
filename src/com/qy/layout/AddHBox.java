package com.qy.layout;

import com.qy.view.MainView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;



public class AddHBox {
     BuildNodeUtils FxBuild = new BuildNodeUtils();

    public HBox buildHTextField(TextField textField , String defaultValue){
        HBox hBox = new HBox();
        hBox.setBackground(Background.EMPTY);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.setPadding(new Insets(3));
        textField = new TextField(defaultValue);
        textField.setOpacity(0.70);
        hBox.getChildren().addAll(textField);
        hBox.setPadding(new Insets(10,40,0,40));
        HBox.setHgrow(textField, Priority.ALWAYS);
        hBox.setSpacing(5);
        return hBox;
    }

    public HBox buildHSendCountField(String defaultValue){
        HBox hBox = new HBox();
        hBox.setBackground(Background.EMPTY);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.setPadding(new Insets(3));
        MainView.sendCount = new TextField(defaultValue);
        MainView.sendCount.setOpacity(0.70);
        hBox.getChildren().addAll(MainView.sendCount);
        hBox.setPadding(new Insets(10,40,0,40));
        HBox.setHgrow(MainView.sendCount, Priority.ALWAYS);
        hBox.setSpacing(5);
        return hBox;
    }
    public HBox buildHTimesField(String defaultValue){
        HBox hBox = new HBox();
        hBox.setBackground(Background.EMPTY);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.setPadding(new Insets(3));
        MainView.times = new TextField(defaultValue);
        MainView.times.setOpacity(0.70);
        hBox.getChildren().addAll(MainView.times);
        hBox.setPadding(new Insets(10,40,0,40));
        HBox.setHgrow(MainView.times, Priority.ALWAYS);
        hBox.setSpacing(5);
        return hBox;
    }

    public TextField buildTextField( String defaultValue){
        MainView.threadCount = new TextField(defaultValue);
        MainView.threadCount.setOpacity(0.70);
        return MainView.threadCount;
    }

    public Label buildLabel(String name,String StyleClass){
        Label label = new Label(name);
        label.getStyleClass().add(StyleClass);
        return label;
    }

    public HBox labelHBox(String name,String StyleClass){
        Label label = new Label(name);
        label.getStyleClass().add(StyleClass);
        return FxBuild.buildHBox(label);
    }

    public HBox buildTextArea(){
        HBox hBox = new HBox();
        hBox.setBackground(Background.EMPTY);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.setPadding(new Insets(3));
        MainView.textArea = new TextArea();

        MainView.textArea.setOpacity(0.70);
        hBox.getChildren().addAll( MainView.textArea);
        hBox.setPadding(new Insets(10,40,0,40));
        HBox.setHgrow(MainView.textArea, Priority.ALWAYS);
        hBox.setSpacing(5);
        return hBox;
    }
}

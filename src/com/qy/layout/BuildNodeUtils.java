package com.qy.layout;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class BuildNodeUtils {

    public HBox buildHBox(Node... node) {
        HBox hBox = new HBox();
        hBox.getChildren().addAll(node);
        return hBox;
    }

    public void boxAdd(Pane box, Node... nodes) {
        box.getChildren().addAll(nodes);
    }


    public VBox buildHVBox(Node... node) {
        VBox vBox = new VBox();
        vBox.getChildren().addAll(node);
        return vBox;
    }

    /**
     *
     * @param menu 自动创建
     * @param menuItems
     * @return
     */
    public MenuBar buildMenuBar(String menu, String... menuItems) {
        MenuBar menuBar = new MenuBar();
        Menu menu_ = new Menu(menu);
        menuBar.getMenus().add(menu_);
        for (String s : menuItems) {
            menu_.getItems().add(new MenuItem(s));
        }
        return menuBar;
    }

    public MenuBar buildMenuBar(String menu, MenuItem... menuItems) {
        MenuBar menuBar = new MenuBar();
        Menu menu_ = new Menu(menu);
        menuBar.getMenus().add(menu_);
        for (MenuItem s : menuItems) {
            menu_.getItems().add(s);
        }
        return menuBar;
    }

    /**
     *
     * @param menuBar 菜单栏
     * @param menu  菜单
     * @param menuItems 菜单项数组
     * @return
     */
   public MenuBar menuBarAdd(MenuBar menuBar,Menu menu,MenuItem... menuItems){
        menuBar.getMenus().add(menu);
        menu.getItems().addAll(menuItems);
        return menuBar;
    }

    /**
     * todo 创建 menu 创建样式
     * @param menuBar   MenuBar
     * @param menu      String
     * @param menuItems String
     * @return  MenuBar
     */
    public MenuBar menuBarAdd(MenuBar menuBar,String menu,String... menuItems){
        Menu menu_ = new Menu(menu);
        menuBar.getMenus().add(menu_);
        for (String menuItem : menuItems) {
            menu_.getItems().add(new MenuItem(menuItem));
        }
        return menuBar;
    }

    public MenuBar menuBarAdd(MenuBar menuBar,Menu menu,String... menuItems){

        menuBar.getMenus().add(menu);
        for (String menuItem : menuItems) {
            menu.getItems().add(new MenuItem(menuItem));
        }
        return menuBar;
    }

    /**
     * todo 创建 menu
     * @param menuBar   MenuBar
     * @param menu      String
     * @param menuItems MenuItems
     * @return  MenuBar
     */
    public MenuBar menuBarAdd(MenuBar menuBar,String menu,MenuItem... menuItems){
        Menu menu_ = new Menu(menu);
        menuBar.getMenus().add(menu_);

        for (MenuItem menuItem : menuItems) {
            menu_.getItems().add(menuItem);
        }
        return menuBar;
    }

    public Menu menuAdd(Menu menu1,Menu menu2,MenuItem... menuItems){
            menu1.getItems().add(menu2);
            menu2.getItems().addAll(menuItems);
        return menu1;
    }

    public Menu menuAdd(Menu menu1,String menu2,String... menuItems){
        Menu menu_ = new Menu(menu2);
        menu1.getItems().add(menu_);
        for (String menuItem : menuItems) {
            menu_.getItems().add(new MenuItem(menuItem));
        }
        return menu1;
    }
    public void setButtonBg(Button button,String url){
        button.setBackground(new Background(new BackgroundImage(new Image(url),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true))));


    }




}

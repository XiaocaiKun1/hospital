package com.neusoft.neu24.his.neuhospital.Controller;

import com.neusoft.neu24.his.neuhospital.MyButton.MyButton;
import com.neusoft.neu24.his.neuhospital.util.GlobalData;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class homeController_4yaofang {
    @FXML
    HBox home_box;
    @FXML
    VBox home_left;
    @FXML
    AnchorPane home_right;
    @FXML
    public void fayao_return() throws IOException
    {
        GlobalData.switchScene1("home-view.fxml", 1280, 768, "登录");
    }
    @FXML
    public void initialize()
    {
        MyButton btn_fayao=new MyButton("发药", "tuiyao.png", "fayao-view.fxml", home_right);
        home_left.getChildren().add(btn_fayao);

        MyButton btn_tuiyao=new MyButton("退药", "tuiyao.png", "tuiyao-view.fxml", home_right);
        home_left.getChildren().add(btn_tuiyao);

        MyButton btn_guanli=new MyButton("药品管理", "tuiyao.png", "guanli-view.fxml", home_right);
        home_left.getChildren().add(btn_guanli);




    }
}

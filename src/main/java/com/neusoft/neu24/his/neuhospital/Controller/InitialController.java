package com.neusoft.neu24.his.neuhospital.Controller;

import com.neusoft.neu24.his.neuhospital.MyButton.MyButton;
import com.neusoft.neu24.his.neuhospital.util.GlobalData;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InitialController {
    @FXML
    HBox home_rooot;
    @FXML
    VBox home_left;
    @FXML
    AnchorPane home_right;
    @FXML
    public void btn_return() throws Exception
    {
        GlobalData.switchScene1("home-view.fxml", 1280, 768, "登录");
    }

    @FXML
    public  void initialize() {
        //创建按钮
        MyButton btn = new MyButton("检查申请", "search.png", "apply-view.fxml"  , home_right );
        home_left.getChildren().add(btn);

        btn = new MyButton("患者录入", "search.png", "enter1-view.fxml"  , home_right );
        home_left.getChildren().add(btn);

        btn = new MyButton("检查结果录入", "search.png", "enter2-view.fxml"  , home_right );
        home_left.getChildren().add(btn);

        btn = new MyButton("检查管理" , "search.png", "manage-view.fxml"  , home_right );
        home_left.getChildren().add(btn);


    }
}

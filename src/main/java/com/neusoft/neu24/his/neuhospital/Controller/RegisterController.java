package com.neusoft.neu24.his.neuhospital.Controller;

import com.neusoft.neu24.his.neuhospital.MyButton.MyButton;
import com.neusoft.neu24.his.neuhospital.util.GlobalData;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class RegisterController {
    @FXML
    private VBox register_left;

    @FXML
    private AnchorPane register_right;

    @FXML
    private HBox register_root;

    public RegisterController() {
    }


    @FXML
    public  void initialize() {



        MyButton btn = new MyButton("挂号", "guahao.png", "guahao-view.fxml"  , register_right );
        register_left.getChildren().add(btn);

        btn = new MyButton("收费", "guahao.png", "shoufei-view.fxml"  , register_right );
        register_left.getChildren().add(btn);

        btn = new MyButton("退费", "guahao.png", "tuifei-view.fxml"  , register_right );
        register_left.getChildren().add(btn);

        btn = new MyButton("退号", "guahao.png", "tuihao-view.fxml"  , register_right );
        register_left.getChildren().add(btn);


    }

    @FXML
    void btn_exit() throws IOException {
        GlobalData.switchScene1("main-home-view.fxml",1280,768,"东软云医院");
    }

}

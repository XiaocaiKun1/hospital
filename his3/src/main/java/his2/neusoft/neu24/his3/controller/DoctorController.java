package his2.neusoft.neu24.his3.controller;

import his2.neusoft.neu24.his3.Mybutton.MyButton;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import his2.neusoft.neu24.his3.util.GlobalData;

public class DoctorController {
    @FXML
    HBox home_rooot;
    @FXML
    VBox home_left;

    @FXML
    AnchorPane home_right;
    @FXML
    public void return2() throws Exception {
        GlobalData.switchScene("main-view.fxml", 1280, 768, "登录");
    }
    @FXML
    public void initialize() {

        MyButton btn = new MyButton("患者叫号", "yisheng.png", "jiaohao-view.fxml", home_right);
        home_left.getChildren().add(btn);

        btn = new MyButton("初步诊断", "yisheng.png", "zhenduan-view.fxml", home_right);
        home_left.getChildren().add(btn);

        btn = new MyButton("开立处方", "yisheng.png", "chufang-view.fxml", home_right);
        home_left.getChildren().add(btn);
    }

}

package his2.neusoft.neu24.his3.controller;

import his2.neusoft.neu24.his3.util.GlobalData;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField tx_userName;
    @FXML
    private PasswordField password;


    @FXML
    public void bt_login() throws Exception {
        if(tx_userName.getText().equals("admin") && password.getText().equals("admin"))
        {
            GlobalData.switchScene("main-view.fxml", 1280, 768, "医院系统");
            GlobalData.initRegisterList();
        }

        else {
            Stage popupStage = new Stage();
            // 设置新窗口的标题
            popupStage.setTitle("医院系统 | 登录");

            // 创建一个简单的UI组件作为新窗口的内容，这里以一个带有文本的StackPane为例
            StackPane popupLayout = new StackPane();
            popupLayout.getChildren().add(new javafx.scene.control.Label("账号密码错误,请重新登陆"));

            // 设置新窗口的场景，包括根节点和尺寸
            Scene popupScene = new Scene(popupLayout, 300, 200);
            popupStage.setScene(popupScene);
            // 确保当新窗口关闭时不会阻止主应用关闭（根据需要调整）
            popupStage.initModality(javafx.stage.Modality.NONE);
            // 显示新窗口
            popupStage.show();
        }
    }

}

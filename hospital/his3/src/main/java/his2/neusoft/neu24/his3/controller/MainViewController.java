package his2.neusoft.neu24.his3.controller;

import javafx.fxml.FXML;
import his2.neusoft.neu24.his3.util.GlobalData;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import java.io.IOException;


public class MainViewController {

    @FXML
    private AnchorPane home_right;

    @FXML
    public void btn_register() throws Exception {
        GlobalData.switchScene("register-view.fxml",1280,768,"东软云医院");
    }

    @FXML
    public void btn_yisheng()throws Exception{
        GlobalData.switchScene("doctor-view.fxml",1280,768,"东软云医院");
    }
    @FXML
    public void btn_jiancha()throws Exception{
        GlobalData.switchScene("jiancha-view.fxml",1280,768,"东软云医院");
    }

    @FXML
    public void btn_yaofang() throws Exception {
        GlobalData.switchScene("fayao-view.fxml", 1280, 768, "东软云医院");
    }

    @FXML
    public void btn_zhushou() throws IOException {
        // 清空右侧区域
        home_right.getChildren().clear();

        // 加载 chat-view.fxml 到右侧 AnchorPane
        Node chatView = FXMLLoader.load(getClass().getResource("/his2/neusoft/neu24/his3/chat-view.fxml"));
        AnchorPane.setTopAnchor(chatView, 0.0);
        AnchorPane.setBottomAnchor(chatView, 0.0);
        AnchorPane.setLeftAnchor(chatView, 0.0);
        AnchorPane.setRightAnchor(chatView, 0.0);

        home_right.getChildren().add(chatView);
    }
}

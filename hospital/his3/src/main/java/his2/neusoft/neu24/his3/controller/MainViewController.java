package his2.neusoft.neu24.his3.controller;

import javafx.fxml.FXML;
import his2.neusoft.neu24.his3.util.GlobalData;
import java.io.IOException;


public class MainViewController {
    @FXML
    public void btn_register() throws Exception {
        GlobalData.switchScene("register-view.fxml",1280,768,"东软云医院");
    }

    @FXML
    public void btn_yisheng()throws Exception{
        GlobalData.switchScene("doctor-view.fxml",1280,768,"东软云医院");
    }


    @FXML
    public void btn_yaofang() throws Exception {
        GlobalData.switchScene("fayao-view.fxml", 1280, 768, "东软云医院");
    }
}

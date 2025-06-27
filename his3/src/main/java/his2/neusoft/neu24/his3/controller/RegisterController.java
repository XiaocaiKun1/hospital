package his2.neusoft.neu24.his3.controller;


import his2.neusoft.neu24.his3.Mybutton.MyButton;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import his2.neusoft.neu24.his3.util.GlobalData;
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

        btn = new MyButton("退号", "guahao.png", "tuihao-view.fxml"  , register_right );
        register_left.getChildren().add(btn);


    }

    @FXML
    void btn_exit() throws IOException {
        GlobalData.switchScene("main-view.fxml",1280,768,"东软云医院");
    }

}
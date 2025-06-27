package his2.neusoft.neu24.his3.Mybutton;


import his2.neusoft.neu24.his3.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class MyButton extends Button {

    public MyButton(String title, String imgname, String viewname, AnchorPane register_right) {

        File file = new File("src/main/resources/images" +imgname);
        String string = file.toURI().toString();
        Image image = new Image(string);
        ImageView view = new ImageView(image);
        view.setFitHeight(50);
        view.setFitWidth(50);
        this.setGraphic( view  );
        this.setText(title);

        setOnAction( ( event )->{
            FXMLLoader fxmlLoader =new FXMLLoader( HelloApplication.class.getResource(viewname) );
            try {
//                读取fxml文件，转化为 AnchorPane对象
                AnchorPane pane =  fxmlLoader.load();
                pane.setLayoutX(0);
                pane.setLayoutY(0);
                register_right.getChildren().clear();
                register_right.getChildren().add(pane);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } );

        this.setStyle("-fx-font-size:100");
        this.setStyle("-fx-font-family: 'JetBrains Mono NL SemiBold'");
        this.setPrefWidth(200);
        this.setPrefHeight(100);
    }
}
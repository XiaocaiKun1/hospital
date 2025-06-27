package com.neusoft.neu24.his.neuhospital;

import com.neusoft.neu24.his.neuhospital.util.GlobalData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication_login extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GlobalData.setStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication_login.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("东软云医院");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
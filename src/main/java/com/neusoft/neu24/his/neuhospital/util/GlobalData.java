package com.neusoft.neu24.his.neuhospital.util;

import com.neusoft.neu24.his.neuhospital.HelloApplication_login;
import com.neusoft.neu24.his.neuhospital.entity.Patient;
import com.neusoft.neu24.his.neuhospital.entity.Project;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    public static List<Project> projectList=new ArrayList<>();
    private static Stage stage;
    public static List<Patient> patientList=new ArrayList<>();
    public static List<Patient> patientList_jiancha=new ArrayList<>();
    public static Patient patient=new Patient();
    public static String str;

    public static void setStage(Stage stage){
        GlobalData.stage=stage;
    }
    public Stage getStage(){
        return stage;
    }
    public static void switchScene1(String sceneName,int width,int height, String title)throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication_login.class.getResource("main-home-view.fxml"));
        Scene scene=new Scene(fxmlLoader.load(),width,height);
        GlobalData.stage.setScene(scene);
        stage.setX(100);
        stage.setY(50);
    }

    public static void switchScene2(String sceneName,int width,int height, String title)throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication_login.class.getResource("register-view.fxml"));
        Scene scene=new Scene(fxmlLoader.load(),width,height);
        GlobalData.stage.setScene(scene);
        stage.setX(100);
        stage.setY(50);
    }



    public static void switchScene4(String sceneName,int width,int height, String title)throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication_login.class.getResource("initial-view.fxml"));
        Scene scene=new Scene(fxmlLoader.load(),width,height);
        GlobalData.stage.setScene(scene);
        stage.setX(100);
        stage.setY(50);

    }
    public static void switchScene5(String sceneName,int width,int height, String title)throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication_login.class.getResource("yaofang-home-view.fxml"));
        Scene scene=new Scene(fxmlLoader.load(),width,height);
        GlobalData.stage.setScene(scene);
        stage.setX(100);
        stage.setY(50);
    }

}
package com.neusoft.neu24.his.neuhospital.Controller;

import com.neusoft.neu24.his.neuhospital.entity.Patient;
import com.neusoft.neu24.his.neuhospital.util.GlobalData;
import com.neusoft.neu24.his.neuhospital.util.ReadPatient;
import javafx.fxml.FXML;

import java.io.IOException;

public class HomeController1 {

        @FXML
    public void btn_register() throws IOException {
            GlobalData.switchScene2("register-view",1280,768,"东软云医院");
            String filePath = "D:/Work/neu/his/NEU-Hospital/src/main/java/com/neusoft/neu24/his/neuhospital/patient.json"; // 替换为你的JSON文件路径
            GlobalData.patientList = ReadPatient.readPatinetsFromJson(filePath);
            if ( GlobalData.patientList  != null) {
//                for (Patient patient :  GlobalData.patientList ) {
//                    System.out.println(patient.getName() + ", " + patient.getAge());
//                    // 可以进一步处理person对象
//                }
            } else {
                System.out.println("数据加载失败");


            }
        }






        @FXML
        public void jiancha_btn() throws IOException {
            GlobalData.switchScene4("initial-view.fxml",1280,768,"东软云医院");
            String filePath = "D:/Work/neu/his/NEU-Hospital/src/main/java/com/neusoft/neu24/his/neuhospital/patient.json"; // 替换为你的JSON文件路径
            GlobalData.patientList = ReadPatient.readPatinetsFromJson(filePath);
            if (GlobalData.patientList  != null) {
            }
        }
        @FXML
    public void btn_yaofang() throws IOException {
            GlobalData.switchScene5("fayao-home-view",1280,768,"东软云医院");
            String filePath = "D:/Work/neu/his/NEU-Hospital/src/main/java/com/neusoft/neu24/his/neuhospital/patient.json"; // 替换为你的JSON文件路径
            GlobalData.patientList = ReadPatient.readPatinetsFromJson(filePath);
            if ( GlobalData.patientList  != null) {
//                for (Patient patient :  GlobalData.patientList ) {
//                    System.out.println(patient.getName() + ", " + patient.getAge());
//                    // 可以进一步处理person对象
//                }
            } else {
                System.out.println("数据加载失败");


            }

//            fayao_TableView1.getItems().clear();
//            initialize();

        }
}




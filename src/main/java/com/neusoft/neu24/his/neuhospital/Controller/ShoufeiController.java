package com.neusoft.neu24.his.neuhospital.Controller;

import com.neusoft.neu24.his.neuhospital.dao.IPatientDao;
import com.neusoft.neu24.his.neuhospital.dao.Impl.PatientDaoImpl;
import com.neusoft.neu24.his.neuhospital.entity.Patient;
import com.neusoft.neu24.his.neuhospital.service.IPatientService;
import com.neusoft.neu24.his.neuhospital.service.impl.PatientServiceImpl;
import com.neusoft.neu24.his.neuhospital.util.GlobalData;
import com.neusoft.neu24.his.neuhospital.util.ReadPatient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ShoufeiController {
    private StringProperty shoufei_searchProperty1 = new SimpleStringProperty("");
    private StringProperty shoufei_searchProperty2 = new SimpleStringProperty("");
    IPatientDao patientDao = new PatientDaoImpl();
    private IPatientService patientService = new PatientServiceImpl(patientDao);
    @FXML
     TextField shoufei_search2;

    @FXML
    TextField shoufei_search1;

    @FXML
     TextField sf_idshow;

    @FXML
     TextField sf_name;

    @FXML
     TextField sf_nameshow;

    @FXML
     TextField sf_sexshow;

    @FXML
    TableView<Patient> shoufei_tv;
    @FXML
    TableColumn<Patient, String> shoufei_btn;
    @FXML
    TableColumn<Patient, String> shoufei_name;
    @FXML
    TableColumn<Patient, String> shoufei_price;
    @FXML
    TableColumn<Patient, String> shoufei_type;
    @FXML
    TableColumn<Patient, String> shoufei_size;
    @FXML
    TableColumn<Patient, String> shoufei_amount;


    @FXML
    void btn_jiazai(ActionEvent event) {
        String filePath = "D:/Work/neu/his/NEU-Hospital/src/main/java/com/neusoft/neu24/his/neuhospital/patient.json"; // 替换为你的JSON文件路径
        GlobalData.patientList = ReadPatient.readPatinetsFromJson(filePath);
        if ( GlobalData.patientList  != null) {
//            for (Patient patient :  GlobalData.patientList ) {
//                System.out.println(patient.getName() + ", " + patient.getAge());
//                // 可以进一步处理person对象
//            }
        } else {
            System.out.println("数据加载失败");


        }
        shoufei_tv.getItems().clear();
        initialize();
    }

    public void initialize(){
        if (GlobalData.patientList == null) {
            GlobalData.patientList = new ArrayList<>();
        }

        shoufei_name.setCellValueFactory(new PropertyValueFactory("blh"));
        shoufei_price.setCellValueFactory(new PropertyValueFactory("name"));
        shoufei_type.setCellValueFactory(new PropertyValueFactory("sex"));
        shoufei_size.setCellValueFactory(new PropertyValueFactory("week"));
        shoufei_amount.setCellValueFactory(new PropertyValueFactory("day"));

        shoufei_tv.getItems().addAll(GlobalData.patientList);

        shoufei_search1.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            shoufei_searchProperty1.set(newValue);
            List<Patient> list = patientService.selectPatientByblh(shoufei_searchProperty1.get());
            if (list != null) {
                shoufei_tv.getItems().clear();
                shoufei_tv.getItems().addAll(list);
            }
        });






        shoufei_btn.setCellFactory(column -> {
            return new TableCell<Patient, String>() {

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item != null) {
                        setGraphic(null);
                    } else {
                        Button button = new Button("收费");
                        button.setOnAction(e -> handleButtonClick(button));
                        setGraphic(button);
                    }
                    setText(null);
                }

                private void handleButtonClick(Button button) {
                    // 创建一个新的Stage作为弹出窗口
                    Stage popupStage = new Stage();

                    // 设置新窗口的标题
                    popupStage.setTitle("新窗口");

                    // 创建一个简单的UI组件作为新窗口的内容，这里以一个带有文本的StackPane为例
                    StackPane popupLayout = new StackPane();
                    popupLayout.getChildren().add(new Button("收费成功"));

                    // 设置新窗口的场景，包括根节点和尺寸
                    Scene popupScene = new Scene(popupLayout, 300, 200);
                    popupStage.setScene(popupScene);

                    // 确保当新窗口关闭时不会阻止主应用关闭（根据需要调整）
                    popupStage.initModality(javafx.stage.Modality.NONE);

                    // 显示新窗口
                    popupStage.show();

                }
            };




        });
    }

}

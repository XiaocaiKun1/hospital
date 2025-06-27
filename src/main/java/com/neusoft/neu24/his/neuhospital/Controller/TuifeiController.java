package com.neusoft.neu24.his.neuhospital.Controller;

import com.neusoft.neu24.his.neuhospital.dao.IPatientDao;
import com.neusoft.neu24.his.neuhospital.dao.Impl.PatientDaoImpl;
import com.neusoft.neu24.his.neuhospital.entity.Patient;
import com.neusoft.neu24.his.neuhospital.service.IPatientService;
import com.neusoft.neu24.his.neuhospital.service.impl.PatientServiceImpl;
import com.neusoft.neu24.his.neuhospital.util.GlobalData;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class TuifeiController {
    private StringProperty tuifei_searchProperty1 = new SimpleStringProperty("");
    private StringProperty tuifei_searchProperty2 = new SimpleStringProperty("");
    IPatientDao patientDao =new PatientDaoImpl();
    private IPatientService patientService = new PatientServiceImpl(patientDao);
    @FXML
    private TextField tuifei_search1;

    @FXML
    private TextField tuifei_search2;

    @FXML
    TableColumn<Patient, String> tuifei_btn;

    @FXML
    private TableColumn<Patient, String> tuifei_amount;

    @FXML
    private TableColumn<Patient, String> tuifei_name;

    @FXML
    private TableColumn<Patient, String> tuifei_price;

    @FXML
    private TableColumn<Patient, String> tuifei_size;

    @FXML
    private TableView<Patient> tuifei_tv;

    @FXML
    private TableColumn<Patient, String> tuifei_type;
    

    public void initialize(){
        if (GlobalData.patientList == null) {
            GlobalData.patientList = new ArrayList<>();
        }

        tuifei_name.setCellValueFactory(new PropertyValueFactory("blh"));
        tuifei_price.setCellValueFactory(new PropertyValueFactory("name"));
        tuifei_type.setCellValueFactory(new PropertyValueFactory("sex"));
        tuifei_size.setCellValueFactory(new PropertyValueFactory("week"));
        tuifei_amount.setCellValueFactory(new PropertyValueFactory("day"));

        tuifei_tv.getItems().addAll(GlobalData.patientList);

        tuifei_search1.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            tuifei_searchProperty1.set(newValue);
            List<Patient> list = patientService.selectPatientByblh(tuifei_searchProperty1.get());
            if (list != null) {
                tuifei_tv.getItems().clear();
                tuifei_tv.getItems().addAll(list);
            }
        });






        tuifei_btn.setCellFactory(column -> {
            return new TableCell<Patient, String>() {

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item != null) {
                        setGraphic(null);
                    } else {
                        Button button = new Button("退费费");
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
                    popupLayout.getChildren().add(new Button("退费成功"));

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

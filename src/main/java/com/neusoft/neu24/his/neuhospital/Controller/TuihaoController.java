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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TuihaoController {
    private StringProperty tuihao_searchProperty1 = new SimpleStringProperty("");
    private StringProperty tuihao_searchProperty2 = new SimpleStringProperty("");
    IPatientDao patientDao = new PatientDaoImpl();
    private IPatientService patientService = new PatientServiceImpl(patientDao);
    @FXML
    TableView<Patient> tuihao_tv;
    @FXML
    TableColumn<Patient, String> tuihao_number;

    @FXML
    private TextField tuifei_search1;

    @FXML
    private TextField tuifei_search2;

    @FXML
    private TableColumn<Patient, String> tuihao_blh;

    @FXML
    private TableColumn<Patient, String> tuihao_btn;

    @FXML
    private TableColumn<Patient, String> tuihao_idNumber;

    @FXML
    private TableColumn<Patient, String> tuihao_name;



    @FXML
    private TableColumn<Patient, String> tuihao_pay;

    @FXML
    private TableColumn<Patient, String> tuihao_sex;




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
        tuihao_tv.getItems().clear();
        initialize();

    }

    public void initialize(){

        if (GlobalData.patientList == null) {
            GlobalData.patientList = new ArrayList<>();
        }

        tuihao_blh.setCellValueFactory(new PropertyValueFactory("blh"));
        tuihao_name.setCellValueFactory(new PropertyValueFactory("name"));
        tuihao_sex.setCellValueFactory(new PropertyValueFactory("sex"));
        tuihao_idNumber.setCellValueFactory(new PropertyValueFactory("week"));
        tuihao_pay.setCellValueFactory(new PropertyValueFactory("day"));

        tuihao_tv.getItems().addAll(GlobalData.patientList);

        tuifei_search1.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            tuihao_searchProperty1.set(newValue);
            List<Patient> list = patientService.selectPatientByblh(tuihao_searchProperty1.get());
            if (list != null) {
                tuihao_tv.getItems().clear();
                tuihao_tv.getItems().addAll(list);
            }
        });



        tuihao_number.setCellFactory(column -> {
            return new TableCell<Patient, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    TableRow<Patient> currentRow = getTableRow();
                    if (!empty && currentRow.getItem() != null) {
                        // 获取当前行的Patient对象
                        Patient patient = currentRow.getItem();

                        // 直接从Patient对象获取信息，这里以获取名字为例
                        String patientName = patient.getName();// 假设Patient类有getName()方法
                        if(patient.getName()==null){
                            setText(null);
                        }
                        // 现在您可以使用patientName进行需要的操作
                        // 例如，您可能想根据patientName设置本单元格的内容或其他逻辑
                        // 这里仅作为演示，实际根据需求调整
                        setText(Integer.toString(getIndex() + 1)); // 示例中保持原逻辑不变
                    } else {
                        setText(null);
                        setStyle("");
                    }
                }
            };
        });

        tuihao_btn.setCellFactory(column -> {
            return new TableCell<Patient, String>() {

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item != null) {
                        setGraphic(null);
                    } else {
                        Button button = new Button("退号");
                        button.setOnAction(e -> {
                            try {
                                handleButtonClick(button);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        });
                        setGraphic(button);
                    }
                    setText(null);
                }

                private void handleButtonClick(Button button) throws IOException {
                    // 创建一个新的Stage作为弹出窗口
                    Stage popupStage = new Stage();

                    // 设置新窗口的标题
                    popupStage.setTitle("新窗口");

                    // 创建一个简单的UI组件作为新窗口的内容，这里以一个带有文本的StackPane为例
                    StackPane popupLayout = new StackPane();
                    popupLayout.getChildren().add(new Label("退号成功"));

                    // 设置新窗口的场景，包括根节点和尺寸
                    Scene popupScene = new Scene(popupLayout, 300, 200);
                    popupStage.setScene(popupScene);

                    // 确保当新窗口关闭时不会阻止主应用关闭（根据需要调整）
                    popupStage.initModality(javafx.stage.Modality.NONE);

                    // 显示新窗口
                    popupStage.show();

                    List<Patient> patients = new PatientDaoImpl().loadPatients();
                    patients.remove(getIndex());
                    new PatientDaoImpl().savepatients(patients);
                    tuihao_tv.getItems().clear();
                    tuihao_tv.getItems().addAll(patients);


                }
            };




        });





    }
}

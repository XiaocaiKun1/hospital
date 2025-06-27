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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ManageController {

    @FXML
    TableView<Patient> tv_patients;
    @FXML
    TextField tf_search;
    @FXML
    TextField tf_search1;
    @FXML
    TableColumn<Patient, String> nameCloumn;
    @FXML
    TableColumn<Patient, String> IDCloumn;
    @FXML
    TableColumn<Patient, String> numberCloumn;
    @FXML
    TableColumn<Patient, String> ageCloumn;
    @FXML
    TableColumn<Patient, String> sexCloumn;
    @FXML
    TableColumn<Patient, String> btnColumn;

    private StringProperty tf_searchProperty1 = new SimpleStringProperty("");
    private StringProperty tf_searchProperty2 = new SimpleStringProperty("");
    IPatientDao patientDao = new PatientDaoImpl();
    private IPatientService patientService = new PatientServiceImpl(patientDao);
    List<Patient> patientList = patientService.selectAllPatient();
    Patient patient = new Patient();

    public void initialize() {
        nameCloumn.setCellValueFactory(new PropertyValueFactory("name"));
        //IDCloumn.setCellValueFactory(new PropertyValueFactory("ID"));
        numberCloumn.setCellValueFactory(new PropertyValueFactory("number"));
        ageCloumn.setCellValueFactory(new PropertyValueFactory("age"));
        sexCloumn.setCellValueFactory(new PropertyValueFactory("sex"));

        tv_patients.getItems().addAll(GlobalData.patientList_jiancha);

        IDCloumn.setCellFactory(column -> {
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

        tf_search.textProperty().bindBidirectional(tf_searchProperty1);
        tf_searchProperty1.addListener((observable, oldValue, newValue) -> {
            List<Patient> lps = patientService.selectPatientByblh(newValue);
            if (lps != null) {
                tv_patients.getItems().clear();
                tv_patients.getItems().addAll(lps);
            }
        });
        tf_search1.textProperty().bindBidirectional(tf_searchProperty2);
        tf_searchProperty2.addListener((observable, oldValue, newValue) -> {
            List<Patient> lps = patientService.selectPatientByName(newValue);
            if (lps != null) {
                tv_patients.getItems().clear();
                tv_patients.getItems().addAll(lps);
            }
        });

        btnColumn.setCellFactory(column -> {
            return new TableCell<Patient, String>() {

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item != null) {
                        setGraphic(null);
                    } else {
                        Button button = new Button("删除");
                        button.setOnAction(e -> handleButtonClick(button));
                        setGraphic(button);
                    }
                    setText(null);

                }

                private void handleButtonClick(Button button) {
                    //点击按钮后执行以下代码
                  patient = getTableView().getItems().get(getIndex());
                  for(int i=0;i<GlobalData.patientList_jiancha.size();i++)
                  {
                      if(GlobalData.patientList_jiancha.get(i).getNumber().equals(patient.getNumber()))
                      {
                          GlobalData.patientList_jiancha.remove(i);
                      }

                  }
                    getTableView().getItems().remove(patient);

                }


            };
        });
    }
}






















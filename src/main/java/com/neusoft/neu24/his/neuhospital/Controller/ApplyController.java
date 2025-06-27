package com.neusoft.neu24.his.neuhospital.Controller;

import com.neusoft.neu24.his.neuhospital.HelloApplication_login;
import com.neusoft.neu24.his.neuhospital.dao.IPatientDao;
import com.neusoft.neu24.his.neuhospital.dao.Impl.PatientDaoImpl;
import com.neusoft.neu24.his.neuhospital.entity.Patient;
import com.neusoft.neu24.his.neuhospital.service.IPatientService;
import com.neusoft.neu24.his.neuhospital.service.impl.PatientServiceImpl;
import com.neusoft.neu24.his.neuhospital.util.GlobalData;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.neusoft.neu24.his.neuhospital.util.GlobalData.patientList;

public class ApplyController implements Initializable {



    @FXML
    TableColumn<Patient, String> nameCloumn;
    @FXML
    AnchorPane Ap_1;

    @FXML
    TableColumn<Patient, String> IDCloumn;

    @FXML
    TableColumn<Patient, String> numberCloumn;
    @FXML
    TableColumn<Patient, String> sexCloumn;
    @FXML
    TableColumn<Patient, String> ageCloumn;
    @FXML
    TableColumn<Patient, String> operateCloumn;


    @FXML
    TableView<Patient> tv_patients;
    @FXML
    TextField tf_search1;
    @FXML
    TextField tf_search2;
    @FXML
    Label label1;
    @FXML
    Label label2;
    int size = 0;
    private StringProperty tf_searchProperty1 = new SimpleStringProperty("");
    private StringProperty tf_searchProperty2 = new SimpleStringProperty("");
    IPatientDao patientDao = new PatientDaoImpl();
    private IPatientService patientService = new PatientServiceImpl(patientDao);
    List<Patient> patientList = patientService.selectAllPatient();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        if (patientList == null) {
//            patientList = new ArrayList<>();
//        }
        nameCloumn.setCellValueFactory(new PropertyValueFactory("name"));
        //IDCloumn.setCellValueFactory(new PropertyValueFactory("ID"));
        numberCloumn.setCellValueFactory(new PropertyValueFactory("number"));
        ageCloumn.setCellValueFactory(new PropertyValueFactory("age"));
        sexCloumn.setCellValueFactory(new PropertyValueFactory("sex"));

        tv_patients.getItems().addAll(patientList);

        IDCloumn.setCellFactory(column -> {
            return new TableCell<Patient, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    TableRow<Patient> currentRow = getTableRow();
                    if (!empty && currentRow.getItem() != null) {
                        Patient patient = currentRow.getItem();
                        String patientName = patient.getName();
                        if(patient.getName()==null){
                            setText(null);
                        }
                        setText(Integer.toString(getIndex() + 1));
                    } else {
                        setText(null);
                        setStyle("");
                    }
                }
            };
        });


        tf_search1.textProperty().bindBidirectional(tf_searchProperty1);
        tf_searchProperty1.addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            List<Patient> lps = patientService.selectPatientByblh(newValue);
            if (lps != null) {
                tv_patients.getItems().clear();
                tv_patients.getItems().addAll(lps);
            }
        });
        tf_search2.textProperty().bindBidirectional(tf_searchProperty2);
        tf_searchProperty2.addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            List<Patient> lps = patientService.selectPatientByName(newValue);
            if (lps != null) {
                tv_patients.getItems().clear();
                tv_patients.getItems().addAll(lps);
            }
        });

        label1.setText("今日已检查" + size + "人");
        label2.setText("当前有" + patientService.selectAllPatient().size() + "人在等待");


        operateCloumn.setCellFactory(column -> {
            return new TableCell<Patient, String>() {
                int size=0;
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item != null) {
                        setGraphic(null);
                    } else {
                        Button button = new Button("检验项目");
                        button.setOnAction(e -> handleButtonClick(button));
                        setGraphic(button);
                    }
                    setText(null);

                }

                private void handleButtonClick(Button button) {


                   Patient patient = getTableView().getItems().get(getIndex());
                    GlobalData.patient=patient;
                    GlobalData.patientList_jiancha.add(patient);
                    size++;
                    label1.setText("今日已检查" + (size) + "人");
                    label2.setText("当前有" + (patientService.selectAllPatient().size() - size) + "人在等待");



                    Ap_1.getChildren().clear();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication_login.class.getResource("enter1-view.fxml"));
                    AnchorPane anchorPane = null;
                    try {
                        anchorPane = fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Enter1Controller enter1Controller=fxmlLoader.getController();//获取控制器对象
                    enter1Controller.setApplyController(ApplyController.this);
                    enter1Controller.Label1.setText("姓名："+ patient.getName());//设置值
                    enter1Controller.Label2.setText("病历号："+GlobalData.patient.getNumber());
                    enter1Controller.Label3.setText("年龄："+GlobalData.patient.getAge());
                    enter1Controller.Label4.setText("性别："+GlobalData.patient.getSex());

                    Ap_1.getChildren().add(anchorPane);


                }


            };
        });

        /*

         */
        /*
for (int i = 0; i < patientList.size(); i++) {
            int finalI = i;
            patientList.get(i).getOperate().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    size++;
                    label1.setText("今日已检查" + (size) + "人");
                    label2.setText("当前有" + (patientService.selectAllPatient().size() - size) + "人在等待");
                    Patient patient = patientList.get(finalI);
                    GlobalData.patient = patient;
                    Ap_1.getChildren().clear();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("enter1-view.fxml"));
                    AnchorPane anchorPane = null;
                    try {
                        anchorPane = fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    FXMLLoader fxmlLoader1=new FXMLLoader(HelloApplication.class.getResource("enter1-view.fxml"));
                    Enter1Controller enter1Controller=fxmlLoader.getController();
                    enter1Controller.setApplyController(ApplyController.this);
                    enter1Controller.Label1.setText("姓名："+ GlobalData.patient.getName());
                    enter1Controller.Label2.setText("病历号："+GlobalData.patient.getNumber());
                    enter1Controller.Label3.setText("年龄："+GlobalData.patient.getAge());
                    enter1Controller.Label4.setText("性别："+GlobalData.patient.getSex());

                    Ap_1.getChildren().add(anchorPane);

                }
            });
        }
         */


    }




}


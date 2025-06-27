package com.neusoft.neu24.his.neuhospital.Controller;

import com.neusoft.neu24.his.neuhospital.dao.IPatientDao;
import com.neusoft.neu24.his.neuhospital.dao.Impl.PatientDaoImpl;
import com.neusoft.neu24.his.neuhospital.entity.Patient;
import com.neusoft.neu24.his.neuhospital.entity.Project;
import com.neusoft.neu24.his.neuhospital.service.IPatientService;
import com.neusoft.neu24.his.neuhospital.service.impl.PatientServiceImpl;
import com.neusoft.neu24.his.neuhospital.util.GlobalData;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.List;

public class Enter2Controller {
    @FXML
    TextField tf111;
    @FXML
    TextField tf222;
    @FXML
    Button bt_1;
    @FXML
    Button bt_2;
    @FXML
    TextField tf_search;
    @FXML
    ComboBox cb_1;
    @FXML
    ComboBox cb_2;
    @FXML
    Label label;
    @FXML
    TableView<Patient> tv_1;
    @FXML
    TableView<Project> tv_2;
    @FXML
    TableColumn<Patient,String> nameCloumn;
    @FXML
    TableColumn<Patient,String> IDCloumn;
    @FXML
    TableColumn<Patient,String> numberCloumn;
    @FXML
    TableColumn<Patient,String> ageCloumn;
    @FXML
    TableColumn<Patient,String> sexCloumn;
    @FXML
    TableColumn<Patient,String> enter2btn;
    @FXML
            TableColumn<Project,String> name1Column;
    @FXML
            TableColumn<Project,String> codeColumn;

    @FXML
            TableColumn<Project,String> priceColumn;
    @FXML
            TableColumn<Project,String> sortColumn;
    @FXML
    TableColumn<Project,String>btnColumn;

    private StringProperty tf_searchProperty1 = new SimpleStringProperty("");
    private StringProperty tf_searchProperty2 = new SimpleStringProperty("");
    IPatientDao patientDao = new PatientDaoImpl();
    private IPatientService patientService = new PatientServiceImpl(patientDao);
    List<Patient> patientList = patientService.selectAllPatient();
   static String str;

    public void initialize()
    {


        nameCloumn.setCellValueFactory(new PropertyValueFactory("name"));
        //IDCloumn.setCellValueFactory(new PropertyValueFactory("ID"));
        numberCloumn.setCellValueFactory(new PropertyValueFactory("number"));
        ageCloumn.setCellValueFactory(new PropertyValueFactory("age"));
        sexCloumn.setCellValueFactory(new PropertyValueFactory("sex"));
        tv_1.getItems().addAll(GlobalData.patientList_jiancha);

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

        cb_1.getItems().addAll("心电图科","脑科","神经科");
        cb_2.getItems().addAll("刘奕泽","黄文聪","刘书涵");
        tf111.textProperty().bindBidirectional(tf_searchProperty1);
        tf_searchProperty1.addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            List<Patient> lps = patientService.selectPatientByblh(newValue);
            if (lps != null) {
                tv_1.getItems().clear();
                tv_1.getItems().addAll(lps);
            }
        });
        tf222.textProperty().bindBidirectional(tf_searchProperty2);
        tf_searchProperty2.addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            List<Patient> lps = patientService.selectPatientByName(newValue);
            if (lps != null) {
                tv_1.getItems().clear();
                tv_1.getItems().addAll(lps);
            }
        });
        enter2btn.setCellFactory(column -> {
            return new TableCell<Patient, String>() {

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item != null) {
                        setGraphic(null);
                    } else {
                        Button button = new Button("检查项目");
                        button.setOnAction(e -> handleButtonClick(button));
                        setGraphic(button);
                    }
                    setText(null);

                }

                private void handleButtonClick(Button button) {
                    Patient patient = getTableView().getItems().get(getIndex());
                    GlobalData.patient=patient;
                    codeColumn.setCellValueFactory(new PropertyValueFactory("code"));
                    name1Column.setCellValueFactory(new PropertyValueFactory("name"));
                    sortColumn.setCellValueFactory(new PropertyValueFactory("scale"));
                    priceColumn.setCellValueFactory(new PropertyValueFactory("price"));
                    if(GlobalData.patient.getProjectList()!=null) {
                        tv_2.getItems().clear();
                        tv_2.getItems().addAll(GlobalData.patient.getProjectList());
                    }

                    codeColumn.setCellFactory(column -> {
                        return new TableCell<Project, String>() {
                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                TableRow<Project> currentRow = getTableRow();
                                if (!empty && currentRow.getItem() != null) {
                                    Project project = currentRow.getItem();
                                    String patientName = project.getName();
                                    if(project.getName()==null){
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



                    btnColumn.setCellFactory(column -> {
                        return new TableCell<Project, String>() {

                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty || item != null) {
                                    setGraphic(null);
                                } else {
                                    Button button = new Button("检查科室");
                                    button.setOnAction(e -> handleButtonClick(button));
                                    setGraphic(button);
                                }
                                setText(null);

                            }

                            private void handleButtonClick(Button button) {
                                Project project = getTableView().getItems().get(getIndex());
                                label.setText("已选择的检查项目："+project.getName());
                             cb_1.setValue(project.getDept());


                            }


                        };
                    });
                }


            };
        });

    }

    @FXML
    public void tv_PatientsKeyRealeased(KeyEvent event)
    {
        if(event.getCode().equals(KeyCode.ENTER))
        {
            Project project=tv_2.getSelectionModel().getSelectedItem();
            label.setText("已选择的检查项目："+project.getName());
        }
    }
    @FXML
    public void bt_1Clicked()
    {
        GlobalData.str=str;

    }
    @FXML
    public void bt_2Clicked()
    {
        tf_search.clear();

    }

}

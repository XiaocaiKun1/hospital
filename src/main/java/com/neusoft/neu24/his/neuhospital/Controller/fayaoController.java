package com.neusoft.neu24.his.neuhospital.Controller;

import com.neusoft.neu24.his.neuhospital.dao.IMedicineDao;
import com.neusoft.neu24.his.neuhospital.dao.IPatientDao;
import com.neusoft.neu24.his.neuhospital.dao.Impl.MedicineDaoImpl;
import com.neusoft.neu24.his.neuhospital.dao.Impl.PatientDaoImpl;
import com.neusoft.neu24.his.neuhospital.entity.Medicine;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class fayaoController {
    IPatientDao patientDao=new PatientDaoImpl();
    private StringProperty fayao_searchProperty1 = new SimpleStringProperty("");
    private StringProperty fayao_searchProperty2 = new SimpleStringProperty("");
    private IPatientService patientService = new PatientServiceImpl(patientDao);
    private IMedicineDao medicineDao=new MedicineDaoImpl();


    @FXML
    AnchorPane fayao_AnchorPane;
    @FXML
    VBox fayao_VBox;
    @FXML
    TextField fayao_search1;
    @FXML
    TextField fayao_search2;

    @FXML
    TableView<Patient> fayao_TableView1;
    @FXML
    TableColumn<Patient,String> fayao_TableView_name;
    @FXML
    TableColumn<Patient,String> fayao_TableView_blh;
    @FXML
    TableColumn<Patient,String> fayao_TableView_age;
    @FXML
    TableColumn<Patient,String> fayao_TableView_sex;
    @FXML
    TableColumn<Patient,String> fayao_TableView_condition;
    @FXML
    TableColumn<Patient,String> fayao_TableView_xianshi;



    @FXML
    TableView<Medicine> fayao_TableView2;
    @FXML
    TableColumn<Medicine,String> fayao_TableView_Mid;
    @FXML
    TableColumn<Medicine,String> fayao_TableView_Mname;
    @FXML
    TableColumn<Medicine,String> fayao_TableView_Mtype;
    @FXML
    TableColumn<Medicine,String> fayao_TableView_Mproducer;
    @FXML
    TableColumn<Medicine,String> fayao_TableView_Mprice;
    @FXML
    TableColumn<Medicine,String> fayao_TableView_Mcondition;
    @FXML
    TableColumn<Medicine,String> fayao_TableView_Mbt;

    @FXML
    TableColumn<Patient,String> fayao_TableView_num;
    @FXML
    TableColumn<Medicine,String> fayao_TableView_Mnum;


    public void initialize()
    {
        if (GlobalData.patientList == null) {
        GlobalData.patientList = new ArrayList<>();
        }

        fayao_TableView_name.setCellValueFactory(new PropertyValueFactory("name"));
        fayao_TableView_blh.setCellValueFactory(new PropertyValueFactory("blh"));
        fayao_TableView_age.setCellValueFactory(new PropertyValueFactory("age"));
        fayao_TableView_sex.setCellValueFactory(new PropertyValueFactory("sex"));
        fayao_TableView_condition.setCellValueFactory(new PropertyValueFactory("condition"));
        fayao_TableView1.getItems().addAll(GlobalData.patientList);

        fayao_TableView_num.setCellFactory(column -> {
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





        //检索
        fayao_search1.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            fayao_searchProperty1.set(newValue);
            List<Patient> list = patientService.selectPatientByblh(fayao_searchProperty1.get());
            if (list != null) {
                fayao_TableView1.getItems().clear();
                fayao_TableView1.getItems().addAll(list);
            }
        });
        fayao_search2.textProperty().addListener((observable, oldValue, newValue) -> {
            fayao_searchProperty2.set(newValue);
            List<Patient> list = patientService.selectPatientByName(fayao_searchProperty2.get());
            if (list != null) {
                fayao_TableView1.getItems().clear();
                fayao_TableView1.getItems().addAll(list);
            }
        });

        //按钮
        fayao_TableView_xianshi.setCellFactory(column -> {
            return new TableCell<Patient, String>() {

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item != null) {
                        setGraphic(null);
                    } else {
                        Button button = new Button("显示药品");
                        button.setOnAction(e -> handleButtonClick(button));
                        setGraphic(button);
                    }
                    setText(null);

                }


                //按钮事件
                private void handleButtonClick(Button button) {
                    Patient patient = getTableView().getItems().get(getIndex());
                    GlobalData.patient=patient;


                    fayao_TableView_Mname.setCellValueFactory(new PropertyValueFactory("name"));
                    fayao_TableView_Mid.setCellValueFactory(new PropertyValueFactory("ID"));
                    fayao_TableView_Mtype.setCellValueFactory(new PropertyValueFactory("type"));
                    fayao_TableView_Mproducer.setCellValueFactory(new PropertyValueFactory("producer"));
                    fayao_TableView_Mprice.setCellValueFactory(new PropertyValueFactory("price"));
                    fayao_TableView_Mcondition.setCellValueFactory(new PropertyValueFactory("Mcondition"));


                    fayao_TableView_Mnum.setCellFactory(column -> {
                        return new TableCell<Medicine, String>() {
                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                TableRow<Medicine> currentRow = getTableRow();
                                if (!empty && currentRow.getItem() != null) {
                                    // 获取当前行的Patient对象
                                    Medicine medicine = currentRow.getItem();

                                    // 直接从Patient对象获取信息，这里以获取名字为例
                                    String medicineName = medicine.getName();// 假设Patient类有getName()方法
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


                    if(patient.getMedicineList()!=null) {
                        fayao_TableView2.getItems().clear();
                        fayao_TableView2.getItems().addAll(patient.getMedicineList());
                    }

                    fayao_TableView_Mbt.setCellFactory(column -> {
                        return new TableCell<Medicine, String>() {
                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty || item != null) {
                                    setGraphic(null);
                                } else {
                                    Button button = new Button("发药");
                                    button.setOnAction(e -> handleButtonClick(button));
                                    setGraphic(button);
                                }
                                setText(null);

                            }

                            private void handleButtonClick(Button button) {
                                Stage popupStage = new Stage();
                                popupStage.setTitle("提示");
                                StackPane popupLayout = new StackPane();
                                popupLayout.getChildren().add(new Label("发药成功"));
                                Scene popupScene = new Scene(popupLayout, 300, 200);
                                popupStage.setScene(popupScene);
                                popupStage.initModality(javafx.stage.Modality.NONE);
                                popupStage.show();


                                patient.setCondition("已发药");
                                fayao_TableView1.refresh();
                                Medicine medicine = getTableView().getItems().get(getIndex());
                                medicine.setMcondition("已发");
                                fayao_TableView2.refresh();


                            }


                        };


                    });
                }


            };


        });



    }
}


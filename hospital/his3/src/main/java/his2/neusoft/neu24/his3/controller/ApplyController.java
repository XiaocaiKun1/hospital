package his2.neusoft.neu24.his3.controller;

import his2.neusoft.neu24.his3.HelloApplication;
import his2.neusoft.neu24.his3.entity.projects;
import his2.neusoft.neu24.his3.util.GlobalData;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class ApplyController {
    @FXML
    TableColumn<projects, String> nameCloumn;
    @FXML
    AnchorPane Ap_1;
    @FXML
    TableColumn<projects, String> IDCloumn;
    @FXML
    TableColumn<projects, String> numberCloumn;
    @FXML
    TableColumn<projects, String> sexCloumn;
    @FXML
    TableColumn<projects, String> ageCloumn;
    @FXML
    TableColumn<projects, String> operateCloumn;
    @FXML
    TableView<projects> tv_patients;
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


    public void initialize() throws Exception {
        GlobalData.initProjectsList();

        nameCloumn.setCellValueFactory(new PropertyValueFactory("name"));
        numberCloumn.setCellValueFactory(new PropertyValueFactory("id"));
        ageCloumn.setCellValueFactory(new PropertyValueFactory("age"));
        sexCloumn.setCellValueFactory(new PropertyValueFactory("sex"));
        tv_patients.getItems().clear();
        tv_patients.getItems().addAll(GlobalData.projectList);


        IDCloumn.setCellFactory(column -> {
            return new TableCell<projects, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    TableRow<projects> currentRow = getTableRow();
                    if (!empty && currentRow.getItem() != null) {
                        projects patient = currentRow.getItem();
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
            List<projects> list1 = new ArrayList<>();
            try {
                Class.forName(GlobalData.getClassName());
                Connection con1 = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
                Statement st1 = con1.createStatement();

                System.out.println(newValue);
                tf_searchProperty1.set(newValue);
                String sql1 = "select * from projects_re where id like '%" + tf_searchProperty1.get() + "%' and real_name like '%" + tf_searchProperty2.get() + "%'";

                ResultSet res1 = st1.executeQuery(sql1);
                while (res1.next()) {
                    String name = res1.getString("name");
                    String age = res1.getString("age");
                    String sex = res1.getString("sex");
                    String id = res1.getString("id");
                    projects p = new projects(name, id, Integer.parseInt(age), sex);
                    list1.add(p);
                }
                st1.close();
                con1.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


            if (list1 != null) {
                tv_patients.getItems().clear();
                tv_patients.getItems().addAll(list1);
            }

        });
        tf_search2.textProperty().bindBidirectional(tf_searchProperty2);
        tf_searchProperty2.addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            List<projects> list1 = new ArrayList<>();
            try {
                Class.forName(GlobalData.getClassName());
                Connection con1 = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
                Statement st1 = con1.createStatement();

                System.out.println(newValue);
                tf_searchProperty1.set(newValue);
                String sql1 = "select * from projects_re where id like '%" + tf_searchProperty1.get() + "%' and real_name like '%" + tf_searchProperty2.get() + "%'";

                ResultSet res1 = st1.executeQuery(sql1);
                while (res1.next()) {
                    String name = res1.getString("name");
                    String age = res1.getString("age");
                    String sex = res1.getString("sex");
                    String id = res1.getString("id");
                    projects p = new projects(name, id, Integer.parseInt(age), sex);
                    list1.add(p);
                }
                st1.close();
                con1.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


            if (list1 != null) {
                tv_patients.getItems().clear();
                tv_patients.getItems().addAll(list1);
            }

        });

        label1.setText("今日已检查" + size + "人");
        label2.setText("当前有" + GlobalData.projectList.size() + "人正在检查");
        operateCloumn.setCellFactory(column -> {
            return new TableCell<projects, String>() {
                int size=0;
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item != null) {
                        setGraphic(null);
                    } else {
                        Button button = new Button("检验项目");
                        button.setOnAction(e -> {
                            try {
                                handleButtonClick(button);
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                        });
                        setGraphic(button);
                    }
                    setText(null);

                }

                //添加按钮点击事件
                private void handleButtonClick(Button button) throws Exception {
                    projects patient = getTableView().getItems().get(getIndex());
                    GlobalData.initProjectsList_selected(patient.getProject_id(), patient.getId());
                    Ap_1.getChildren().clear();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("enter1-view.fxml"));
                    AnchorPane anchorPane = null;
                    try {
                        anchorPane = fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Enter1Controller enter1Controller=fxmlLoader.getController();//获取控制器对象
                    enter1Controller.setApplyController(ApplyController.this);
                    enter1Controller.Label1.setText("姓名："+ patient.getName());//设置值
                    enter1Controller.Label2.setText("病历号："+patient.getId());
                    enter1Controller.Label3.setText("年龄："+patient.getAge());
                    enter1Controller.Label4.setText("性别："+patient.getSex());

                    Ap_1.getChildren().add(anchorPane);


                }


            };
        });
    }
}



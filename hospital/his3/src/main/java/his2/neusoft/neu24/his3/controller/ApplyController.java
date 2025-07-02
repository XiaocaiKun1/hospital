package his2.neusoft.neu24.his3.controller;

import his2.neusoft.neu24.his3.HelloApplication;
import his2.neusoft.neu24.his3.entity.Register;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ApplyController {
    @FXML
    TableColumn<Register, String> nameCloumn;
    @FXML
    AnchorPane Ap_1;
    @FXML
    TableColumn<Register, String> IDCloumn;
    @FXML
    TableColumn<Register, String> numberCloumn;
    @FXML
    TableColumn<Register, String> sexCloumn;
    @FXML
    TableColumn<Register, String> ageCloumn;
    @FXML
    TableColumn<Register, String> operateCloumn;
    @FXML
    TableView<Register> tv_patients;
    @FXML
    TextField tf_search1;
    @FXML
    TextField tf_search2;
    @FXML
    Label label1;
    @FXML
    Label label2;

    int size = 0;

    //实现按名搜索和按病历号搜索
    private StringProperty tf_searchProperty1 = new SimpleStringProperty("");
    private StringProperty tf_searchProperty2 = new SimpleStringProperty("");

    //初始化
    public void initialize() throws Exception {
        GlobalData.initRegisterProjectsList();

        //设置关联，同时将信息加入表中
        nameCloumn.setCellValueFactory(new PropertyValueFactory("name"));
        numberCloumn.setCellValueFactory(new PropertyValueFactory("case_number"));
        ageCloumn.setCellValueFactory(new PropertyValueFactory("age"));
        sexCloumn.setCellValueFactory(new PropertyValueFactory("sex"));
        tv_patients.getItems().clear();
        tv_patients.getItems().addAll(GlobalData.register_projects_List);

        // 为每一行设置序号列
        IDCloumn.setCellFactory(column -> {
            return new TableCell<Register, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    TableRow<Register> currentRow = getTableRow();
                    if (!empty && currentRow.getItem() != null) {
                        Register patient = currentRow.getItem();
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

        //实现搜索功能
        tf_search1.textProperty().bindBidirectional(tf_searchProperty1);
        tf_searchProperty1.addListener((observable, oldValue, newValue) -> {
            try {
                tf_searchProperty1.set(newValue);
                //查询对应的病历单
                this.search();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        //实现搜索功能
        tf_search2.textProperty().bindBidirectional(tf_searchProperty2);
        tf_searchProperty2.addListener((observable, oldValue, newValue) -> {
            try {
                tf_searchProperty2.set(newValue);
                this.search();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        label1.setText("今日已检查" + size + "人");
        label2.setText("当前有" + GlobalData.register_projects_List.size() + "人正在检查");
        operateCloumn.setCellFactory(column -> {
            return new TableCell<Register, String>() {
                int size=0;
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item != null) {
                        setGraphic(null);
                    } else {
                        Button button = new Button("检查项目");
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
                    Register patient = getTableView().getItems().get(getIndex());
                    GlobalData.register_SelectedOnProject = patient;
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
                    enter1Controller.Label2.setText("病历号："+patient.getCase_number());
                    enter1Controller.Label3.setText("年龄："+patient.getAge());
                    enter1Controller.Label4.setText("性别："+patient.getSex());

                    Ap_1.getChildren().add(anchorPane);


                }


            };
        });
    }

    public void search() throws Exception {
        List<Register> list1 = new ArrayList<>();
        Class.forName(GlobalData.getClassName());
        Connection con1 = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
        Statement st1 = con1.createStatement();

        String sql1 = "select * from register where  id in (select distinct reg_id from projects where jiancha_state = '未检查') and id like '%" + tf_searchProperty1.get() + "%' and real_name like '%" + tf_searchProperty2.get() + "%'";

        ResultSet res1 = st1.executeQuery(sql1);
        while (res1.next()) {
            String name = res1.getString("real_name");
            String age = res1.getString("age");
            String sex = res1.getString("gender");
            String id = res1.getString("id");
            Register p = new Register(id, name, age, sex);
            list1.add(p);
        }
        st1.close();
        con1.close();

        if (list1 != null) {
            tv_patients.getItems().clear();
            tv_patients.getItems().addAll(list1);
        }
    }
    @FXML
    public void bt_refresh() throws Exception {
        this.initialize();
    }

}



package his2.neusoft.neu24.his3.controller;

import his2.neusoft.neu24.his3.HelloApplication;
import his2.neusoft.neu24.his3.entity.Project;
import his2.neusoft.neu24.his3.entity.Register;
import his2.neusoft.neu24.his3.util.GlobalData;
import his2.neusoft.neu24.his3.util.SqlConnecting;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JiaohaoController {
    private StringProperty jiaohao_searchProperty1 = new SimpleStringProperty("");
    private StringProperty jiaohao_searchProperty2 = new SimpleStringProperty("");

    @FXML
    AnchorPane jiaohao;
    @FXML
    TableColumn<Register, String> numberPCloumn;
    @FXML
    TableColumn<Register, String> namePCloumn;
    @FXML
    TableColumn<Register, String> idPCloumn;
    @FXML
    TableColumn<Register, String> jiaohao_TableView_Mbt1;
    @FXML
    TableColumn<Register, String> jiaohao_TableView_Mbt2;
    @FXML
    TableColumn<Register, String> jiaohao_TableView_Mbt3;
    @FXML
    TextField tf_searchId;
    @FXML
    TextField tf_searchName;
    @FXML
    TableView<Register> tv_patients;

    public void initialize() throws Exception {

        namePCloumn.setCellValueFactory(new PropertyValueFactory("name"));
        idPCloumn.setCellValueFactory(new PropertyValueFactory("case_number"));


        //获取未处理患者  从数据库中获取
        Class.forName(GlobalData.getClassName());
        Connection con = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
        Statement st = con.createStatement();

        String sql = "select id, register.real_name from register where re_condition = '未处理'";
        ResultSet res = st.executeQuery(sql);
        List<Register> list = new ArrayList<>();
        while (res.next()) {
            String id = res.getString("id");
            String name = res.getString("real_name");
            Register register = new Register(id, name);
            list.add(register);
        }
        st.close();
        con.close();
        tv_patients.getItems().addAll(list);


        numberPCloumn.setCellFactory(column -> {
            return new TableCell<Register, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    TableRow<Register> currentRow = getTableRow();
                    if (!empty && currentRow.getItem() != null) {
                        Register patient = currentRow.getItem();
                        String patientName = patient.getName();
                        if (patient.getName() == null) {
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

        jiaohao_TableView_Mbt1.setCellFactory(column -> {
            return new TableCell<Register, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item != null) {
                        setGraphic(null);
                    } else {
                        Button button = new Button("创建病历");
                        button.setOnAction(e -> {
                            try {
                                handleButtonClick(button);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        });
                        setGraphic(button);
                    }
                    setText(null);

                }

                private void handleButtonClick(Button button) throws Exception {
                    Register patient1=getTableView().getItems().get(getIndex());
                    if(patient1.getCase_number()!=null){
                        jiaohao.getChildren().clear();
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("zhenduan-view.fxml"));
                        AnchorPane anchorPane = fxmlLoader.load();
                        jiaohao.getChildren().add(anchorPane);
                        GlobalData.register_id_Selected = patient1.getCase_number();
                    }
                }


            };


        });
        jiaohao_TableView_Mbt2.setCellFactory(column -> {
            return new TableCell<Register, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item != null) {
                        setGraphic(null);
                    } else {
                        Button button = new Button("完成");
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

                private void handleButtonClick(Button button) throws Exception {
                    // 删除当前行数据
                    Register patient1=getTableView().getItems().get(getIndex());
                    getTableView().getItems().remove(patient1);
                    tv_patients.refresh();
                    System.out.println("删除成功");
                    String id = patient1.getCase_number();
                    String sql = "update register set re_condition = '已处理' where id = '"+ id +"'";
                    SqlConnecting.Update(sql);

                    // 弹出提示框
                    Stage popupStage = new Stage();
                    popupStage.setTitle("医院系统");
                    AnchorPane popupLayout = new AnchorPane();
                    Label label = new javafx.scene.control.Label("操作成功！");
                    label.setLayoutX(110);
                    label.setLayoutY(60);
                    label.setStyle("-fx-font-size: 15px;");
                    popupLayout.getChildren().add(label);

                    Button button1 = new Button("确定");
                    button1.setLayoutX(120);
                    button1.setLayoutY(100);
                    button1.setStyle("-fx-font-size: 14px;");
                    button1.setOnAction(event -> popupStage.close());
                    popupLayout.getChildren().add(button1);


                    Scene popupScene = new Scene(popupLayout, 300, 200);
                    popupStage.setScene(popupScene);
                    // 确保当新窗口关闭时不会阻止主应用关闭（根据需要调整）
                    popupStage.initModality(javafx.stage.Modality.NONE);
                    // 显示新窗口
                    popupStage.show();

                }


            };


        });

        jiaohao_TableView_Mbt3.setCellFactory(column -> {
            return new TableCell<Register, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item != null) {
                        setGraphic(null);
                    } else {
                        Button button = new Button("创建处方");
                        button.setOnAction(e -> {
                            try {
                                handleButtonClick(button);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        });
                        setGraphic(button);
                    }
                    setText(null);

                }

                private void handleButtonClick(Button button) throws Exception {
                    Register patient1=getTableView().getItems().get(getIndex());
                    GlobalData.register_id_Selected = patient1.getCase_number();

                    jiaohao.getChildren().clear();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("chufang-view.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    jiaohao.getChildren().add(anchorPane);
                }
            };
        });

        tf_searchId.textProperty().addListener((observable, oldValue, newValue) -> {
            List<Register> list1 = new ArrayList<>();
            try {
                Class.forName(GlobalData.getClassName());
                Connection con1 = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
                Statement st1 = con1.createStatement();

                System.out.println(newValue);
                jiaohao_searchProperty1.set(newValue);
                String sql1 = "select * from register where id like '%" + jiaohao_searchProperty1.get() + "%' and real_name like '%" + jiaohao_searchProperty2.get() + "%'";

                ResultSet res1 = st1.executeQuery(sql1);
                while (res1.next()) {
                    String id = res1.getString("id");
                    String name = res1.getString("real_name");
                    Register r = new Register(id, name);
                    list1.add(r);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


            if (list1 != null) {
                tv_patients.getItems().clear();
                tv_patients.getItems().addAll(list1);
            }

        });

        tf_searchName.textProperty().addListener((observable, oldValue, newValue) -> {
            List<Register> list1 = new ArrayList<>();
            try {
                Class.forName(GlobalData.getClassName());
                Connection con1 = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
                Statement st1 = con1.createStatement();

                System.out.println(newValue);
                jiaohao_searchProperty2.set(newValue);
                String sql1 = "select * from register where id like '%" + jiaohao_searchProperty1.get() + "%' and real_name like '%" + jiaohao_searchProperty2.get() + "%'";

                ResultSet res1 = st1.executeQuery(sql1);
                while (res1.next()) {
                    String id = res1.getString("id");
                    String name = res1.getString("real_name");
                    Register r = new Register(id, name);
                    list1.add(r);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


            if (list != null) {
                tv_patients.getItems().clear();
                tv_patients.getItems().addAll(list1);
            }
        });

    }

    @FXML
    public void bt_search() throws Exception {
        String ID = tf_searchId.getText();
        String name = tf_searchName.getText();

        namePCloumn.setCellValueFactory(new PropertyValueFactory("name"));
        idPCloumn.setCellValueFactory(new PropertyValueFactory("case_number"));

        Class.forName(GlobalData.getClassName());
        Connection con = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
        Statement st = con.createStatement();

        if (ID.equals("")) {
            String sql = "select * from register_new where real_name = '" + name + "' and re_condition = '未处理'";
            ResultSet res = st.executeQuery(sql);
            List<Register> list = new ArrayList<>();
            while (res.next()) {
                String id = res.getString("id");
                String real_name = res.getString("real_name");
                Register r = new Register(id, real_name);
                list.add(r);
            }
            st.close();
            con.close();
            tv_patients.getItems().clear();
            tv_patients.getItems().addAll(list);
        } else {
            String sql = "select * from register_new where id = '" + ID + "' and re_condition = '未处理'";
            ResultSet res = st.executeQuery(sql);
            List<Register> list = new ArrayList<>();
            while (res.next()) {
                String id = res.getString("id");
                String real_name = res.getString("real_name");
                Register r = new Register(id, real_name);
                list.add(r);
            }
            st.close();
            con.close();
            tv_patients.getItems().clear();
            tv_patients.getItems().addAll(list);
        }
    }
}

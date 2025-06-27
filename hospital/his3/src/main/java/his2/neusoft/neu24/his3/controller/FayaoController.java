package his2.neusoft.neu24.his3.controller;

import his2.neusoft.neu24.his3.HelloApplication;
import his2.neusoft.neu24.his3.entity.Medicine;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FayaoController {
    private StringProperty blh_searchProperty1 = new SimpleStringProperty("");
    private StringProperty name_searchProperty1 = new SimpleStringProperty("");

    @FXML
    AnchorPane fayao_AnchorPane;
    @FXML
    VBox fayao_VBox;
    @FXML
    TextField search_name;
    @FXML
    TextField search_blh;

    @FXML
    TableView<Register> fayao_TableView1;
    @FXML
    TableColumn<Register,String> case_num;
    @FXML
    TableColumn<Register,String> real_name;
    @FXML
    TableColumn<Register,String> age;
    @FXML
    TableColumn<Register,String> gender;
    @FXML
    TableColumn<Register,String> card_num;
    @FXML
    TableColumn<Register,String> address;
    @FXML
    TableColumn<Register,String> re_control;
    @FXML
    TableColumn<Register,String> num;


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
    TableColumn<Medicine,String> fayao_TableView_Mnum;


    public void initialize() throws Exception {

        //患者信息绑定
        case_num.setCellValueFactory(new PropertyValueFactory("case_number"));
        real_name.setCellValueFactory(new PropertyValueFactory("name"));
        age.setCellValueFactory(new PropertyValueFactory("age"));
        gender.setCellValueFactory(new PropertyValueFactory("sex"));
        card_num.setCellValueFactory(new PropertyValueFactory("card_number"));
        address.setCellValueFactory(new PropertyValueFactory("home_address"));


        //患者信息
        Class.forName(GlobalData.getClassName());
        Connection con1 = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
        Statement st1 = con1.createStatement();

        String sql1 = "select * from re_need_drug";
        List<Register> list1 = new ArrayList<>();
        ResultSet res1 = st1.executeQuery(sql1);
        while(res1.next()){
            Register register = new Register(res1.getString("case_number"),res1.getString("real_name"),res1.getString("gender"),res1.getString("card_number"),res1.getLong("age"),res1.getString("home_address"));
            list1.add(register);
        }
        fayao_TableView1.getItems().addAll(list1);
        st1.close();
        con1.close();

        //搜索  根据名字进行模糊查询
        search_name.textProperty().addListener((observable, oldValue, newValue) -> {
            List<Register> list2 = new ArrayList<>();
            try {
                Class.forName(GlobalData.getClassName());
                Connection con2 = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
                Statement st2 = con2.createStatement();

                name_searchProperty1.set(newValue);
                String sql2 = "select * from re_need_drug where real_name like '%" + name_searchProperty1.get() + "%' and case_number like '%" + blh_searchProperty1.get() + "%'";

                ResultSet res2 = st2.executeQuery(sql2);
                while (res2.next()) {
                    Register register = new Register(res2.getString("case_number"),res2.getString("real_name"),res2.getString("gender"),res2.getString("card_number"),res2.getLong("age"),res2.getString("home_address"));
                    list2.add(register);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (list2 != null) {
                fayao_TableView1.getItems().clear();
                fayao_TableView1.getItems().addAll(list2);
            }

        });
        //搜索  根据病历号进行模糊查询
        search_blh.textProperty().addListener((observable, oldValue, newValue) -> {
            List<Register> list2 = new ArrayList<>();
            try {
                Class.forName(GlobalData.getClassName());
                Connection con2 = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
                Statement st2 = con2.createStatement();

                blh_searchProperty1.set(newValue);
                String sql2 = "select * from re_need_drug where real_name like '%" + name_searchProperty1.get() + "%' and case_number like '%" + blh_searchProperty1.get() + "%'";

                ResultSet res2 = st2.executeQuery(sql2);
                while (res2.next()) {
                    Register register = new Register(res2.getString("case_number"),res2.getString("real_name"),res2.getString("gender"),res2.getString("card_number"),res2.getLong("age"),res2.getString("home_address"));
                    list2.add(register);
                }

                st2.close();
                con2.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (list2 != null) {
                fayao_TableView1.getItems().clear();
                fayao_TableView1.getItems().addAll(list2);
            }

        });

        //为每个register表上序号
        num.setCellFactory(column -> {
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





        //按钮
        re_control.setCellFactory(column -> {
            return new TableCell<Register, String>() {

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item != null) {
                        setGraphic(null);
                    } else {
                        Button button = new Button("显示药品");
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


                //按钮事件
                private void handleButtonClick(Button button) throws Exception {

                    //获取当前行数据
                    Register patient = getTableView().getItems().get(getIndex());
                    String case_number = patient.getCase_number();
                    GlobalData.register_id_for_drug = case_number;

                    List<Medicine> list = new ArrayList<>();
                    //获取对应的药品信息
                    Class.forName(GlobalData.getClassName());
                    Connection con2 = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
                    Statement st2 = con2.createStatement();
                    String sql2  = "select  p.ID,d.drug_code, d.drug_name, d.drug_type, d.manufacturer, d.drug_price, p.pcondition from prescription p, drug_info d where drug_id = d.ID and p.register_id = " + "'" + case_number + "'";
                    ResultSet res2 = st2.executeQuery(sql2);

                    while (res2.next()) {
                        Medicine medicine = new Medicine(res2.getString("ID"),res2.getString("drug_name"),res2.getString("drug_type"),res2.getString("manufacturer"),res2.getString("drug_price"),res2.getString("drug_code"), res2.getString("pcondition"));
                        list.add(medicine);
                    }
                    fayao_TableView2.getItems().clear();
                    fayao_TableView2.getItems().addAll(list);
                    st2.close();
                    con2.close();


                    fayao_TableView_Mname.setCellValueFactory(new PropertyValueFactory("drug_name"));
                    fayao_TableView_Mid.setCellValueFactory(new PropertyValueFactory("drug_code"));
                    fayao_TableView_Mtype.setCellValueFactory(new PropertyValueFactory("drug_type"));
                    fayao_TableView_Mproducer.setCellValueFactory(new PropertyValueFactory("manufacturer"));
                    fayao_TableView_Mprice.setCellValueFactory(new PropertyValueFactory("drug_price"));
                    fayao_TableView_Mcondition.setCellValueFactory(new PropertyValueFactory("drug_condition"));


                    //为该患者的药品编号
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
                                    String medicineName = medicine.getDrug_name();// 假设Patient类有getName()方法
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


                    //发药按钮
                    fayao_TableView_Mbt.setCellFactory(column -> {
                        return new TableCell<Medicine, String>() {
                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty || item != null) {
                                    setGraphic(null);
                                } else {
                                    Button button = new Button("发药");
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
                                Stage popupStage = new Stage();
                                popupStage.setTitle("医院系统");
                                AnchorPane popupLayout = new AnchorPane();
                                Label label = new javafx.scene.control.Label("发药成功！");
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


                                // 因为这里保存的是处方表的ID 字段 所以可以直接使用
                                Medicine medicine = getTableView().getItems().get(getIndex());
                                String id = medicine.getID();
                                String sql = "update prescription set pcondition = '已发药' where id = " + "'" + id + "'";
                                SqlConnecting.Update(sql);
                                medicine.setDrug_condition("已发药");
                                fayao_TableView2.refresh();


                            }


                        };


                    });
                }


            };


        });

    }
    @FXML
    public void bt_refrash() throws Exception{
        fayao_AnchorPane.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fayao-view.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        fayao_AnchorPane.getChildren().add(anchorPane);


    }
}

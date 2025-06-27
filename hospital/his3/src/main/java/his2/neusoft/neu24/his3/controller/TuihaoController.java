package his2.neusoft.neu24.his3.controller;


import his2.neusoft.neu24.his3.entity.Register;
import his2.neusoft.neu24.his3.util.GlobalData;
import his2.neusoft.neu24.his3.util.SqlConnecting;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

public class TuihaoController {

    private StringProperty tuihao_searchProperty1 = new SimpleStringProperty("");
    private StringProperty tuihao_searchProperty2 = new SimpleStringProperty("");
    @FXML
    TableView<Register> tuihao_tv;

    @FXML
    TableColumn<Register, String> tuihao_number;

    @FXML
    private TextField tuifei_search1;
    @FXML
    private TextField tuifei_search2;

    @FXML
    private TableColumn<Register, String> tuihao_blh;
    @FXML
    private TableColumn<Register, String> tuihao_btn;

    @FXML
    private TableColumn<Register, String> tuihao_idNumber;

    @FXML
    private TableColumn<Register, String> tuihao_name;



    @FXML
    private TableColumn<Register, String> tuihao_pay;

    @FXML
    private TableColumn<Register, String> tuihao_sex;


    @FXML
    public void btn_jiazai(ActionEvent event) throws Exception {
        String ID = tuifei_search1.getText();
        String name = tuifei_search2.getText();

        tuihao_blh.setCellValueFactory(new PropertyValueFactory("case_number"));
        tuihao_name.setCellValueFactory(new PropertyValueFactory("name"));
        tuihao_sex.setCellValueFactory(new PropertyValueFactory("sex"));
        tuihao_idNumber.setCellValueFactory(new PropertyValueFactory("card_number"));

        Class.forName(GlobalData.getClassName());
        Connection con = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
        Statement st = con.createStatement();

        if(ID.equals(""))
        {
            String sql = "select * from register_new where real_name = '"+ name +"' and re_condition = '未处理'";
            ResultSet res = st.executeQuery(sql);
            List<Register> list = new ArrayList<>();
            while (res.next())
            {
                String id = res.getString("id");
                String real_name = res.getString("real_name");
                String sex = res.getString("gender");
                String id_card = res.getString("card_number");
                Register r = new Register(id, real_name, sex, id_card);
                list.add(r);
            }
            st.close();
            con.close();
            tuihao_tv.getItems().clear();
            tuihao_tv.getItems().addAll(list);
        }
        else
        {
            String sql = "select * from register_new where card_number = '"+ ID +"' and re_condition = '未处理'";
            ResultSet res = st.executeQuery(sql);
            List<Register> list = new ArrayList<>();
            while (res.next())
            {
                String id = res.getString("id");
                String real_name = res.getString("real_name");
                String sex = res.getString("gender");
                String id_card = res.getString("card_number");
                Register r = new Register(id, real_name, sex, id_card);
                list.add(r);
            }
            st.close();
            con.close();
            tuihao_tv.getItems().clear();
            tuihao_tv.getItems().addAll(list);
        }

        tuihao_number.setCellFactory(column -> {
            return new TableCell<Register, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    TableRow<Register> currentRow = getTableRow();
                    if (!empty && currentRow.getItem() != null) {
                        // 获取当前行的Patient对象
                        Register patient = currentRow.getItem();

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
            return new TableCell<Register, String>() {
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
                                TableRow<Register> currentRow = getTableRow();
                                Register patient = currentRow.getItem();
                                String id = patient.getCase_number();
                                //
                                String sql = "update register set re_condition = '已处理' where id = '"+ id +"'";
                                System.out.println(id);
                                SqlConnecting.Update(sql);

                            } catch (Exception ex) {
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
                    popupStage.setTitle("医院系统");
                    AnchorPane popupLayout = new AnchorPane();
                    Label label = new javafx.scene.control.Label("退号成功！");
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
    }




}
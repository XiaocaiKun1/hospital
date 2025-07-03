package his2.neusoft.neu24.his3.controller;

import his2.neusoft.neu24.his3.HelloApplication;
import his2.neusoft.neu24.his3.entity.Medicine;
import his2.neusoft.neu24.his3.util.GlobalData;
import his2.neusoft.neu24.his3.util.SqlConnecting;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import his2.neusoft.neu24.his3.entity.MedicalRecord;
import his2.neusoft.neu24.his3.service.ChatModelService;
import java.util.Arrays;
import javafx.application.Platform;
import java.sql.ResultSet;
import java.io.IOException;
import java.util.List;

public class ChufangController {
    @FXML
    private TableView<Medicine> tv_medicines;
    @FXML
    private TableColumn<Medicine, String> idCloumn;
    @FXML
    private TableColumn<Medicine, String> nameCloumn;
    @FXML
    private TableColumn<Medicine, String> priceCloumn;
    @FXML
    private TableColumn<Medicine, String> typeCloumn;
    @FXML
    private TableColumn<Medicine, String> producerCloumn;
    @FXML
    private TableColumn<Medicine, String> chufang_TableView_Mbt1;
    @FXML
    private AnchorPane Ap_6;

    String register_id;

    // 获取tableview
    public TableView<Medicine> getTv_medicines() {
        return tv_medicines;
    }
    @FXML
    void bt_save() throws Exception {
        register_id = GlobalData.register_id_Selected;
        tv_medicines.getItems().forEach(medicine -> {
            if (medicine.getID() != null)
            {
                String drug_id = medicine.getID();
                String sql = "insert into prescription (register_id,drug_id, pcondition) values('" + register_id + "','" + drug_id + "', '未处理')";
                try {
                    SqlConnecting.Insert(sql);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        tv_medicines.getItems().clear();
        // 弹出提示框
        Stage popupStage = new Stage();
        popupStage.setTitle("医院系统");
        AnchorPane popupLayout = new AnchorPane();
        Label label = new Label("操作成功！");
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
        popupStage.initModality(Modality.NONE);
        // 显示新窗口
        popupStage.show();
    }

    @FXML
    public void bt_p() throws IOException {
        Ap_6.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("chufang-view.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Ap_6.getChildren().add(anchorPane);

    }


    public void initialize() {
        nameCloumn.setCellValueFactory(new PropertyValueFactory("drug_name"));
        priceCloumn.setCellValueFactory(new PropertyValueFactory("drug_price"));
        idCloumn.setCellValueFactory(new PropertyValueFactory("ID"));
        producerCloumn.setCellValueFactory(new PropertyValueFactory("manufacturer"));
        typeCloumn.setCellValueFactory(new PropertyValueFactory("drug_type"));


        chufang_TableView_Mbt1.setCellFactory(column -> {
            return new TableCell<Medicine, String>() {
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
                    Medicine medicine=getTableView().getItems().get(getIndex());
                    getTableView().getItems().remove(medicine);
                    tv_medicines.refresh();
                    System.out.println("删除成功");


                }

            };

        });

    }

    @FXML
    public void tvMouseClicked() throws Exception
    {
        Stage stage=new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(GlobalData.getStage());
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("yaofang-view.fxml"));
        AnchorPane anchorPane=fxmlLoader.load();
        YaofangController controller=fxmlLoader.getController();
        controller.setChufangViewController(this);
        controller.setPrimarystage(stage);
        Scene scene=new Scene(anchorPane);
        stage.setScene(scene);
        stage.setTitle("医院系统 | 药房");
        stage.show();
    }
    @FXML
    private void tuijian() {
        register_id = GlobalData.register_id_Selected;
        if (register_id == null || register_id.isEmpty()) {
            showAlert("错误", "无法获取患者ID");
            return;
        }

        // 查询数据库获取病历信息
        MedicalRecord medicalRecord = getMedicalRecordFromDatabase(register_id);

        if (medicalRecord != null) {
            // 创建并初始化ChatModelService
            ChatModelService chatModelService = new ChatModelService();
            String message = "患者诉求：" + medicalRecord.getReadme() +
                            "，患者当前状况:" + medicalRecord.getPresent()+
                    "，请为我推荐具体的药品，回答时只简单回答药品名称，同时回答时不要使用文本无法显示的特殊符号或表情，尽量简洁具体，用一句话概括";
            System.out.println(message);
            chatModelService.sendMessage(List.of("用户: " + message), this::handleModelResponse);
//            String response = chatModelService.sendMessageSync(List.of("用户: " + message));
//            handleModelResponse(response);
//            System.out.println("推荐:" + response);
        } else {
            showAlert("错误", "未找到该患者的病历信息");
        }
    }

    // 处理大模型响应
    private void handleModelResponse(String modelResponse) {
        if (modelResponse != null && !modelResponse.isEmpty()) {
            // 显示标准的 Alert 弹窗
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("智能推荐");
                alert.setHeaderText(null); // 不显示头部信息
                alert.setContentText(modelResponse);
                
                // 设置弹窗大小
                alert.getDialogPane().setMinWidth(400);  // 扩大最小宽度
                alert.getDialogPane().setMinHeight(200); // 增加最小高度
                
                // 设置字体大小
                alert.getDialogPane().setStyle("-fx-font-size: 16px;"); 

                // 设置对话框的图标（可选）
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                // stage.getIcons().add(new Image("file:resources/icon-info.png")); // 如果有图标文件可以取消注释

                alert.showAndWait();
            });
        }
    }

    // 数据库查询实现
    private MedicalRecord getMedicalRecordFromDatabase(String patientId) {
        // 这里应该实现从数据库查询的逻辑
        // 仅为示例返回模拟数据
        try {
            String sql = "SELECT readme, present FROM medical_record WHERE register_id = '" + patientId + "'";
            ResultSet rs = SqlConnecting.executeQuery(sql);

            if (rs.next()) {
                String readme = rs.getString("readme");
                String present = rs.getString("present");
                return new MedicalRecord(readme, present);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 弹窗辅助方法
    private void showAlert(String title, String content) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        });
    }



}

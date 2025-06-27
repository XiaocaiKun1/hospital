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

import java.io.IOException;

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

    // 获取tableview
    public TableView<Medicine> getTv_medicines() {
        return tv_medicines;
    }
    @FXML
    void bt_save() throws Exception {
        String register_id = GlobalData.register_id_Selected;
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
}

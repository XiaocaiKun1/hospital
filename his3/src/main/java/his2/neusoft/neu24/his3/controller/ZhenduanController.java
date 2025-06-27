package his2.neusoft.neu24.his3.controller;

import his2.neusoft.neu24.his3.HelloApplication;
import his2.neusoft.neu24.his3.entity.Project;
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

public class ZhenduanController {

    @FXML
    private TextField tf_readme;
    @FXML
    private TextField tf_present;
    @FXML
    private TextField tf_presentTreat;
    @FXML
    private TextField tf_proposal;
    @FXML
    private TextField tf_careful;
    @FXML
    private TableView<Project> tv_diseases;
    @FXML
    private AnchorPane Ap_2;
    @FXML
    private TableColumn<Project, String> zhenduan_TableView_Mbt1;
    @FXML
    private TableColumn<Project, String> idDCloumn;
    @FXML
    private TableColumn<Project, String> nameDCloumn;
    @FXML
    private TableColumn<Project, String> feeDCloumn;


    public void initialize() {
        idDCloumn.setCellValueFactory(new PropertyValueFactory("id"));
        nameDCloumn.setCellValueFactory(new PropertyValueFactory("name"));
        feeDCloumn.setCellValueFactory(new PropertyValueFactory("fee"));


        zhenduan_TableView_Mbt1.setCellFactory(column -> {
            return new TableCell<Project, String>() {
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
                    Project project = getTableView().getItems().get(getIndex());
                    getTableView().getItems().remove(project);
                    tv_diseases.refresh();
                    System.out.println("删除成功");
                }
            };
        });
    }


    @FXML
    public void bt_add() throws Exception
    {
        Stage stage=new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(GlobalData.getStage());
        FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("bingku-view.fxml"));
        AnchorPane anchorPane=fxmlLoader.load();
        BingkuController controller=fxmlLoader.getController();
        controller.setZhenduanViewController(this);
        controller.setPrimarystage(stage);
        Scene scene=new Scene(anchorPane);
        stage.setScene(scene);
        stage.setTitle("医院系统 | 检查项目");
        stage.show();
    }

    @FXML
    public void bt_baocun() throws Exception {
        String regiset_id = GlobalData.register_id_Selected;
        String readme = tf_readme.getText();
        String present = tf_present.getText();
        String presentTreat = tf_presentTreat.getText();
        String proposal = tf_proposal.getText();
        String careful = tf_careful.getText();
        String sql = "insert into medical_record(register_id,readme,present,present_treat,proposal,careful) values(" + regiset_id + ", '" + readme + "','" + present + "','" + presentTreat + "','" + proposal + "','" + careful + "')";
        SqlConnecting.Insert(sql);
        tv_diseases.getItems().clear();

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
    public void bt_clear() throws Exception {
        Ap_2.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("zhenduan-view.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Ap_2.getChildren().add(anchorPane);

    }

    public TableView<Project> getTv_diseases() {
        return tv_diseases;
    }





}

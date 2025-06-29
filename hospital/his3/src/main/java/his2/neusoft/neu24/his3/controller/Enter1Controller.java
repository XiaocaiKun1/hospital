package his2.neusoft.neu24.his3.controller;

import his2.neusoft.neu24.his3.HelloApplication;
import his2.neusoft.neu24.his3.entity.Project;
import his2.neusoft.neu24.his3.entity.projects;
import his2.neusoft.neu24.his3.util.GlobalData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class Enter1Controller {
    private ApplyController applyController;

    public void setApplyController(ApplyController applyController) {
        this.applyController = applyController;
    }
    @FXML
    Label Label1;
    @FXML
    Label Label2;
    @FXML
    Label Label3;
    @FXML
    Label Label4;
    @FXML
    Label Label5;
    @FXML
    AnchorPane Ap_2;
    @FXML
    TableColumn<projects,String> codeColumn;
    @FXML
    TableColumn<projects,String> nameColumn;
    @FXML
    TableColumn<projects,String> priceColumn;
    @FXML
    TableColumn<projects,String> sortColumn;
    @FXML
    TableColumn<projects,String> numberColumn;
    @FXML
    TableView<projects> tv_patients;
    @FXML
    ComboBox cb_1;
    @FXML
    ComboBox cb_2;


    public void initialize() {
        Label1.setText("姓名");
        Label2.setText("病历号");
        Label3.setText("年龄");
        Label4.setText("性别");
        codeColumn.setCellValueFactory(new PropertyValueFactory("code"));
        nameColumn.setCellValueFactory(new PropertyValueFactory("project_name"));
        numberColumn.setCellValueFactory(new PropertyValueFactory("project_id"));
        priceColumn.setCellValueFactory(new PropertyValueFactory("fee"));
        sortColumn.setCellValueFactory(new PropertyValueFactory("sort"));
        tv_patients.getItems().addAll(GlobalData.projectList_selected);
        codeColumn.setCellFactory(column -> {
            return new TableCell<projects, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    TableRow<projects> currentRow = getTableRow();
                    if (!empty && currentRow.getItem() != null) {

                        projects project = currentRow.getItem();
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

    }
    @FXML
    public void bt_w() throws IOException {
        Ap_2.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("enter2-view.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Ap_2.getChildren().add(anchorPane);

    }
    @FXML
    public void tv_PatientsKeyRealeased(KeyEvent event)
    {
        if(event.getCode().equals(KeyCode.ENTER))
        {
            projects project=tv_patients.getSelectionModel().getSelectedItem();
            Label5.setText("已选择的检查项目："+project.getName());
        }
    }
    @FXML
    public void bt_e()
    {
        cb_1.setValue(null);
        cb_2.setValue(null);
    }



}

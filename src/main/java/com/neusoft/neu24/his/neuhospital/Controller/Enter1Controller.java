package com.neusoft.neu24.his.neuhospital.Controller;

import com.neusoft.neu24.his.neuhospital.HelloApplication_login;
import com.neusoft.neu24.his.neuhospital.entity.Project;
import com.neusoft.neu24.his.neuhospital.service.IProjectService;
import com.neusoft.neu24.his.neuhospital.service.impl.ProjectServiceImpl;
import com.neusoft.neu24.his.neuhospital.util.GlobalData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;

public class Enter1Controller {
    private ApplyController applyController;

    public void setApplyController(ApplyController applyController) {
        this.applyController = applyController;
    }

    private IProjectService projectService=new ProjectServiceImpl();
    List<Project> projectList=projectService.selectAllProject();
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
    TableColumn<Project,String> codeColumn;
    @FXML
    TableColumn<Project,String> nameColumn;
    @FXML
    TableColumn<Project,String> scaleColumn;
    @FXML
    TableColumn<Project,String> priceColumn;
    @FXML
    TableColumn<Project,String> sortColumn;
    @FXML
    TableView<Project> tv_patients;
    @FXML
    ComboBox cb_1;
    @FXML
    ComboBox cb_2;




//    public Label getLabel1() {
//        return Label1;
//    }
//    public Label getLabel2() {
//        return Label2;
//    }
//    public Label getLabel3() {
//        return Label3;
//    }
//    public Label getLabel4() {
//        return Label4;
//    }

    public void initialize()
    {
      Label1.setText("name");
        Label2.setText("number");
        Label3.setText("age");
        Label4.setText("sex");
        codeColumn.setCellValueFactory(new PropertyValueFactory("code"));
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        scaleColumn.setCellValueFactory(new PropertyValueFactory("scale"));
        priceColumn.setCellValueFactory(new PropertyValueFactory("price"));
        sortColumn.setCellValueFactory(new PropertyValueFactory("sort"));
        if(GlobalData.patient.getProjectList()!=null) {
            tv_patients.getItems().addAll(GlobalData.patient.getProjectList());
        }

        codeColumn.setCellFactory(column -> {
            return new TableCell<Project, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    TableRow<Project> currentRow = getTableRow();
                    if (!empty && currentRow.getItem() != null) {

                        Project project = currentRow.getItem();
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

        GlobalData.projectList=projectList;
        cb_1.getItems().addAll("心电图科","脑科","神经科");
        cb_2.getItems().addAll("刘奕泽","黄文聪","刘书涵");



    }
@FXML
public void bt_w() throws IOException {
    Ap_2.getChildren().clear();
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication_login.class.getResource("enter2-view.fxml"));
    AnchorPane anchorPane = fxmlLoader.load();
    Ap_2.getChildren().add(anchorPane);

}
    @FXML
    public void tv_PatientsKeyRealeased(KeyEvent event)
    {
        if(event.getCode().equals(KeyCode.ENTER))
        {
            Project project=tv_patients.getSelectionModel().getSelectedItem();
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

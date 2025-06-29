package his2.neusoft.neu24.his3.controller;

import his2.neusoft.neu24.his3.HelloApplication;
import his2.neusoft.neu24.his3.entity.Project;
import his2.neusoft.neu24.his3.entity.Register;
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
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


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
    TableColumn<Project,String> codeColumn;
    @FXML
    TableColumn<Project,String> nameColumn;
    @FXML
    TableColumn<Project,String> priceColumn;
    @FXML
    TableColumn<Project,String> sortColumn;
    @FXML
    TableColumn<Project,String> btColumn;
    @FXML
    TableView<Project> tv_patients;
    @FXML
    ComboBox cb_1;
    @FXML
    ComboBox cb_2;

    String project_id;

    public void initialize() throws Exception {
        GlobalData.initProjectsList();
        Label1.setText("姓名");
        Label2.setText("病历号");
        Label3.setText("年龄");
        Label4.setText("性别");
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory("fee"));
        tv_patients.getItems().addAll(GlobalData.projectList);

        //为每一行设置检查编号
        codeColumn.setCellFactory(column -> {
            return new TableCell<Project, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    TableRow<Project> currentRow = getTableRow();
                    if (!empty && currentRow.getItem() != null) {

                        Project project = currentRow.getItem();
                        String patientName = project.getName();
                        if (project.getName() == null) {
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
        //为每一行设置检查类别
        sortColumn.setCellFactory(column -> {
            return new TableCell<Project, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    TableRow<Project> currentRow = getTableRow();
                    if (!empty && currentRow.getItem() != null) {

                        Project project = currentRow.getItem();
                        String projectName = project.getName();
                        if (project.getName() == null) {
                            setText(null);
                        }
                        setText("检查");
                    } else {
                        setText(null);
                        setStyle("");
                    }
                }
            };
        });

        //处理选中的检查项目
        btColumn.setCellFactory(column -> {
            return new TableCell<Project, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item != null) {
                        setGraphic(null);
                    } else {
                        Button button = new Button("进行检查");
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
                    Project project=getTableView().getItems().get(getIndex());
                    project_id = project.getId();
                    //设置检查科室
                    cb_1.setValue("检查科");
                    String sql = "select project_doctor.name from project_doctor where project_doctor.id in (select distinct project.doctor from project where project.id = "+ project.getId() +");";
                    Class.forName(GlobalData.getClassName());
                    Connection con = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
                    Statement st = con.createStatement();
                    ResultSet res = st.executeQuery(sql);
                    while (res.next()) {
                        cb_2.getItems().add(res.getString("name"));
                    }

                    st.close();
                    con.close();
                }
            };
        });
    }
    @FXML
    public void bt_start() throws Exception {
        String sql = "update projects set jiancha_state='检查完成' where reg_id= " +GlobalData.register_SelectedOnProject.getCase_number() + " and pro_id= " + project_id;
        Class.forName(GlobalData.getClassName());
        Connection con = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
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
    public void bt_reset()
    {
        cb_2.setValue("");
    }

}

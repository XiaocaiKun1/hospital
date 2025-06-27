package his2.neusoft.neu24.his3.controller;

import his2.neusoft.neu24.his3.entity.Project;
import his2.neusoft.neu24.his3.util.GlobalData;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BingkuController {

    private ZhenduanController zhenduanController;
    private Stage primarystage;
    private StringProperty bingku_searchProperty1 = new SimpleStringProperty("");
    private StringProperty bingku_searchProperty2 = new SimpleStringProperty("");

    public void setPrimarystage(Stage primarystage) {
        this.primarystage = primarystage;
    }

    public void setZhenduanViewController(ZhenduanController zhenduanController) {
        this.zhenduanController = zhenduanController;
    }


    @FXML
    TableColumn<Project, String> idD;
    @FXML
    TableColumn<Project, String> nameD;
    @FXML
    TableColumn<Project, String> priceD;
    @FXML
    TableView<Project> tv_disease;
    @FXML
    TextField tf_searchId;
    @FXML
    TextField tf_searchName;

    @FXML
    public void tv_DiseasesKeyRealeased(KeyEvent event)
    {
        if(event.getCode().equals(KeyCode.ENTER))
        {
            Project disease = tv_disease.getSelectionModel().getSelectedItem();
            zhenduanController.getTv_diseases().getItems().add(disease);
        }
    }

    @FXML
    public void tv_MousePressed(MouseEvent event)
    {
        if(event.getClickCount()==2)
        {
            Project disease = tv_disease.getSelectionModel().getSelectedItem();
            zhenduanController.getTv_diseases().getItems().add(disease);
        }
    }
    public TableView<Project> getTv_diseases() {
        return tv_disease;
    }
    List<Project> projectList = new ArrayList<>();

    public void initialize () throws Exception {
        idD.setCellValueFactory(new PropertyValueFactory("id"));
        nameD.setCellValueFactory(new PropertyValueFactory("name"));
        priceD.setCellValueFactory(new PropertyValueFactory("fee"));

        Class.forName(GlobalData.getClassName());
        Connection con = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
        Statement st = con.createStatement();

        String sql = "select * from project";
        ResultSet res = st.executeQuery(sql);
        while (res.next()) {
            int id = res.getInt("id");
            String name = res.getString("name");
            BigDecimal fee = res.getBigDecimal("fee");
            Project project = new Project(id, name, fee);
            projectList.add(project);
        }
        tv_disease.getItems().addAll(projectList);
        st.close();
        con.close();

        tf_searchId.textProperty().addListener((observable, oldValue, newValue) -> {
            List<Project> list = new ArrayList<>();
            try {
                Class.forName(GlobalData.getClassName());
                Connection con1 = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
                Statement st1 = con1.createStatement();

                System.out.println(newValue);
                bingku_searchProperty1.set(newValue);
                String sql1 = "select * from project where name like '%" + bingku_searchProperty2.get() + "%' and id like '%" + bingku_searchProperty1.get() + "%'";

                ResultSet res1 = st1.executeQuery(sql1);
                while (res1.next()) {
                    int id = res1.getInt("id");
                    String name = res1.getString("name");
                    BigDecimal fee = res1.getBigDecimal("fee");
                    Project project = new Project(id, name, fee);
                    list.add(project);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


            if (list != null) {
                tv_disease.getItems().clear();
                tv_disease.getItems().addAll(list);
            }

        });

        tf_searchName.textProperty().addListener((observable, oldValue, newValue) -> {
            List<Project> list = new ArrayList<>();
            try {
                Class.forName(GlobalData.getClassName());
                Connection con1 = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
                Statement st1 = con1.createStatement();

                System.out.println(newValue);
                bingku_searchProperty2.set(newValue);
                String sql1 = "select * from project where name like '%" + bingku_searchProperty2.get() + "%' and id like '%" + bingku_searchProperty1.get() + "%'";

                ResultSet res1 = st1.executeQuery(sql1);
                while (res1.next()) {
                    int id = res1.getInt("id");
                    String name = res1.getString("name");
                    BigDecimal fee = res1.getBigDecimal("fee");
                    Project project = new Project(id, name, fee);
                    list.add(project);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


            if (list != null) {
                tv_disease.getItems().clear();
                tv_disease.getItems().addAll(list);
            }
        });

    }

    @FXML
    public void bt_search() throws Exception {
        Class.forName(GlobalData.getClassName());
        Connection con = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
        Statement st = con.createStatement();
        String sql = "select * from project where name like '%" + bingku_searchProperty2.get() + "%' and id like '%" + bingku_searchProperty1.get() + "%'";
        ResultSet res = st.executeQuery(sql);

        List<Project> list = new ArrayList<>();
        while (res.next()) {
            int id = res.getInt("id");
            String name = res.getString("name");
            BigDecimal fee = res.getBigDecimal("fee");
            Project project = new Project(id, name, fee);
            list.add(project);
        }

        tv_disease.getItems().clear();
        tv_disease.getItems().addAll(list);

        st.close();
        con.close();
    }
}

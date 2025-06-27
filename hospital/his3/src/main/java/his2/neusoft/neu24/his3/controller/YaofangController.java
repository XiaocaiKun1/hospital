package his2.neusoft.neu24.his3.controller;

import his2.neusoft.neu24.his3.entity.Medicine;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class YaofangController {
    private Stage primarystage;

    public void setPrimarystage(Stage primarystage) {
        this.primarystage = primarystage;
    }

    public Stage getPrimarystage() {
        return primarystage;
    }

    private ChufangController chufangController;

    public void setChufangViewController(ChufangController chufangController) {
        this.chufangController = chufangController;
    }

    private StringProperty search_name = new SimpleStringProperty("");
    private StringProperty search_zhu = new SimpleStringProperty("");
    @FXML
    TableView<Medicine> tv_medicines;
    @FXML
    TableColumn<Medicine, String> id;
    @FXML
    TableColumn<Medicine, String> mnemonic_code;
    @FXML
    TableColumn<Medicine, String> name;
    @FXML
    TableColumn<Medicine, String> price;
    @FXML
    TableColumn<Medicine, String> producer;
    @FXML
    TableColumn<Medicine, String> type;
    @FXML
    TextField tf_name;
    @FXML
    TextField tf_zhujima;

    @FXML
    public void tv_PatientsKeyRealeased(KeyEvent event)
    {
        if(event.getCode().equals(KeyCode.ENTER))
        {
            Medicine medicine=tv_medicines.getSelectionModel().getSelectedItem();
            chufangController.getTv_medicines().getItems().add(medicine);
        }
    }

    @FXML
    public void tv_MousePressed(MouseEvent event)
    {
        if(event.getClickCount()==2)
        {
            Medicine medicine=tv_medicines.getSelectionModel().getSelectedItem();
            chufangController.getTv_medicines().getItems().add(medicine);
        }
    }

    public TableView<Medicine> getTv_medicines() {
        return tv_medicines;
    }

    public List<Medicine> getMedicineList() throws Exception {
        Class.forName(GlobalData.getClassName());
        Connection con1 = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
        Statement st1 = con1.createStatement();

        List<Medicine> list = new ArrayList<>();
        String sql = "select * from drug_info";
        ResultSet res = st1.executeQuery(sql);
        while (res.next()) {
            String id = res.getString("ID");
            String mnemonic_code = res.getString("mnemonic_code");
            String drug_name = res.getString("drug_name");
            String manufacturer = res.getString("manufacturer");
            String drug_price = res.getString("drug_price");
            String drug_type = res.getString("drug_type");
            Medicine medicine = new Medicine(id, drug_name, mnemonic_code, drug_type, manufacturer, drug_price);
            list.add(medicine);
        }

        st1.close();
        con1.close();
        return list;
    }

    @FXML
    public void initialize () throws Exception {
        mnemonic_code.setCellValueFactory(new PropertyValueFactory("mnemonic_code"));
        name.setCellValueFactory(new PropertyValueFactory("drug_name"));
        price.setCellValueFactory(new PropertyValueFactory("drug_price"));
        producer.setCellValueFactory(new PropertyValueFactory("manufacturer"));
        type.setCellValueFactory(new PropertyValueFactory("drug_type"));
        id.setCellValueFactory(new PropertyValueFactory("ID"));

        tv_medicines.getItems().addAll(getMedicineList());

        tf_name.textProperty().addListener((observable, oldValue, newValue) -> {
            List<Medicine> list = new ArrayList<>();
            try {
                Class.forName(GlobalData.getClassName());
                Connection con1 = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
                Statement st1 = con1.createStatement();

                System.out.println(newValue);
                search_name.set(newValue);
                String sql1 = "select * from drug_info where drug_name like '%" + search_name.get() + "%' and mnemonic_code like '%" + search_zhu.get().toLowerCase() + "%'";
                System.out.println(sql1);

                ResultSet res1 = st1.executeQuery(sql1);
                while (res1.next()) {
                    String id = res1.getString("ID");
                    String mnemonic_code = res1.getString("mnemonic_code");
                    String drug_name = res1.getString("drug_name");
                    String manufacturer = res1.getString("manufacturer");
                    String drug_price = res1.getString("drug_price");
                    String drug_type = res1.getString("drug_type");
                    Medicine medicine = new Medicine(id, drug_name, mnemonic_code, drug_type, manufacturer, drug_price);
                    list.add(medicine);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


            if (list != null) {
                tv_medicines.getItems().clear();
                tv_medicines.getItems().addAll(list);
            }
        });

        tf_zhujima.textProperty().addListener((observable, oldValue, newValue) -> {
            List<Medicine> list = new ArrayList<>();
            try {
                Class.forName(GlobalData.getClassName());
                Connection con1 = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
                Statement st1 = con1.createStatement();

                System.out.println(newValue);
                search_zhu.set(newValue);
                String sql1 = "select * from drug_info where drug_name like '%" + search_name.get() + "%' and mnemonic_code like '%" + search_zhu.get().toLowerCase() + "%'";

                System.out.println(sql1);
                ResultSet res1 = st1.executeQuery(sql1);
                while (res1.next()) {
                    String id = res1.getString("ID");
                    String mnemonic_code = res1.getString("mnemonic_code");
                    String drug_name = res1.getString("drug_name");
                    String manufacturer = res1.getString("manufacturer");
                    String drug_price = res1.getString("drug_price");
                    String drug_type = res1.getString("drug_type");
                    Medicine medicine = new Medicine(id, drug_name, mnemonic_code, drug_type, manufacturer, drug_price);
                    list.add(medicine);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


            if (list != null) {
                tv_medicines.getItems().clear();
                tv_medicines.getItems().addAll(list);
            }
        });


    }
}

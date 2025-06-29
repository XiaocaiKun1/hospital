package his2.neusoft.neu24.his3.controller;

import his2.neusoft.neu24.his3.entity.Project;
import his2.neusoft.neu24.his3.entity.projects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.List;

public class Enter2Controller {
    @FXML
    TextField tf111;
    @FXML
    TextField tf222;
    @FXML
    Button bt_1;
    @FXML
    Button bt_2;
    @FXML
    TextField tf_search;
    @FXML
    ComboBox cb_1;
    @FXML
    ComboBox cb_2;
    @FXML
    Label label;
    @FXML
    TableView<projects> tv_1;
    @FXML
    TableView<projects> tv_2;
    @FXML
    TableColumn<projects,String> nameCloumn;
    @FXML
    TableColumn<projects,String> IDCloumn;
    @FXML
    TableColumn<projects,String> numberCloumn;
    @FXML
    TableColumn<projects,String> ageCloumn;
    @FXML
    TableColumn<projects,String> sexCloumn;
    @FXML
    TableColumn<projects,String> enter2btn;
    @FXML
    TableColumn<projects,String> name1Column;
    @FXML
    TableColumn<projects,String> codeColumn;

    @FXML
    TableColumn<projects,String> priceColumn;
    @FXML
    TableColumn<projects,String> sortColumn;
    @FXML
    TableColumn<projects,String>btnColumn;

    private StringProperty tf_searchProperty1 = new SimpleStringProperty("");
    private StringProperty tf_searchProperty2 = new SimpleStringProperty("");
    static String str;

    public void initialize() {
    }

}

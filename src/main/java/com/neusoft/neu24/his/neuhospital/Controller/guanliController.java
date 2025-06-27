package com.neusoft.neu24.his.neuhospital.Controller;

import com.neusoft.neu24.his.neuhospital.dao.IMedicineDao;
import com.neusoft.neu24.his.neuhospital.dao.Impl.MedicineDaoImpl;
import com.neusoft.neu24.his.neuhospital.entity.Medicine;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class guanliController {
    private StringProperty guanli_searchProperty1 = new SimpleStringProperty("");
    private StringProperty guanli_searchProperty2 = new SimpleStringProperty("");
    private StringProperty guanli_searchProperty3 = new SimpleStringProperty("");

    //对应的是MedicineDaoImpl类中static中第一个集合MEDICINE_LIST
    private IMedicineDao medicineDao = new MedicineDaoImpl();

    @FXML
    TextField guanli_search1;
    @FXML
    TextField guanli_search2;
    @FXML
    TextField guanli_search3;

    @FXML
    TableView<Medicine> guanli_TableView;
    @FXML
    TableColumn<Medicine, String> guanli_TableView_num;
    @FXML
    TableColumn<Medicine, String> guanli_TableView_Mid;
    @FXML
    TableColumn<Medicine, String> guanli_TableView_Mname;
    @FXML
    TableColumn<Medicine, String> guanli_TableView_Mtype;
    @FXML
    TableColumn<Medicine, String> guanli_TableView_Mproducer;
    @FXML
    TableColumn<Medicine, String> guanli_TableView_Mprice;
    @FXML
    TableColumn<Medicine, String> guanli_TableView_Mbt1;

    public void initialize() {


        guanli_search1.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            guanli_searchProperty1.set(newValue);
            List<Medicine> list = medicineDao.selectMedicineByID(guanli_searchProperty1.get());
            if (list != null) {
                guanli_TableView.getItems().clear();
                guanli_TableView.getItems().addAll(list);
            }
        });

        guanli_search2.textProperty().addListener((observable, oldValue, newValue) -> {
            guanli_searchProperty2.set(newValue);
            List<Medicine> list = medicineDao.selectMedicineByZhujima(guanli_searchProperty2.get());
            if (list != null) {
                guanli_TableView.getItems().clear();
                guanli_TableView.getItems().addAll(list);
            }
        });

        guanli_search3.textProperty().addListener((observable, oldValue, newValue) -> {
            guanli_searchProperty3.set(newValue);
            List<Medicine> list = medicineDao.selectMedicineByName(guanli_searchProperty3.get());
            if (list != null) {
                guanli_TableView.getItems().clear();
                guanli_TableView.getItems().addAll(list);
            }
        });

        guanli_TableView_Mname.setCellValueFactory(new PropertyValueFactory("name"));
        guanli_TableView_Mid.setCellValueFactory(new PropertyValueFactory("ID"));
        guanli_TableView_Mtype.setCellValueFactory(new PropertyValueFactory("type"));
        guanli_TableView_Mproducer.setCellValueFactory(new PropertyValueFactory("producer"));
        guanli_TableView_Mprice.setCellValueFactory(new PropertyValueFactory("price"));
        guanli_TableView.getItems().addAll(medicineDao.selectAllMedicine());

        guanli_TableView_num.setCellFactory(column -> {
            return new TableCell<Medicine, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    TableRow<Medicine> currentRow = getTableRow();
                    if (!empty && currentRow.getItem() != null) {
                        // 获取当前行的Patient对象
                        Medicine medicine = currentRow.getItem();

                        // 直接从Patient对象获取信息，这里以获取名字为例
                        String medicineID = medicine.getID();// 假设Patient类有getName()方法
                        if(medicine.getID()==null){
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

        guanli_TableView_Mbt1.setCellFactory(column -> {
            return new TableCell<Medicine, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item != null) {
                        setGraphic(null);
                    } else {
                        Button button = new Button("删除");
                        button.setOnAction(e -> {
                            try {
                                handleButtonClick(button);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        });
                        setGraphic(button);
                    }
                    setText(null);
                }

                private void handleButtonClick(Button button) throws IOException {

                    Stage popupStage = new Stage();
                    popupStage.setTitle("提示");
                    StackPane popupLayout = new StackPane();
                    popupLayout.getChildren().add(new Label("删除成功"));
                    Scene popupScene = new Scene(popupLayout, 300, 200);
                    popupStage.setScene(popupScene);
                    popupStage.initModality(javafx.stage.Modality.NONE);
                    popupStage.show();

                    Medicine medicine1=getTableView().getItems().get(getIndex());
                    getTableView().getItems().remove(medicine1);
                    guanli_TableView.refresh();
                    System.out.println("删除成功");
                }

            };
        });

    }

    public void btn_add(ActionEvent actionEvent) {
    }
}

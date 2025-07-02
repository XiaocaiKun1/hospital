package his2.neusoft.neu24.his3.controller;

import his2.neusoft.neu24.his3.entity.Project;
import his2.neusoft.neu24.his3.entity.Register;
import his2.neusoft.neu24.his3.entity.projects;
import his2.neusoft.neu24.his3.util.GlobalData;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Enter2Controller {
    @FXML
    TextField search_id;
    @FXML
    TextField search_name;
    @FXML
    Button bt_1;
    @FXML
    Button bt_2;
    @FXML
    TextField jiancha_result;
    @FXML
    Label label;
    @FXML
    TableView<Register> tv_1;
    @FXML
    TableView<Project> tv_2;
    @FXML
    TableColumn<Register,String> nameCloumn;
    @FXML
    TableColumn<Register,String> IDCloumn;
    @FXML
    TableColumn<Register,String> numberCloumn;
    @FXML
    TableColumn<Register,String> ageCloumn;
    @FXML
    TableColumn<Register,String> sexCloumn;
    @FXML
    TableColumn<Register,String> enter2btn;
    @FXML
    TableColumn<Project,String> name1Column;
    @FXML
    TableColumn<Project,String> codeColumn;

    @FXML
    TableColumn<Project,String> priceColumn;
    @FXML
    TableColumn<Project,String> sortColumn;
    @FXML
    TableColumn<Project,String>btnColumn;

    private StringProperty tf_searchProperty1 = new SimpleStringProperty("");
    private StringProperty tf_searchProperty2 = new SimpleStringProperty("");

    private Project project_selected;//存储选中的project
    private Register register_selected; //存储选中的register

    public void initialize() throws Exception {


        nameCloumn.setCellValueFactory(new PropertyValueFactory("name"));
        numberCloumn.setCellValueFactory(new PropertyValueFactory("case_number"));
        ageCloumn.setCellValueFactory(new PropertyValueFactory("age"));
        sexCloumn.setCellValueFactory(new PropertyValueFactory("sex"));

        //设置第二个table
        codeColumn.setCellValueFactory(new PropertyValueFactory("name"));
        name1Column.setCellValueFactory(new PropertyValueFactory("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory("fee"));

        //sql查询
        String sql1 = "select * from register where id in (select distinct reg_id from projects where state = '未处理' and jiancha_state = '检查完成')";
        Class.forName(GlobalData.getClassName());
        Connection con = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
        Statement st = con.createStatement();
        List< Register> registerList = new ArrayList<>();
        ResultSet res = st.executeQuery(sql1);
        while (res.next()) {
            //获取未处理的挂号表
            String case_number = res.getString("id");
            String name = res.getString("real_name");
            String sex = res.getString("gender");
            String card_number = res.getString("card_number");
            Long age = res.getLong("age");
            Register register = new Register(case_number, name, sex, card_number, age);
            registerList.add(register);
        }

        st.close();
        con.close();
        tv_1.getItems().clear();
        tv_1.getItems().addAll(registerList);

        //设置费用分类
        sortColumn.setCellFactory(column ->{
            return new TableCell<Project, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    TableRow<Project> currentRow = getTableRow();
                    if (!empty && currentRow.getItem() != null) {
                        Project project = currentRow.getItem();
                        String projectName = project.getName();
                        if(project.getName()==null){
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
        //添加序号
        IDCloumn.setCellFactory(column -> {
            return new TableCell<Register, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    TableRow<Register> currentRow = getTableRow();
                    if (!empty && currentRow.getItem() != null) {
                        Register patient = currentRow.getItem();
                        String patientName = patient.getName();
                        if(patient.getName()==null){
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

        //实现按照id搜索
        search_id.textProperty().bindBidirectional(tf_searchProperty1);
        tf_searchProperty1.addListener((observable, oldValue, newValue) -> {
            tf_searchProperty1.set(newValue);
            try {
                this.search();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        //实现按照name搜索
        search_name.textProperty().bindBidirectional(tf_searchProperty2);
        tf_searchProperty2.addListener((observable, oldValue, newValue) -> {
            tf_searchProperty2.set(newValue);
            try {
                this.search();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        enter2btn.setCellFactory(column -> {
            return new TableCell<Register, String>() {

                //为每一行添加button
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item != null) {
                        setGraphic(null);
                    } else {
                        Button button = new Button("检查项目");
                        button.setOnAction(e -> {
                            try {
                                handleButtonClick(button);
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                        });
                        setGraphic(button);
                    }
                    setText(null);
                }

                //处理按钮点击事件
                private void handleButtonClick(Button button) throws Exception {
                    Register register = getTableView().getItems().get(getIndex());
                    GlobalData.register_SelectedOnProject = register;
                    register_selected = register;
                    tv_2.getItems().clear();
                    GlobalData.initProjectsList("state", "未处理");
                    tv_2.getItems().addAll(GlobalData.projectList);

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

                    //为每一行的检查项目添加button
                    btnColumn.setCellFactory(column -> {
                        return new TableCell<Project, String>() {

                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty || item != null) {
                                    setGraphic(null);
                                } else {
                                    Button button = new Button("录入检查结果");
                                    button.setOnAction(e -> handleButtonClick(button));
                                    setGraphic(button);
                                }
                                setText(null);

                            }

                            //设置按钮点击事件 录入检查结果
                            private void handleButtonClick(Button button) {
                                project_selected = getTableView().getItems().get(getIndex());
                                label.setText("已选择的检查项目："+project_selected.getName());

                            }
                        };
                    });
                }
            };
        });

    }

    @FXML
    public void tv_PatientsKeyRealeased(KeyEvent event)
    {
        if(event.getCode().equals(KeyCode.ENTER))
        {
            Project project=tv_2.getSelectionModel().getSelectedItem();
            label.setText("已选择的检查项目："+ project.getName());
        }
    }
    @FXML
    public void bt_1Clicked () throws Exception {
        String sql1 = "update projects set results = '" + jiancha_result.getText() + "', state = '已处理' where reg_id = '" + register_selected.getCase_number() +"' and pro_id = '" + project_selected.getId() + "'";
        System.out.println(sql1);
        Class.forName(GlobalData.getClassName());
        Connection con = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
        Statement st = con.createStatement();
        st.executeUpdate(sql1);
        st.close();
        con.close();
        jiancha_result.clear();
        tv_2.getItems().clear();
        this.initialize();
    }
    @FXML
    public void bt_2Clicked()
    {
        jiancha_result.clear();
    }

    //sql查询病历表
    public void search() throws Exception{
        List<Register> list1 = new ArrayList<>();
            Class.forName(GlobalData.getClassName());
            Connection con1 = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
            Statement st1 = con1.createStatement();

            String sql1 = "select * from register where  id in (select distinct reg_id from projects where state = '未处理')) and id like '%" + tf_searchProperty1.get() + "%' and real_name like '%" + tf_searchProperty2.get() + "%'";

            ResultSet res1 = st1.executeQuery(sql1);
            while (res1.next()) {
                String name = res1.getString("real_name");
                String age = res1.getString("age");
                String sex = res1.getString("gender");
                String id = res1.getString("id");
                Register p = new Register(id, name, age, sex);
                list1.add(p);
            }
            st1.close();
            con1.close();

        if (list1 != null) {
            tv_1.getItems().clear();
            tv_1.getItems().addAll(list1);
        }

    }

}

package his2.neusoft.neu24.his3.util;

import his2.neusoft.neu24.his3.HelloApplication;
import his2.neusoft.neu24.his3.entity.Project;
import his2.neusoft.neu24.his3.entity.Register;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;
import his2.neusoft.neu24.his3.entity.projects;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GlobalData {
    private static Stage stage;

    private static String className = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/hospital";
    private static String user = "root";
    private static String password = "123456";
    public static String register_id_Selected;
    public static String register_id_for_drug;

    public static List<Register> registerList = new ArrayList<>();
    public static List<Project> projectList = new ArrayList<>();
    public static List<Register> register_projects_List = new ArrayList<>();
    public static Register register_SelectedOnProject;

    public static void switchScene(String sceneName, float width, float height, String title)throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(sceneName));
        Scene scene=new Scene(fxmlLoader.load(),width,height);
        GlobalData.stage.setScene(scene);
        stage.setTitle(title);
    }


    public GlobalData() {
    }

    public static void initRegisterList() throws Exception {
        registerList.clear();
        String sql1 = "select * from register_new";
        Class.forName(GlobalData.getClassName());
        Connection con = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
        Statement st = con.createStatement();

        ResultSet res = st.executeQuery(sql1);
        while (res.next()) {
            //获取未处理的挂号表
            String case_number = res.getString("id");
            String name = res.getString("real_name");
            String sex = res.getString("gender");
            String card_number = res.getString("card_number");
            Long age = res.getLong("age");
            String home_address = res.getString("home_address");
            Timestamp visit_date = res.getTimestamp("visit_date");
            String noon = res.getString("noon");
            String dept_name = res.getString("dept_name");
            String emp_name = res.getString("emp_name");
            String level_name = res.getString("level_name");
            BigDecimal fee = res.getBigDecimal("fee");
            String re_condition = res.getString("re_condition");

            Register register = new Register(case_number, name, sex, card_number, age, home_address, visit_date, noon, dept_name, emp_name, level_name, fee, re_condition);
            GlobalData.registerList.add(register);
        }

        st.close();
        con.close();
    }

    public static void initRegisterProjectsList() throws Exception {
        register_projects_List.clear();
        String sql1 = "select * from register where id in (select distinct reg_id from projects where jiancha_state = '未检查')";
        Class.forName(GlobalData.getClassName());
        Connection con = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
        Statement st = con.createStatement();

        ResultSet res = st.executeQuery(sql1);
        while (res.next()) {
            //获取未处理的挂号表
            String case_number = res.getString("id");
            String name = res.getString("real_name");
            String sex = res.getString("gender");
            String card_number = res.getString("card_number");
            Long age = res.getLong("age");
            Register register = new Register(case_number, name, sex, card_number, age);
            GlobalData.register_projects_List.add(register);
        }

        st.close();
        con.close();
    }

    public static void initProjectsList() throws Exception {
        projectList.clear();
        String sql1 = "select project.id, project.name, project.fee  from projects, project where pro_id=project.id and projects.reg_id =" + register_SelectedOnProject.getCase_number();
        Class.forName(GlobalData.getClassName());
        Connection con = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
        Statement st = con.createStatement();
        ResultSet res = st.executeQuery(sql1);

        while (res.next()) {
            //获取对应的检查项目
            String id = res.getString("id");
            String name = res.getString("name");
            BigDecimal fee = res.getBigDecimal("fee");
            Project project = new Project(id, name, fee);
            projectList.add(project);
        }

        st.close();
        con.close();
    }

    public static void setStage(Stage stage) {
        GlobalData.stage = stage;
    }

    public static void setClassName(String className) {
        GlobalData.className = className;
    }

    public static void setUrl(String url) {
        GlobalData.url = url;
    }

    public static void setUser(String user) {
        GlobalData.user = user;
    }

    public static void setPassword(String password) {
        GlobalData.password = password;
    }

    public static Stage getStage() {
        return stage;
    }

    public static String getClassName() {
        return className;
    }

    public static String getUrl() {
        return url;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }
}

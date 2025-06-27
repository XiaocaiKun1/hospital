package his2.neusoft.neu24.his3.controller;


import his2.neusoft.neu24.his3.util.GlobalData;
import his2.neusoft.neu24.his3.util.RegisterText;
import his2.neusoft.neu24.his3.util.SqlConnecting;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class GuahaoController {
    private long blh;

    @FXML
    private ComboBox cb_day;
    @FXML
    private ComboBox cb_doctor;
    @FXML
    private ComboBox cb_number;
    @FXML
    private ComboBox cb_pay;
    @FXML
    private ComboBox cb_room;
    @FXML
    private ComboBox cb_sex;
    @FXML
    private ComboBox cb_week;


    @FXML
    private DatePicker dp_time;
    @FXML
    private TextField tf_address;
    @FXML
    private TextField tf_age;
    @FXML
    private TextField tf_blh;
    @FXML
    private TextField tf_idnumber;
    @FXML
    private TextField tf_money;
    @FXML
    private TextField tf_name;



    @FXML
    public void initialize() throws Exception{
        cb_sex.getItems().addAll(RegisterText.getSex());
        cb_week.getItems().addAll(RegisterText.getWeek());
        cb_day.getItems().addAll(RegisterText.getDay());
        cb_room.getItems().addAll(RegisterText.getRoom());
        cb_number.getItems().addAll(RegisterText.getNumber());
        cb_pay.getItems().addAll(RegisterText.getPay());

        //获得病历号
        String sql1 = "insert into register(real_name, re_condition) values ('admin', '已处理')";
        SqlConnecting.Insert(sql1);

        Class.forName(GlobalData.getClassName());
        Connection con = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
        Statement st = con.createStatement();
        String sql2 = "select id from register where real_name = 'admin'";
        ResultSet res = st.executeQuery(sql2);
        res.next();
        blh = res.getLong("id");
        st.close();
        con.close();
        //设置病历号
        tf_blh.setText(String.valueOf(blh));
    }

    @FXML
    public void dp_ChooseTime(){
        if(dp_time.getValue() != null){
            String time = dp_time.getValue().toString();
            String[] str = time.split("-");
            LocalDateTime dateTime= LocalDateTime.now();
            LocalDateTime with = dateTime.withYear(Integer.parseInt(str[0])).withMonth(Integer.parseInt(str[1])).withDayOfMonth(Integer.parseInt(str[2]));
            DayOfWeek dayOfWeek = with.getDayOfWeek();
            switch (dayOfWeek) {
                case MONDAY:
                    cb_week.setValue("星期一");
                    break;
                case TUESDAY:
                    cb_week.setValue("星期二");
                    break;
                case WEDNESDAY:
                    cb_week.setValue("星期三");
                    break;
                case THURSDAY:
                    cb_week.setValue("星期四");
                    break;
                case FRIDAY:
                    cb_week.setValue("星期五");
                    break;
                case SATURDAY:
                    cb_week.setValue("星期六");
                    break;
                case SUNDAY:
                    cb_week.setValue("星期日");
                    break;
                default:
                    break;
            }


        }
    }


    public void cb_ChoiceRoom() throws Exception{
        String room = cb_room.getValue() != null ? cb_room.getValue().toString() : "";
        if(room.equals("")){
            tf_money.clear();
            tf_money.setText("0");
        }
        else{

            // 清空 ComboBox 的内容
            cb_doctor.getSelectionModel().clearSelection();  // 清除选择
            cb_doctor.setItems(FXCollections.observableArrayList());  // 移除所有选项
            Class.forName(GlobalData.getClassName());
            Connection con = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
            Statement st = con.createStatement();

            //获取医生
            String sql = "select emp_name from doctor_view where dept_name = '" + room + "'";
            ResultSet res = st.executeQuery(sql);
            while (res.next()) {
                cb_doctor.getItems().add(res.getString("emp_name"));
            }
            st.close();
            con.close();
        }
    }


    public void cb_ChoiceNumber() throws Exception {

        // 清空 ComboBox 的内容
        cb_doctor.getSelectionModel().clearSelection();  // 清除选择
        cb_doctor.setItems(FXCollections.observableArrayList());  // 移除所有选项

        Class.forName(GlobalData.getClassName());
        Connection con = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
        Statement st = con.createStatement();

        if (cb_number.getValue() != null){
            //获取挂号级别    以及所需费用
            String sql = "select fee from regist_level where level_name = '" + cb_number.getValue() + "'";
            ResultSet res = st.executeQuery(sql);
            if (res.next()) {
                BigDecimal fee = res.getBigDecimal("fee");
                tf_money.setText(fee.toString());
            }

            String room = cb_room.getValue() != null ? cb_room.getValue().toString() : "";
            String sql1 = "select emp_name from doctor_view where level_name = '" + cb_number.getValue() + "'" + " and dept_name = '" + room + "'";
            ResultSet res1 = st.executeQuery(sql1);
            while (res1.next()) {
                cb_doctor.getItems().add(res1.getString("emp_name"));
            }
        }
        st.close();
        con.close();
    }

    public void cb_ChoiceDoctor() throws Exception {
        if (cb_doctor.getValue() != null)
        {
            Class.forName(GlobalData.getClassName());
            Connection con = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
            Statement st = con.createStatement();

            String name = cb_doctor.getValue().toString();
            String dept = cb_room.getValue() != null ? cb_room.getValue().toString() : "";
            String sql = "select doctor_view.level_name , doctor_view.fee from doctor_view where dept_name = '" + dept + "' and emp_name = '" + name + "'";

            ResultSet res = st.executeQuery(sql);
            if(res.next())
            {
                BigDecimal fee = res.getBigDecimal("fee");
                tf_money.setText(fee.toString());
            }

            st.close();
            con.close();
        }
    }




    @FXML
    void btn_clear() throws IOException {

        tf_name.setText("");
        tf_age.setText("");
        tf_idnumber.setText("");
        tf_address.setText("");
        dp_time.setValue(null);
        tf_money.setText("");
        cb_sex.setValue(null);
        cb_week.setValue(null);
        cb_day.setValue(null);
        cb_room.setValue(null);
        cb_number.setValue(null);
        cb_doctor.setValue(null);
        cb_pay.setValue(null);
    }

    @FXML
    public void btn_guahao() throws Exception {

        Long dept_id = 0l;
        Long registe_level_id = 0l;
        Long emp_id = 0l;

        Class.forName(GlobalData.getClassName());
        Connection con = DriverManager.getConnection(GlobalData.getUrl(), GlobalData.getUser(), GlobalData.getPassword());
        Statement st = con.createStatement();
        ResultSet res;

        String condition = "未处理";
        String room = cb_room.getValue() != null ? cb_room.getValue().toString() : "";
        String levle = cb_number.getValue() != null ? cb_number.getValue().toString() : "";
        String doctor = cb_doctor.getValue() != null ? cb_doctor.getValue().toString() : "";
        String sql1 = "select id from dept where dept_name = '" + room + "'";
        String sql2 = "select id from regist_level where level_name = '" + levle + "'";

        //获取对应的外键
        res = st.executeQuery(sql1);
        if(res.next())
           dept_id = res.getLong("id");

        res = st.executeQuery(sql2);
        if (res.next())
            registe_level_id = res.getLong("id");

        String sql3 = "select id from employee where emp_name = '" + doctor + "'" + " and dept_id = " + dept_id + " and register_level =" + registe_level_id;
        res = st.executeQuery(sql3);
        if (res.next())
            emp_id = res.getLong("id");

        String noon =cb_day.getValue() != null ? cb_day.getValue().toString() : "";
        Timestamp visit_date = dp_time.getValue() != null ? Timestamp.valueOf(dp_time.getValue().atStartOfDay()) : null;
        String address = tf_address.getText();
        Long age = Long.valueOf(tf_age.getText());
        String card_num = tf_idnumber.getText();
        String sex = cb_sex.getValue() != null ? cb_sex.getValue().toString() : "";
        String name = tf_name.getText();
        String pay_way = cb_pay.getValue() != null ? cb_pay.getValue().toString() : "";
        String sql = "update register set case_number = " + blh + ", real_name = '" + name + "', gender = '" + sex + "', card_number = '" + card_num + "', age = '" + age + "', home_address = '" + address + "', visit_date = '" + visit_date + "', noon = '" + noon + "', deptment_id = '" + dept_id + "', employee_id = '" + emp_id + "', regist_level_id = '" + registe_level_id + "', re_condition = '" + condition +"', pay_way = '" + pay_way + "' where id = " + blh;
        SqlConnecting.Insert(sql);

        GlobalData.switchScene("register-view.fxml",1280,768,"东软云医院");

        //弹窗显示
        if(pay_way.equals("现金支付"))
        {
            Stage popupStage = new Stage();
            popupStage.setTitle("医院系统");
            AnchorPane popupLayout = new AnchorPane();
            Label label = new javafx.scene.control.Label("发药成功！");
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
        else {
            Stage popupStage = new Stage();
            popupStage.setTitle("医院系统 | 支付");
            StackPane popupLayout = new StackPane();
            Scene popupScene = new Scene(popupLayout, 300, 400);
            popupLayout.getChildren().add(new Label("操作成功！"));
            URL imageUrl;

            if(pay_way.equals("支付宝支付"))
                imageUrl = getClass().getResource("/images/AliPay.jpg");
            else
                imageUrl = getClass().getResource("/images/WechatPay.jpg");
            Image image = new Image(imageUrl.toString());
            ImageView view = new ImageView(image);
            view.setFitHeight(400);
            view.setFitWidth(300);
            popupLayout.getChildren().add(view);

            popupStage.setScene(popupScene);
            popupStage.initModality(Modality.NONE);
            popupStage.show();
        }

        btn_clear();



    }

}